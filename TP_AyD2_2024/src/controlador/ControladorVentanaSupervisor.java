package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;


import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


import javax.swing.JOptionPane;

import ventana.VentanaLogin;
import ventana.VentanaSupervisor;
import ventana.VentanaTotem;
import modelo.Estadisticas;

public class ControladorVentanaSupervisor implements ActionListener{

	private VentanaSupervisor ventanasupervisor; 
	private VentanaLogIn ventanaLogin;
	public boolean admin;
	private static ControladorVentanaSupervisor instancia = null;
	private InetAddress direccion; 
	private DatagramSocket socketUPD; 
	static byte[] buffer = new byte[1024];
	final static int portServidor = 10000;
	private Estadisticas estadisticas; 
	
	public int cantCliAtendidos;
	public float tiempoEsperaMin;
	public float tiempoEsperaMax;
	public float tiempoEsperaProm;
	
	private ControladorVentanaSupervisor() { 
		this.ventanaLogin = new VentanaLogin(); 
		this.ventanaLogin.setControladorSupervisor(this);
		
		if (admin) {
			this.ventanaLogin.frame.setVisible(false);
			try {
				
				int puerto = 10700;
				
				InetAddress direccion = InetAddress.getByName("localHost");
				
				
				while(!puertoDisponible(puerto))
					puerto++;
				socketUPD = new DatagramSocket(puerto); 
				String reg = "1111";
				
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
			
			
			
			this.ventanasupervisor = new VentanaSupervisor();
			this.ventanasupervisor.setControlador(this);
		}
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
	
	
	public static ControladorVentanaSupervisor getInstancia() {
		if (instancia == null)
			instancia = new ControladorVentanaSupervisor();
		return instancia;
	}
	
	//Para recibir los datos de la clase estadistica
	public void recibeEstadisticas() {
		while (true) {
			buffer = new byte[4096]; // Reservar buffer para recibir objeto //VER: chequear  valor buffer
		    DatagramPacket entrada = new DatagramPacket(buffer, buffer.length, direccion, portServidor);
		    try {
		    	 socketUPD.receive(entrada);

		    	 // Deserializar bytes recibidos en objeto Estadisticas
		    	 ByteArrayInputStream byteStream = new ByteArrayInputStream(entrada.getData());
		    	 ObjectInputStream objectStream = new ObjectInputStream(byteStream);
		    	 estadisticas = (Estadisticas) objectStream.readObject();

		    	 this.ventanasupervisor.CargaEstadistica(estadisticas);
		    	 
		    	 
		  } catch (IOException | ClassNotFoundException e) {
		    e.printStackTrace();
		  }
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
	
	
}

