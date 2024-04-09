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
				
				String[] partes = mensaje.split(" ");
				String referencia = partes[0];
				String relevante = partes[1];
				
				System.out.println(mensaje);
				System.out.println(relevante);
				
				if(referencia.equals("RT")) {
					conexiones.put(puertoEntrada,"T");   //Asumo que relevante seria el puerto del totem
					System.out.println("se establecio la conexion con: " + puertoEntrada);
				}else if(referencia.equals("DNIT")) {
					//System.out.println("Entraste donde querias");
					gdt.añadirTurno(relevante);
					//gdt.mostrarCola();
				}else if(referencia.equals("RBOXO")) {
					conexiones.put(puertoEntrada,"O");   //Asumo que relevante seria el puerto del box
					System.out.println("se establecio la conexion con: " + puertoEntrada);
				}else if(referencia.equals("LOGBOXO")) {
					
				}else if(referencia.equals("SOLIBOXO")) {
					
				}else if(referencia.equals("SOLICLO")) {
					
				}else if(referencia.equals("OKCLO")) {
					
				}else if(referencia.equals("RPP")) {
					conexiones.put(puertoEntrada,"P");   //Asumo que relevante seria el puerto de la pantallaTV
					System.out.println("se establecio la conexion con: " + puertoEntrada);
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