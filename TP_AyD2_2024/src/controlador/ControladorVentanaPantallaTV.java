package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import modelo.PantallaTV;
import modelo.Turno;
import ventana.VentanaPantallaTV;

public class ControladorVentanaPantallaTV{

	private VentanaPantallaTV ventanaPantallaTV; 
	private String dni; 
	final static int portServidor = 10000;
	static byte[] buffer = new byte[1024]; 
	private DatagramSocket socketUPD;
	private InetAddress direccion;
	private static ControladorVentanaPantallaTV instancia = null;
	private PantallaTV tv;

	private ControladorVentanaPantallaTV() {
		
		try {
			
			int puerto = 10500;
			
			InetAddress direccion = InetAddress.getByName("localHost");
			
			
			while(!puertoDisponible(puerto))
				puerto++;
			socketUPD = new DatagramSocket(puerto); 
			String reg = "Soy un televisor y me quiero conectar con el servidor";
			
			Arrays.fill(buffer, (byte) 0);
			buffer = reg.getBytes();
			DatagramPacket salida = new DatagramPacket(buffer, buffer.length,direccion,portServidor);
			socketUPD.send(salida);
			
		
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		this.ventanaPantallaTV = new VentanaPantallaTV();
		this.ventanaPantallaTV.setControlador(this);
		this.dni = "";
		this.tv = new PantallaTV();
		tv.esperandoNotificaciones(socketUPD);
	}
	
	public static ControladorVentanaPantallaTV getInstancia() {
		if (instancia == null) 
			instancia = new ControladorVentanaPantallaTV();
		return instancia;
	}
	
	public static boolean puertoDisponible(int puerto) {
        try {
            DatagramSocket socket = new DatagramSocket(puerto);
            socket.close(); 
            return true; 
        } catch (SocketException e) {
            return false; 
        }
    }

	
	/**public void recibeDNI() { 
		try {
			buffer = dni.getBytes();
			DatagramPacket entrada = new DatagramPacket(buffer, buffer.length,direccion,portServidor);
			socketUPD.receive(entrada);
			
			//Turno turno = entrada.getClass(); 
			
			//muestraTurno(entrada);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //recibe dni
	}**/
	
	public void muestraTurno(Turno turno) {
		this.ventanaPantallaTV.agregaTurno(turno);

	}
	
	
	
	
	
	
	
}