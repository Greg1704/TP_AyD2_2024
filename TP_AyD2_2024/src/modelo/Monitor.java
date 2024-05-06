package modelo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;

import controlador.ControladorVentanaOperador;


public class Monitor {
	private Boolean estado;
	private DatagramSocket socketUPD;
	static byte[] buffer = new byte[1024];
	final int portServidor = 10000;
	private static ControladorVentanaOperador instancia = null;
	private int portMonitor = 11000; 
	private HashMap<Integer, Boolean> servidoresDisp = new HashMap<>();  //<Port,estado>
	
	
	
	private void Monitor() { 
		try {
			
			direccion = InetAddress.getByName("localHost");
			
			
			socketUPD = new DatagramSocket(portMonitor); 
			
			buffer = reg.getBytes();
			DatagramPacket salida = new DatagramPacket(buffer, buffer.length,direccion,portServidor);
			
			socketUPD.send(salida);
			socketUPD.setSoTimeout(1000);
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}
