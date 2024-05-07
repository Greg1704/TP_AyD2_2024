package modelo;

import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;

public class pruebafalopaGrego {

	public static void main(String[] args) { 
		try {
			DatagramSocket socketUDP = new DatagramSocket(10000);
			socketUDP.close();
			socketUDP = new DatagramSocket(10000);
			System.out.println("Calabaza");
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		
		
	}
	
}
