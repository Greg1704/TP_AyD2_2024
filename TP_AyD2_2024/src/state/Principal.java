package state;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Map;

import interfaces.IStateServidor;
import modelo.Cliente;
import modelo.Estadisticas;
import modelo.GestionServidor;
import modelo.Servidor;
import modelo.Turno;

public class Principal implements IStateServidor{
	
	Servidor servidor;
	
	public Principal(Servidor s) {
		this.servidor = s;
	}
	
	@Override
	public void principal() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void secundario() {
		// TODO Auto-generated method stub
		this.servidor.setEstado(new Secundario(this.servidor));
		
	}

	@Override
	public void accion(DatagramSocket socketUDP, DatagramPacket entrada, byte[] buffer) {
		// TODO Auto-generated method stub
		DatagramPacket salida;
		String reg = "";
		
		String mensaje = new String(entrada.getData());
		mensaje = mensaje.trim();
		int puertoEntrada = entrada.getPort();
		System.out.println("Largo del mensaje  " + mensaje.length());
		
		//Totem: 10100 - 10200
		//Operadores: 10300 - 10400 
		//TV: 10500 - 10600 
		//Supervisor: 10700 - 10800
		//Monitor: 11000
		
		if(puertoEntrada >= 10100 && puertoEntrada <=10200  && principal) { //Entrada de Totems
			if (!this.servidor.getGestionServidor().getConexiones().containsKey(puertoEntrada)) { //Caso en el que el puerto no sea reconocido por el sistema
				System.out.println("se establecio la conexion con: " + puertoEntrada);
				this.servidor.getGestionServidor().getConexiones().put(puertoEntrada,"Totem");
			 
				reg = "Registrado";
				buffer = reg.getBytes();
				salida = new DatagramPacket(buffer, buffer.length,direccion,puertoEntrada);
				socketUDP.send(salida);
				
			}else if (mensaje.matches("\\d+")){ //Caso en el que se este enviando un turno para el subsistema de gestion de turnos
				
				//Extraer de mensaje un objeto Cliente
				ByteArrayInputStream byteStream = new ByteArrayInputStream(entrada.getData());
		    	ObjectInputStream objectStream = new ObjectInputStream(byteStream);
		    	Cliente cliente = (Cliente) objectStream.readObject();
				
		    	this.servidor.getGestionServidor().getGdt().añadirTurno(cliente);
		    	this.servidor.getGestionServidor().getGdt().mostrarCola(); 
				
				Thread.sleep(750);
				
				reg = "Recibido";
				buffer = reg.getBytes();
				salida = new DatagramPacket(buffer, buffer.length,direccion,puertoEntrada);
				socketUDP.send(salida);
				
			}else if(mensaje.equals("Hello there")) {  //Caso reintentos fallidos
				reg = "Reconectado";
				buffer = reg.getBytes();
				salida = new DatagramPacket(buffer, buffer.length,direccion,puertoEntrada);
				socketUDP.send(salida);
			}
		}else if(puertoEntrada >= 10300 && puertoEntrada <=10400 && principal) { //Entrada de Operadores/Boxs
			if (!this.servidor.getGestionServidor().getConexiones().containsKey(puertoEntrada)) { //Caso en el que el puerto no sea reconocido por el sistema
				System.out.println("se establecio la conexion con: " + puertoEntrada);
				this.servidor.getGestionServidor().getConexiones().put(puertoEntrada,"Operador");
				int box = Integer.parseInt(mensaje);
				if(this.servidor.getGestionServidor().getBoxesOcupados().containsValue(box)) {
					box = 1;
					while(this.servidor.getGestionServidor().getBoxesOcupados().containsValue(box))
						box++;
				}
				this.servidor.getGestionServidor().getBoxesOcupados().put(puertoEntrada,box);
				reg = Integer.toString(box);
				buffer = reg.getBytes();
				salida = new DatagramPacket(buffer, buffer.length,direccion,puertoEntrada);
				socketUDP.send(salida);

				
			}else if(mensaje.equals("acepto")){ //Caso confirmacion de llegada del cliente al box
				Estadisticas e = this.servidor.getGestionServidor().getEstadisticas();
				e.agregarClienteAtendidos();
				e.agregarTiempos(this.servidor.getTiempoEspera());
				
				//Hacer el tema relacionado con la persistencia que nahue dijo con la variable tiempoAtendido
				
				
			}else if(mensaje.equals("Solicito un turno")){ //Caso en el que el operador solicita un nuevo cliente para que vaya al box
				Thread.sleep(750);
				if(!this.servidor.getGestionServidor().getGdt().isColaTurnosVacia()) {
					reg = "hay turno";
					buffer = reg.getBytes();
		            System.out.println("Largo buffer = " + buffer.length);
					salida = new DatagramPacket(buffer, buffer.length,direccion,puertoEntrada);
					socketUDP.send(salida);
					
					Turno t = this.servidor.getGestionServidor().getGdt().extraerPrimerTurno();
					//Estadisticas e = Estadisticas.getInstance();
					this.servidor.setTiempoEspera(t.getCronometro().getTiempoFin());
					
					this.servidor.setTiempoAtendido(t.getCronometro().getTiempoAtendido());
					
					System.out.println(this.servidor.getTiempoEspera());
					
			        t.setNumeroDeBox(String.valueOf(this.servidor.getGestionServidor().getBoxesOcupados().get(puertoEntrada)));
					
			        
			        if(this.servidor.getGestionServidor().getTurnosEnPantalla().size() == 4) {
			        	this.servidor.getGestionServidor().getTurnosEnPantalla().remove(0);
			        }
			        
			        this.servidor.getGestionServidor().getTurnosEnPantalla().add(t);
			        
					ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
			        ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);
					objectStream.writeObject(t);
		            objectStream.flush();
		            buffer = byteStream.toByteArray();
		            
		            for (Map.Entry<Integer,String> entry : this.servidor.getGestionServidor().getConexiones().entrySet()) {
		                if (entry.getValue().equals("TV")) {
		                	salida = new DatagramPacket(buffer, buffer.length,direccion,entry.getKey());
							socketUDP.send(salida);
		                }
		            }
		            
		           
			            
			       
				}else { //Entra si no hay turnos disponibles
					reg = "no hay turno";
					buffer = reg.getBytes();
		            System.out.println("Largo buffer = " + buffer.length);
					salida = new DatagramPacket(buffer, buffer.length,direccion,puertoEntrada);
					socketUDP.send(salida);
				}
			}else if(mensaje.matches("\\d+")) {  //Caso reintentos fallidos
				reg = "Reconectado";
				buffer = reg.getBytes();
				salida = new DatagramPacket(buffer, buffer.length,direccion,puertoEntrada);
				socketUDP.send(salida);
			}
		}else if(puertoEntrada >= 10500 && puertoEntrada <=10600  && principal) { //Entrada de las Pantallas TV
			if (!this.servidor.getGestionServidor().getConexiones().containsKey(puertoEntrada)) { //Caso en el que el puerto no sea reconocido por el sistema
				System.out.println("se establecio la conexion con: " + puertoEntrada);
				this.servidor.getGestionServidor().getConexiones().put(puertoEntrada,"TV"); 
				
				
				reg = "confirmacion";
				buffer = reg.getBytes();
				salida = new DatagramPacket(buffer, buffer.length,direccion,puertoEntrada);
				socketUDP.send(salida);
				/*Arrays.fill(buffer, (byte) 0);
				String reg = "1234";
				buffer = reg.getBytes();
				DatagramPacket salida = new DatagramPacket(buffer, buffer.length,direccion,puertoEntrada);
				socketUDP.send(salida);*/
				for(Turno turno: this.servidor.getGestionServidor().getTurnosEnPantalla()) {
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
		else if (puertoEntrada >= 10700 && puertoEntrada <=10800  && principal) { //Entrada de Supervisores
			if (!this.servidor.getGestionServidor().getConexiones().containsKey(puertoEntrada)) {
			  System.out.println("se establecio la conexion con: " + puertoEntrada);
			  this.servidor.getGestionServidor().getConexiones().put(puertoEntrada, "Supervisor");
			  
			  System.out.println("Envio estadisticas (en conexion)");
			  
			  Estadisticas e = this.servidor.getGestionServidor().getEstadisticas();
			  ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		      ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);
		      objectStream.writeObject(e);
	          objectStream.flush();
	          buffer = byteStream.toByteArray();
	          
	          salida = new DatagramPacket(buffer, buffer.length,direccion,puertoEntrada);
	          socketUDP.send(salida);
			}
			else if(mensaje.equals("trueActualizar")){  //Caso en el que se actualizan las estadisticas
				Thread.sleep(750);
			 	if (mensaje.equals("trueActualizar")){
					Estadisticas e = this.servidor.getGestionServidor().getEstadisticas();
					System.out.println(e.cantCliAtentidos + "   " + e.tiempoEsperaProm + "   " + e.tiempoEsperaMin + "   " + e.tiempoEsperaMax + "   ");
					ByteArrayOutputStream byteStream_Est = new ByteArrayOutputStream();
		        	ObjectOutputStream objectStream_Est = new ObjectOutputStream(byteStream_Est);
					objectStream_Est.writeObject(e);
					objectStream_Est.flush();
					buffer_Est = byteStream_Est.toByteArray();
		        	for (Map.Entry<Integer,String> entry : this.servidor.getGestionServidor().getConexiones().entrySet()) {
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
			}else if(mensaje.equals("Hello there")) {  //Caso reintentos fallidos
				reg = "Reconectado";
				buffer = reg.getBytes();
				salida = new DatagramPacket(buffer, buffer.length,direccion,puertoEntrada);
				socketUDP.send(salida);
			}
		}else if(puertoEntrada == 11000) {  //Entrada del Monitor
			if(mensaje.equals("ping")) {  //Caso pingEcho
				
				this.estado.principal();
				reg = "pong";
				buffer = reg.getBytes();
				salida = new DatagramPacket(buffer, buffer.length,direccion,portMonitor);
				socketUDP.send(salida);
				
			
				ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
			    ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);
			    objectStream.writeObject(this.servidor.getGestionServidor());
		        objectStream.flush();
		        buffer = byteStream.toByteArray();
		        
		        System.out.println("Largo del buffer " + buffer.length);
		        
				
		        
		        for(int i = 10000;i<10011;i++) {
		        	if(i != port) {
			        	salida = new DatagramPacket(buffer, buffer.length,direccion,i);
				        socketUDP.send(salida);
		        	}
		        }
		        
		        this.servidor.getGestionServidor().getGdt().mostrarCola();
				
			}else if(mensaje.equals("cambio")) { //Caso en el que un servidor secundario pasa a ser el principal
				
				this.estado.principal();
				reg = "reemplazo";
				buffer = reg.getBytes();
				salida = new DatagramPacket(buffer, buffer.length,direccion,portMonitor);
				socketUDP.send(salida);
				//Mandarle al monitor que soy el nuevo jefe
				
				for (Map.Entry<Integer,String> entry : this.servidor.getGestionServidor().getConexiones().entrySet()) {
					reg = "cambio";
					buffer = reg.getBytes();
					salida = new DatagramPacket(buffer, buffer.length,direccion,entry.getKey());
					socketUDP.send(salida);
				}
			}
		} else if(puertoEntrada>= 10000 && puertoEntrada <10011 && puertoEntrada != port) { //recibe datos de backup
		
	    	 try {
	    		 
		    	 // Deserializar bytes recibidos en objeto Estadisticas
		    	 ByteArrayInputStream byteStream = new ByteArrayInputStream(entrada.getData());
		    	 ObjectInputStream objectStream = new ObjectInputStream(byteStream);
		    	 this.servidor.setGestionServidor((GestionServidor) objectStream.readObject());
		    	 //System.out.println("Recibo estadisticas");
		    	 //gestionServidor.getGdt().mostrarCola();
		    	 
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	 
			
		}
		
	}

}
