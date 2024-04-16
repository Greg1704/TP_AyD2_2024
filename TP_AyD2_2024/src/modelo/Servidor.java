package modelo;


import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;

public class Servidor {
	
	public static void main(String[] args) { 
		ServerSocket servidor = null; 
		Socket sc = null;
	
		final int port = 10000; 
		
		//ArrayList<Integer> conexiones = new ArrayList<>(); 
		HashMap<Integer, String> conexiones = new HashMap<>();
		GestionDeTurnos gdt = new GestionDeTurnos();
		Estadisticas estadisticas = new Estadisticas();
		//ArrayList<Integer> boxesOcupados = new ArrayList<Integer>();
		HashMap<Integer, Integer> boxesOcupados = new HashMap<>();  //<N Box,Puerto Box>
		
		
		try {
			DatagramSocket socketUDP = new DatagramSocket(port); 
			System.out.println("Servidor iniciado");
			
			while(true) {
				byte[] buffer = new byte[1024];	
				//byte[] buffer_Est = new byte[5120];	
				
				DatagramPacket entrada = new DatagramPacket(buffer, buffer.length);
				socketUDP.receive(entrada);
				
				String mensaje = new String(entrada.getData());
				mensaje = mensaje.trim();
				int puertoEntrada = entrada.getPort();
				InetAddress direccion = entrada.getAddress();
				
				//conexiones.add(puertoEntrada);
				
				System.out.println(mensaje);
				System.out.println("Largo del mensaje  " + mensaje.length());
				
				//Totem: 10100 - 10200
				//Operadores: 10300 - 10400 
				//TV: 10500 - 10600 
				//Supervisor: 10700 - 10800
				
				if(puertoEntrada >= 10100 && puertoEntrada <=10200) { //Entrada de Totems
					if (!conexiones.containsKey(puertoEntrada)) { //Caso en el que el puerto no sea reconocido por el sistema
						System.out.println("se establecio la conexion con: " + puertoEntrada);
						conexiones.put(puertoEntrada,"Totem");   
					}else { //Caso en el que se este enviando un turno para el subsistema de gestion de turnos
						System.out.println("Entro al lugar indicado");
						gdt.añadirTurno(mensaje);
						gdt.mostrarCola(); //No funciona el metodo :(
					}
				}else if(puertoEntrada >= 10300 && puertoEntrada <=10400) { //Entrada de Operadores/Boxs
					if (!conexiones.containsKey(puertoEntrada)) { //Caso en el que el puerto no sea reconocido por el sistema
						System.out.println("se establecio la conexion con: " + puertoEntrada);
						conexiones.put(puertoEntrada,"Operador");
						int box = Integer.parseInt(mensaje);
						if(boxesOcupados.containsValue(box)) {
							box = 1;
							while(boxesOcupados.containsValue(box))
								box++;
						}
						boxesOcupados.put(puertoEntrada,box);
						String reg = Integer.toString(box);
						buffer = reg.getBytes();
						DatagramPacket salida = new DatagramPacket(buffer, buffer.length,direccion,puertoEntrada);
						socketUDP.send(salida);

						
					}else if(mensaje.equals("acepto")){ //Caso confirmacion de llegada del cliente al box
						System.out.println("El cliente vino al box papa");
					}else{ //Caso en el que el operador solicita un nuevo cliente para que vaya al box
						if(!gdt.isColaTurnosVacia()) {
							 
							Turno t = gdt.extraerPrimerTurno();
							Estadisticas e = Estadisticas.getInstance();
					        e.agregarTiempos(t.getCronometro().getTiempoFin());
							t.setNumeroDeBox(String.valueOf(boxesOcupados.get(puertoEntrada)));
							
							ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
					        ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);
							objectStream.writeObject(t);
				            objectStream.flush();
				            buffer = byteStream.toByteArray();
				            
				            for (Map.Entry<Integer,String> entry : conexiones.entrySet()) {
				                if (entry.getValue().equals("TV")) {
				                	DatagramPacket salida = new DatagramPacket(buffer, buffer.length,direccion,entry.getKey());
									socketUDP.send(salida);
				                }
				            }
				            
				            String reg = "hay turno";
							buffer = reg.getBytes();
				            System.out.println("Largo buffer = " + buffer.length);
							DatagramPacket salida = new DatagramPacket(buffer, buffer.length,direccion,puertoEntrada);
							socketUDP.send(salida);
					            
					        /*ByteArrayOutputStream byteStream_Est = new ByteArrayOutputStream();
					        ObjectOutputStream objectStream_Est = new ObjectOutputStream(byteStream_Est);
							objectStream_Est.writeObject(e);
							objectStream_Est.flush();
							buffer_Est = byteStream_Est.toByteArray();
					        for (Map.Entry<Integer,String> entry : conexiones.entrySet()) {
					           if (entry.getValue().equals("Supervisor")) {
						          System.out.println("Envio estadisticas (en llamar siguiente)");
			                	  DatagramPacket salida1 = new DatagramPacket(buffer_Est, buffer_Est.length,direccion,puertoEntrada);
						          socketUDP.send(salida1);
						        }
					        }
							
							
							String reg_Est = "hay turno, envio estadistica";
							buffer_Est = reg_Est.getBytes();
					        System.out.println("Largo buffer = " + buffer_Est.length);
							DatagramPacket salida_Est = new DatagramPacket(buffer_Est, buffer_Est.length,direccion,puertoEntrada);
							socketUDP.send(salida_Est);
							*/
					            
						}else {
							System.out.println("Entro en donde no hay turnos");
							String reg = "no hay turno";
							buffer = reg.getBytes();
				            System.out.println("Largo buffer = " + buffer.length);
							DatagramPacket salida = new DatagramPacket(buffer, buffer.length,direccion,puertoEntrada);
							socketUDP.send(salida);
						}
					}
				}else if(puertoEntrada >= 10500 && puertoEntrada <=10600) { //Entrada de las Pantallas TV
					if (!conexiones.containsKey(puertoEntrada)) { //Caso en el que el puerto no sea reconocido por el sistema
						System.out.println("se establecio la conexion con: " + puertoEntrada);
						conexiones.put(puertoEntrada,"TV"); 
						System.out.println("Televisor en linea papito");
						Arrays.fill(buffer, (byte) 0);
						String reg = "1234";
						buffer = reg.getBytes();
						DatagramPacket salida = new DatagramPacket(buffer, buffer.length,direccion,puertoEntrada);
						socketUDP.send(salida);
					}
				}
				else if (puertoEntrada >= 10700 && puertoEntrada <=10800) {
					if (!conexiones.containsKey(puertoEntrada)) {
					  System.out.println("se establecio la conexion con: " + puertoEntrada);
					  conexiones.put(puertoEntrada, "Supervisor");
					  
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
	
}