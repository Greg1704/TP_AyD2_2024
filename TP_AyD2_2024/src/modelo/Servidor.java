package modelo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
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
import java.util.concurrent.Executors;

public class Servidor {

	public static void main(String[] args) { 
		ServerSocket servidor = null; 
		Socket sc = null;
	
		final int port = 10000; 
		byte[] buffer = new byte[1024];
		
		//ArrayList<Integer> conexiones = new ArrayList<>(); 
		HashMap<Integer, String> conexiones = new HashMap<>();
		GestionDeTurnos gdt = new GestionDeTurnos();
		ArrayList<Integer> boxesOcupados = new ArrayList<Integer>();
		
		 
		try {
			DatagramSocket socketUDP = new DatagramSocket(port); 
			System.out.println("Servidor iniciado");
			
			while(true) {
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
						if(boxesOcupados.contains(box)) {
							box = 1;
							while(boxesOcupados.contains(box))
								box++;
						}
						boxesOcupados.add(box);
						Arrays.fill(buffer, (byte) 0);
						String reg = Integer.toString(box);
						buffer = reg.getBytes();
						DatagramPacket salida = new DatagramPacket(buffer, buffer.length,direccion,puertoEntrada);
						socketUDP.send(salida);
						
					}else if(mensaje.equals("acepto")){ //Caso confirmacion de llegada del cliente al box
						System.out.println("El cliente vino al box papa");
					}else{ //Caso en el que el operador solicita un nuevo cliente para que vaya al box
						Turno t = gdt.extraerPrimerTurno();
						//esperando a nahue :D
					}
				}else if(puertoEntrada >= 10500 && puertoEntrada <=10600) { //Entrada de las Pantallas TV
					if (!conexiones.containsKey(puertoEntrada)) { //Caso en el que el puerto no sea reconocido por el sistema
						System.out.println("se establecio la conexion con: " + puertoEntrada);
						conexiones.put(puertoEntrada,"TV"); 
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