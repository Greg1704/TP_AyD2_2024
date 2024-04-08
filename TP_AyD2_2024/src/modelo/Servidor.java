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
import java.util.concurrent.Executors;

public class Servidor {

	public static void main(String[] args) { 
		ServerSocket servidor = null; 
		Socket sc = null;
	
		final int port = 10000; 
		byte[] buffer = new byte[1024];
		
		ArrayList<Integer> conexiones = new ArrayList<>(); 
		
		
		 
		try {
			DatagramSocket socketUDP = new DatagramSocket(port); 
			System.out.println("Servidor inciado");
			
			while(true) {
				DatagramPacket entrada = new DatagramPacket(buffer, buffer.length);
				socketUDP.receive(entrada);
				
				String dni = new String(entrada.getData());
				int puertoEntrada = entrada.getPort();
				InetAddress direccion = entrada.getAddress();
				
				conexiones.add(puertoEntrada);
				System.out.println("se establecio la conexion con: " + puertoEntrada);
				
				System.out.println(dni);
				System.out.println(conexiones);
				
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 
	}
	
}