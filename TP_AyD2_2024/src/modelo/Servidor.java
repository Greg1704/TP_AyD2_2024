package modelo;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Servidor {
	private static long tiempoEspera;
	static int portMonitor = 11000;
	public static GestionServidor gestionServidor; 
	
	
	public static void main(String[] args) { 
		//ServerSocket servidor = null; 
		//Socket sc = null;
		
		HashMap<Integer, String> conexiones;
		GestionDeTurnos gdt;
		HashMap<Integer, Integer> boxesOcupados;
		ArrayList<Turno> turnosEnPantalla;
		
		gestionServidor =  new GestionServidor(conexiones, gdt, boxesOcupados, turnosEnPantalla); 
		
		
		InetAddress direccion;
		byte[] buffer = new byte[1024];
		//Estadisticas estadisticas = new Estadisticas();
		//ArrayList<Integer> boxesOcupados = new ArrayList<Integer>();
		
		String reg;
		
		try {
			int port = 10000;
			DatagramSocket socketUDP = new DatagramSocket(port); 
			System.out.println("Servidor iniciado");
			
			try {
				direccion = InetAddress.getByName("localHost");
				
				while(!puertoDisponible(port))
					port++;
				socketUDP = new DatagramSocket(port); 
				
				buffer = reg.getBytes();
				DatagramPacket salida = new DatagramPacket(buffer, buffer.length,direccion,portMonitor);
				
				socketUDP.send(salida);
				socketUDP.setSoTimeout(1000);
				
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			while(true) {
				byte[] buffer = new byte[1024];	
				byte[] buffer_Est = new byte[5120];	
				
				DatagramPacket entrada = new DatagramPacket(buffer, buffer.length);
				socketUDP.receive(entrada);
				
				String mensaje = new String(entrada.getData());
				mensaje = mensaje.trim();
				int puertoEntrada = entrada.getPort();
				InetAddress direccion = entrada.getAddress();
				
				//conexiones.add(puertoEntrada);
				
				System.out.println("Largo del mensaje  " + mensaje.length());
				
				//Totem: 10100 - 10200
				//Operadores: 10300 - 10400 
				//TV: 10500 - 10600 
				//Supervisor: 10700 - 10800
				
				if(puertoEntrada >= 10100 && puertoEntrada <=10200) { //Entrada de Totems
					if (!gestionServidor.getConexiones().containsKey(puertoEntrada)) { //Caso en el que el puerto no sea reconocido por el sistema
						System.out.println("se establecio la conexion con: " + puertoEntrada);
						gestionServidor.getConexiones().put(puertoEntrada,"Totem");
					 
						
						reg = "confirmacion";
						buffer = reg.getBytes();
						DatagramPacket salida = new DatagramPacket(buffer, buffer.length,direccion,puertoEntrada);
						socketUDP.send(salida);
						
					}else { //Caso en el que se este enviando un turno para el subsistema de gestion de turnos
						gestionServidor.getGdt().añadirTurno(mensaje);
						gestionServidor.getGdt().mostrarCola(); //No funciona el metodo :(
						
						reg = "confirmacion";
						buffer = reg.getBytes();
						DatagramPacket salida = new DatagramPacket(buffer, buffer.length,direccion,puertoEntrada);
						socketUDP.send(salida);
					}
				}else if(puertoEntrada >= 10300 && puertoEntrada <=10400) { //Entrada de Operadores/Boxs
					if (!gestionServidor.getConexiones().containsKey(puertoEntrada)) { //Caso en el que el puerto no sea reconocido por el sistema
						System.out.println("se establecio la conexion con: " + puertoEntrada);
						gestionServidor.getConexiones().put(puertoEntrada,"Operador");
						int box = Integer.parseInt(mensaje);
						if(gestionServidor.getBoxesOcupados().containsValue(box)) {
							box = 1;
							while(gestionServidor.getBoxesOcupados().containsValue(box))
								box++;
						}
						gestionServidor.getBoxesOcupados().put(puertoEntrada,box);
						reg = Integer.toString(box);
						buffer = reg.getBytes();
						DatagramPacket salida = new DatagramPacket(buffer, buffer.length,direccion,puertoEntrada);
						socketUDP.send(salida);

						
					}else if(mensaje.equals("acepto")){ //Caso confirmacion de llegada del cliente al box
						Estadisticas e = Estadisticas.getInstance();
						e.agregarClienteAtendidos();
						e.agregarTiempos(tiempoEspera);
						
					}else{ //Caso en el que el operador solicita un nuevo cliente para que vaya al box
						if(!gestionServidor.getGdt().isColaTurnosVacia()) {
							Turno t = gestionServidor.getGdt().extraerPrimerTurno();
							//Estadisticas e = Estadisticas.getInstance();
							tiempoEspera = t.getCronometro().getTiempoFin();
							System.out.println(tiempoEspera);
							
					        t.setNumeroDeBox(String.valueOf(gestionServidor.getBoxesOcupados().get(puertoEntrada)));
							
					        
					        if(gestionServidor.getTurnosEnPantalla().size() == 4) {
					        	gestionServidor.getTurnosEnPantalla().remove(0);
					        }
					        
					        gestionServidor.getTurnosEnPantalla().add(t);
					        
							ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
					        ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);
							objectStream.writeObject(t);
				            objectStream.flush();
				            buffer = byteStream.toByteArray();
				            
				            for (Map.Entry<Integer,String> entry : gestionServidor.getConexiones().entrySet()) {
				                if (entry.getValue().equals("TV")) {
				                	DatagramPacket salida = new DatagramPacket(buffer, buffer.length,direccion,entry.getKey());
									socketUDP.send(salida);
				                }
				            }
				            
				            reg = "hay turno";
							buffer = reg.getBytes();
				            System.out.println("Largo buffer = " + buffer.length);
							DatagramPacket salida = new DatagramPacket(buffer, buffer.length,direccion,puertoEntrada);
							socketUDP.send(salida);
					            
					       
						}else { //Entra si no hay turnos disponibles
							reg = "no hay turno";
							buffer = reg.getBytes();
				            System.out.println("Largo buffer = " + buffer.length);
							DatagramPacket salida = new DatagramPacket(buffer, buffer.length,direccion,puertoEntrada);
							socketUDP.send(salida);
						}
					}
				}else if(puertoEntrada >= 10500 && puertoEntrada <=10600) { //Entrada de las Pantallas TV
					if (!gestionServidor.getConexiones().containsKey(puertoEntrada)) { //Caso en el que el puerto no sea reconocido por el sistema
						System.out.println("se establecio la conexion con: " + puertoEntrada);
						gestionServidor.getConexiones().put(puertoEntrada,"TV"); 
						
						
						reg = "confirmacion";
						buffer = reg.getBytes();
						DatagramPacket salida = new DatagramPacket(buffer, buffer.length,direccion,puertoEntrada);
						socketUDP.send(salida);
						/*Arrays.fill(buffer, (byte) 0);
						String reg = "1234";
						buffer = reg.getBytes();
						DatagramPacket salida = new DatagramPacket(buffer, buffer.length,direccion,puertoEntrada);
						socketUDP.send(salida);*/
						for(Turno turno: gestionServidor.getTurnosEnPantalla()) {
							ByteArrayOutputStream byteStream_Est = new ByteArrayOutputStream();
				        	ObjectOutputStream objectStream_Est = new ObjectOutputStream(byteStream_Est);
							objectStream_Est.writeObject(turno);
							objectStream_Est.flush();
							buffer_Est = byteStream_Est.toByteArray();
							DatagramPacket salida1 = new DatagramPacket(buffer_Est, buffer_Est.length,direccion,puertoEntrada);
				          	socketUDP.send(salida1);
						}
					}
				}
				else if (puertoEntrada >= 10700 && puertoEntrada <=10800) { //Entrada de Supervisores
					if (!gestionServidor.getConexiones().containsKey(puertoEntrada)) {
					  System.out.println("se establecio la conexion con: " + puertoEntrada);
					  gestionServidor.getConexiones().put(puertoEntrada, "Supervisor");
					  
					  System.out.println("Envio estadisticas (en conexion)");
					  
					  Estadisticas e = Estadisticas.getInstance();
					  ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
				      ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);
				      objectStream.writeObject(e);
			          objectStream.flush();
			          buffer = byteStream.toByteArray();
			          
			          DatagramPacket salida = new DatagramPacket(buffer, buffer.length,direccion,puertoEntrada);
			          socketUDP.send(salida);
					}
					else {
					 	if (mensaje.equals("trueActualizar")){
							Estadisticas e = Estadisticas.getInstance();
							System.out.println(e.cantCliAtentidos + "   " + e.tiempoEsperaProm + "   " + e.tiempoEsperaMin + "   " + e.tiempoEsperaMax + "   ");
							ByteArrayOutputStream byteStream_Est = new ByteArrayOutputStream();
				        	ObjectOutputStream objectStream_Est = new ObjectOutputStream(byteStream_Est);
							objectStream_Est.writeObject(e);
							objectStream_Est.flush();
							buffer_Est = byteStream_Est.toByteArray();
				        	for (Map.Entry<Integer,String> entry : gestionServidor.getConexiones().entrySet()) {
				        		if (entry.getValue().equals("Supervisor")) {
						         	System.out.println("Envio Estadisticas (en Actualizar)");
			                	  	DatagramPacket salida1 = new DatagramPacket(buffer_Est, buffer_Est.length,direccion,puertoEntrada);
						          	socketUDP.send(salida1);
						        }
					        }
				        	/**
							String reg_Est = "envio estadistica";
							buffer_Est = reg_Est.getBytes();
					        System.out.println("Largo buffer = " + buffer_Est.length);
							DatagramPacket salida_Est = new DatagramPacket(buffer_Est, buffer_Est.length,direccion,puertoEntrada);
							socketUDP.send(salida_Est);
							**/
						}
					}
				}
		
				
				//System.out.println(referencia);
				
				//System.out.println(mensaje);
				//System.out.println(conexiones);
				Arrays.fill(buffer, (byte) 0);
				System.out.println("----------------------------------------");
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static boolean puertoDisponible(int puerto) {
        try {
            DatagramSocket socket = new DatagramSocket(puerto);
            socket.close(); 
            return true; 
        } catch (SocketException e) {
            return false; 
        }
    }

	private static void heartbeat() {
		
		
	}
	
}