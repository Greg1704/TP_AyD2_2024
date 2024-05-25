package state;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Map;

import interfaces.IStateServidor;
import modelo.Cliente;
import modelo.Estadisticas;
import modelo.GestionServidor;
import modelo.Servidor;
import modelo.Turno;

public class Secundario implements IStateServidor{
	
	Servidor servidor;
	
	public Secundario(Servidor s) {
		this.servidor = s;
	}

	@Override
	public void principal() {
		// TODO Auto-generated method stub
		this.servidor.setEstado(new Principal(this.servidor));
		
	}

	@Override
	public void secundario() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void accion(DatagramSocket socketUDP, DatagramPacket entrada, byte[] buffer) throws IOException, InterruptedException, ClassNotFoundException {
		// TODO Auto-generated method stub
		DatagramPacket salida;
		String reg = "";
		InetAddress direccion = InetAddress.getByName("localHost");
		
		String mensaje = new String(entrada.getData());
		mensaje = mensaje.trim();
		int puertoEntrada = entrada.getPort();
		System.out.println("Largo del mensaje  " + mensaje.length());
		
		//Totem: 10100 - 10200
		//Operadores: 10300 - 10400 
		//TV: 10500 - 10600 
		//Supervisor: 10700 - 10800
		//Monitor: 11000
		
		if(puertoEntrada == 11000) {  //Entrada del Monitor
				if(mensaje.equals("ping")) {  //Caso pingEcho
					this.servidor.getEstado().principal();
					reg = "pong";
					buffer = reg.getBytes();
					salida = new DatagramPacket(buffer, buffer.length,direccion,this.servidor.getPortMonitor());
					socketUDP.send(salida);
					
				
					ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
				    ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);
				    objectStream.writeObject(this.servidor.getGestionServidor());
			        objectStream.flush();
			        buffer = byteStream.toByteArray();
			        
			        System.out.println("Largo del buffer " + buffer.length);
				}if(mensaje.equals("cambio")) { //Caso en el que un servidor secundario pasa a ser el principal
				
				this.servidor.getEstado().principal();
				reg = "reemplazo";
				buffer = reg.getBytes();
				salida = new DatagramPacket(buffer, buffer.length,direccion,this.servidor.getPortMonitor());
				socketUDP.send(salida);
				//Mandarle al monitor que soy el nuevo jefe
				
				for (Map.Entry<Integer,String> entry : this.servidor.getGestionServidor().getConexiones().entrySet()) {
					reg = "cambio";
					buffer = reg.getBytes();
					salida = new DatagramPacket(buffer, buffer.length,direccion,entry.getKey());
					socketUDP.send(salida);
				}
			}
		} else if(puertoEntrada>= 10000 && puertoEntrada <10006 && puertoEntrada != this.servidor.getPort()) { //recibe datos de backup
		
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
