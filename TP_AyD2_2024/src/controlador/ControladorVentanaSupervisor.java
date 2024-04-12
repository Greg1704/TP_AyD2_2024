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
	private VentanaLogin ventanaLogin;
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
		  try {
		    buffer = new byte[1024]; // Reservar buffer para recibir objeto
		    DatagramPacket entrada = new DatagramPacket(buffer, buffer.length, direccion, portServidor);
		    socketUPD.receive(entrada);

		    // Deserializar bytes recibidos en objeto Estadisticas
		    ByteArrayInputStream bais = new ByteArrayInputStream(entrada.getData());
		    ObjectInputStream in = new ObjectInputStream(bais);
		    estadisticas = (Estadisticas) in.readObject();

		    // Acceder a los datos del objeto Estadisticas recibido
		    cantCliAtendidos = estadisticas.getCantCliAtentidos();
		    tiempoEsperaMin = estadisticas.getTiempoEsperaMin();
		    tiempoEsperaMax = estadisticas.getTiempoEsperaMax();

		    System.out.println("Estadísticas recibidas:");
		    System.out.println("  Clientes atendidos: " + cantCliAtendidos);
		    System.out.println("  Tiempo espera mínimo: " + tiempoEsperaMin);
		    System.out.println("  Tiempo espera máximo: " + tiempoEsperaMax);
		    
		  } catch (IOException | ClassNotFoundException e) {
		    e.printStackTrace();
		  }
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
	
	
}

