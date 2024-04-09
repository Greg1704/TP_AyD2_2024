package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;

import ventana.VentanaTotem;

public class ControladorVentanaTotem implements ActionListener{
	public VentanaTotem ventanaTotem;
	final static int portServidor = 10000;
	static byte[] buffer = new byte[1024];
	private static ControladorVentanaTotem instancia = null;
	
	//hay que poner un action listener para que cuando se apriere el solicitar turno se mande esto al servidor
	//tambien habria que ver para que el servidor no se cierre y abra cada vez que se apriete el boton
	
	private ControladorVentanaTotem() { 
		
		try {
			InetAddress direccion = InetAddress.getByName("localHost");
			
			DatagramSocket socketUPD = new DatagramSocket(); 
			
			String reg = "RT " + "1234";
			
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
		
		
		this.ventanaTotem = new VentanaTotem();
		this.ventanaTotem.setControlador(this);
		this.ventanaTotem.setActionListener(this);
		
	}
	
	public static ControladorVentanaTotem getInstancia() {
		if (instancia == null)
			instancia = new ControladorVentanaTotem();
		return instancia;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equalsIgnoreCase("Confirmar")) {
			String dni = this.ventanaTotem.getDni();
			if (dni.length() == 8) {
				this.ventanaTotem.setDni("");
				JOptionPane.showMessageDialog(null, "DNI recibido"); //Se podria poner "Su dni ha sido enviado a la cola de espera" por formalizarlo un poco idk
				InetAddress direccion;
				try {
					direccion = InetAddress.getByName("localHost");
					DatagramSocket socketUPD = new DatagramSocket(); 
					dni = "DNIT " + dni;
					buffer = dni.getBytes();
					DatagramPacket salida = new DatagramPacket(buffer, buffer.length,direccion,portServidor);
					socketUPD.send(salida);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else { 
				this.ventanaTotem.errorLargo();
			}
		}
	}

}