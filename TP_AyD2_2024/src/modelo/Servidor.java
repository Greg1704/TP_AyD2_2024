package modelo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.Executors;

public class Servidor {

	public static void main(String[] args) { 
		ServerSocket servidor = null; 
		Socket sc = null;
		DataInputStream in;
		DataOutputStream out;
		final int Port = 10000; 
		ArrayList<Integer> conexiones = new ArrayList<>(); 
		
		
		 
		try {
			servidor = new ServerSocket();
			System.out.println("Servidor inciado");
			
			while(true) { //espera a que alguien se una
				sc = servidor.accept();
				
				in = new DataInputStream(sc.getInputStream());
				out = new DataOutputStream(sc.getOutputStream());
				
				String mensaje = in.readUTF(); 
				System.out.println(mensaje);
				
				out.writeUTF("Turno recibido");
				
				sc.close();
				System.out.println("Cliente desconectado");
				
			}
			
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 
	}
	
}