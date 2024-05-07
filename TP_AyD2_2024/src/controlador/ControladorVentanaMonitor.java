package controlador;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.HashMap;

import javax.swing.JOptionPane;

import modelo.Monitor;
import ventana.VentanaLogin;
import ventana.VentanaOperador;

public class ControladorVentanaMonitor {

	private InetAddress direccion; 
	private DatagramSocket socketUDP;
	static byte[] buffer = new byte[1024];
	final int portMonitor = 11000;
	String reg;
	private HashMap<Integer, Boolean> servidoresDisp = new HashMap<>();
	
	
	private void controladorVentanaMonitor() {
		//DatagramSocket socketUDP;
		try {
			socketUDP = new DatagramSocket(portMonitor);
			
			this.esperandoNotificaciones(socketUDP);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void esperandoNotificaciones(DatagramSocket socketUDP) {
		// TODO Auto-generated method stub

		
			try {
				while(true) {
					buffer = new byte[1024];
					DatagramPacket entrada = new DatagramPacket(buffer, buffer.length);
					socketUDP.receive(entrada);
					
					String mensaje = new String(entrada.getData());
					mensaje = mensaje.trim();
					int puertoEntrada = entrada.getPort();
					InetAddress direccion = entrada.getAddress();
					
					if(puertoEntrada >= 10000 && puertoEntrada <= 10010) {
						if(!this.servidoresDisp.containsKey(puertoEntrada)) {
							if(puertoEntrada == 10000)
								this.servidoresDisp.put(puertoEntrada, true);
							else
								this.servidoresDisp.put(puertoEntrada, false);
						}
					}else {
						//System.out.println("Puerto no habilitado");
					}
				}
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Arrays.fill(buffer, (byte) 0);
		
	}
	
}
