package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import ventana.VentanaOperador;


public class ControladorVentanaOperador implements ActionListener{

	private VentanaOperador ventanaOperador; 
	private String siguiente = "false";
	private InetAddress direccion; 
	private DatagramSocket socketUPD; 
	final int portServidor = 10000;
	private static ControladorVentanaOperador instancia = null;
	
	public void iniciaConexion() { 
		try {
			direccion = InetAddress.getByName("localHost");
			socketUPD = new DatagramSocket();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
	}
	
	private ControladorVentanaOperador() {
		super();
		this.ventanaOperador = new VentanaOperador();
		this.ventanaOperador.setControlador(this);
	}
	
	public static ControladorVentanaOperador getInstancia() {
		if (instancia == null)
			instancia = new ControladorVentanaOperador();
		return instancia;
	}

	public void actionPerformed(ActionEvent e) { //deberia conectarse con el servidor y enviar un "true" (hay que ver como sacar el string y poner un boolean o algo) diciendo que hay siguiente.
		if (e.getActionCommand().equalsIgnoreCase("Llamar siguiente")) { 
			byte[] buffer = new byte[1024]; 
			siguiente = "true";
			
			try {
				buffer = siguiente.getBytes();
				DatagramPacket salida = new DatagramPacket(buffer, buffer.length,direccion,portServidor);
				
				socketUPD.send(salida);
				
			
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
}

