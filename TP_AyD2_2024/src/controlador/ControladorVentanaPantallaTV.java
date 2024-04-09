package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

import ventana.VentanaPantallaTV;

public class ControladorVentanaPantallaTV{

	private VentanaPantallaTV ventanaPantallaTV; 
	private String dni; 
	final static int portServidor = 10000;
	static byte[] buffer = new byte[1024]; 
	private DatagramSocket socketUPD;
	private InetAddress direccion;
	private static ControladorVentanaPantallaTV instancia = null;

	private ControladorVentanaPantallaTV() {
		super();
		this.ventanaPantallaTV = new VentanaPantallaTV();
		this.ventanaPantallaTV.setControlador(this);
		this.dni = "";
	}
	
	public static ControladorVentanaPantallaTV getInstancia() {
		if (instancia == null)
			instancia = new ControladorVentanaPantallaTV();
		return instancia;
	}
	
	public void iniciaConexion() { 
		try {
			direccion = InetAddress.getByName("localHost");	
			socketUPD = new DatagramSocket(); 
			
			
		
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void recibeDNI() { 
		try {
			buffer = dni.getBytes();
			DatagramPacket entrada = new DatagramPacket(buffer, buffer.length,direccion,portServidor);
			socketUPD.receive(entrada);
			
			dni = new String(entrada.getData());
			System.out.println(dni);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //recibe dni
	}
	

}