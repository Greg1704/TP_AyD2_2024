package controlador;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import modelo.Monitor;
import ventana.VentanaLogin;
import ventana.VentanaOperador;

public class ControladorVentanaMonitor {

	private InetAddress direccion; 
	private DatagramSocket socketUPD;
	static byte[] buffer = new byte[1024];
	final int portMonitor = 11000;
	String reg;
	
	
	private void controladorVentanaMonitor() {
		DatagramSocket socketUDP;
		try {
			socketUDP = new DatagramSocket(portMonitor);
			while(true) {
				byte[] buffer = new byte[1024];	
				byte[] buffer_Est = new byte[5120];	
				
				DatagramPacket entrada = new DatagramPacket(buffer, buffer.length);
				socketUDP.receive(entrada);
				
				String mensaje = new String(entrada.getData());
				mensaje = mensaje.trim();
				int puertoEntrada = entrada.getPort();
				InetAddress direccion = entrada.getAddress();
				
				//conexiones.add(puertoEntrada);
				
				System.out.println("Largo del mensaje  " + mensaje.length());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
