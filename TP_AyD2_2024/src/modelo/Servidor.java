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
import java.util.ArrayList;
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
		
		 
		try {
			DatagramSocket socketUDP = new DatagramSocket(port); 
			System.out.println("Servidor iniciado");
			
			while(true) {
				DatagramPacket entrada = new DatagramPacket(buffer, buffer.length);
				socketUDP.receive(entrada);
				
				String mensaje = new String(entrada.getData());
				int puertoEntrada = entrada.getPort();
				InetAddress direccion = entrada.getAddress();
				
				//conexiones.add(puertoEntrada);
				
				System.out.println(mensaje);
				
				//Totem: 10100 - 10200
				//Operadores: 10300 - 10400 
				//TV: 10500 - 10600 
				
				
				if(puertoEntrada >= 10100 && puertoEntrada <=10200) {
					if (!conexiones.containsKey(puertoEntrada)) {
						System.out.println("se establecio la conexion con: " + puertoEntrada);
						conexiones.put(puertoEntrada,"Totem");   
					}
				}else if(puertoEntrada >= 10300 && puertoEntrada <=10400) {
					if (!conexiones.containsKey(puertoEntrada)) {
						System.out.println("se establecio la conexion con: " + puertoEntrada);
						conexiones.put(puertoEntrada,"Operador");
					}
				}else if(puertoEntrada >= 10500 && puertoEntrada <=10600) {
					if (!conexiones.containsKey(puertoEntrada)) {
						System.out.println("se establecio la conexion con: " + puertoEntrada);
						conexiones.put(puertoEntrada,"TV"); 
					}
				}
		
				
				//System.out.println(referencia);
				
				
				
				//System.out.println(mensaje);
				//System.out.println(conexiones);
				
				System.out.println("----------------------------------------");
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 
	}
	
}