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
import java.util.Arrays;

import javax.swing.JOptionPane;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import ventana.VentanaLoginDefinitiva;
import ventana.VentanaSupervisor;
import modelo.Estadisticas;

public class ControladorVentanaSupervisor implements ActionListener{

	private VentanaSupervisor ventanasupervisor; 
	private VentanaLoginDefinitiva vl;
	public boolean admin = true;
	private static ControladorVentanaSupervisor instancia = null;
	private InetAddress direccion; 
	private DatagramSocket socketUPD; 
	static byte[] buffer = new byte[1024];
	final static int portServidor = 10000;
	private Estadisticas estadisticas; 
	private String actualizar = "false";
	

	
	private ControladorVentanaSupervisor() { 		
		this.vl = new VentanaLoginDefinitiva();
		this.vl.setControladorSupervisor(this);
		this.vl.esperarBoton();
		
		while(!this.vl.getTextFieldUsuario().getText().equals("admin")) {
			JOptionPane.showMessageDialog(null, "Usuario y/o contraseña invalido");
			this.vl.dispose();
			this.vl = new VentanaLoginDefinitiva();
			this.vl.setControladorSupervisor(this);
			this.vl.esperarBoton();
		}
		
		try {
			
			int puerto = 10700;
			
			direccion = InetAddress.getByName("localHost");
			
			
			while(!puertoDisponible(puerto))
				puerto++;
			socketUPD = new DatagramSocket(puerto); 
			String reg = "Soy supervisor y me quiero conectar con el servidor";
			
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
		this.vl.dispose();
		this.ventanasupervisor = new VentanaSupervisor();
		this.ventanasupervisor.setControlador(this);
		recibeEstadisticas(socketUPD);
		
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
	

	public void recibeEstadisticas(DatagramSocket socketUPD) {
		while (true) {
			buffer = new byte[4096]; // Reservar buffer para recibir objeto //VER: chequear  valor buffer
		    DatagramPacket entrada = new DatagramPacket(buffer, buffer.length, direccion, portServidor);
		    try {
		    	 socketUPD.receive(entrada);
		    	 
		    	 // Deserializar bytes recibidos en objeto Estadisticas
		    	 ByteArrayInputStream byteStream = new ByteArrayInputStream(entrada.getData());
		    	 ObjectInputStream objectStream = new ObjectInputStream(byteStream);
		    	 estadisticas = (Estadisticas) objectStream.readObject();
		    	 System.out.println("Recibo estadisticas");
		    	 
		    	 this.ventanasupervisor.CargaEstadistica(estadisticas);
		    	 
		    	 
		  } catch (IOException | ClassNotFoundException e) {
		    e.printStackTrace();
		  }
		}
	}
	
	public void actionPerformed(ActionEvent e) { //deberia conectarse con el servidor y enviar un "true" (hay que ver como sacar el string y poner un boolean o algo) diciendo que hay siguiente.
		if (e.getActionCommand().equalsIgnoreCase("Actualizar")) { 
			System.out.println("Se apreto actualizar");
			actualizar = "trueActualizar";
			try {
				buffer = new byte[4096];
				Arrays.fill(buffer, (byte) 0);
				buffer = actualizar.getBytes();
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
		Arrays.fill(buffer, (byte) 0);
	}
	
	
	
}

