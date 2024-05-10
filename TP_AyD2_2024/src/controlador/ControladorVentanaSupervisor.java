package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Arrays;

import javax.swing.JOptionPane;

import java.io.ObjectInputStream;

import ventana.VentanaLogin;
import ventana.VentanaSupervisor;
import modelo.Estadisticas;

public class ControladorVentanaSupervisor implements ActionListener{

	private VentanaSupervisor ventanasupervisor; 
	private VentanaLogin vl;
	public boolean admin = true;
	private static ControladorVentanaSupervisor instancia = null;
	private InetAddress direccion; 
	private DatagramSocket socketUDP; 
	static byte[] buffer = new byte[1024];
	int portServidor = 10000;
	private Estadisticas estadisticas; 
	private String actualizar = "false";
	

	
	private ControladorVentanaSupervisor() { 		
		this.vl = new VentanaLogin();
		this.vl.setControladorSupervisor(this);
		this.vl.esperarBoton();
		
		while(!this.vl.getTextFieldUsuario().getText().equals("admin")) {
			JOptionPane.showMessageDialog(null, "Usuario y/o contraseña invalido");
			this.vl.dispose();
			this.vl = new VentanaLogin();
			this.vl.setControladorSupervisor(this);
			this.vl.esperarBoton();
		}
		
		try {
			
			int puerto = 10700;
			
			direccion = InetAddress.getByName("localHost");
			
			
			while(!puertoDisponible(puerto))
				puerto++;
			socketUDP = new DatagramSocket(puerto); 
			String reg = "Soy supervisor y me quiero conectar con el servidor";
			
			this.verificaServidor();
	
			
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
		recibeEstadisticas(socketUDP);
		
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
	

	public void recibeEstadisticas(DatagramSocket socketUDP) {
		
		    try {
		    	while (true) {
						 buffer = new byte[4096]; // Reservar buffer para recibir objeto //VER: chequear  valor buffer
						 DatagramPacket entrada = new DatagramPacket(buffer, buffer.length, direccion, portServidor);
				    	 socketUDP.receive(entrada);
				    	 socketUDP.setSoTimeout(0);
				    	 int puertoEntrada = entrada.getPort();
						InetAddress direccion = entrada.getAddress();
						
						if(puertoEntrada == portServidor) {
					    	 // Deserializar bytes recibidos en objeto Estadisticas
					    	 ByteArrayInputStream byteStream = new ByteArrayInputStream(entrada.getData());
					    	 ObjectInputStream objectStream = new ObjectInputStream(byteStream);
					    	 estadisticas = (Estadisticas) objectStream.readObject();
					    	 //System.out.println("Recibo estadisticas");
					    	 
					    	 this.ventanasupervisor.CargaEstadistica(estadisticas);
						}else if(puertoEntrada>portServidor && puertoEntrada <10011){
							this.portServidor = puertoEntrada;
						}
		    	 
		    	}	 
		  } catch (SocketTimeoutException e2) {
			  JOptionPane.showOptionDialog(null, "Servidor fuera de línea",null, JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, new Object[] { "Reintentar conexión" }, "Reintentar conexión");
			  this.socketUDP.close();
			  this.ventanasupervisor.dispose();
				
	        } 
		    catch (IOException | ClassNotFoundException e) {
		    e.printStackTrace();
		  }
		
	}
	
	public void actionPerformed(ActionEvent e) { //deberia conectarse con el servidor y enviar un "true" (hay que ver como sacar el string y poner un boolean o algo) diciendo que hay siguiente.
		if (e.getActionCommand().equalsIgnoreCase("Actualizar")) { 
			//System.out.println("Se apreto actualizar");
			actualizar = "trueActualizar";
			try {
				buffer = new byte[4096];
				Arrays.fill(buffer, (byte) 0);
				buffer = actualizar.getBytes();
				DatagramPacket salida = new DatagramPacket(buffer, buffer.length,direccion,portServidor);
				
				socketUDP.send(salida);				
				socketUDP.setSoTimeout(1000);
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
	
	public void verificaServidor() {
		boolean conseguimosServidor = false;
		String reg = "Hello there";
		InetAddress direccion;
		
			while(!conseguimosServidor && portServidor<10011) {
				try {
					conseguimosServidor = true;
					direccion = InetAddress.getByName("localHost");
					buffer = reg.getBytes();
					DatagramPacket salida = new DatagramPacket(buffer, buffer.length,direccion,portServidor);
					socketUDP.send(salida);
					socketUDP.setSoTimeout(1000);
					
					DatagramPacket entrada = new DatagramPacket(buffer,buffer.length);
					socketUDP.receive(entrada);
					socketUDP.setSoTimeout(0);
					JOptionPane.showMessageDialog(null, "Conectado al servidor"); 
				}catch (SocketTimeoutException e2) {
					System.out.println("Servidor no disponible");
					conseguimosServidor = false;
					portServidor++;
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
	}
	
}

