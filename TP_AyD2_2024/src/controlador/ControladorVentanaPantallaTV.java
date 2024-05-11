package controlador;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Arrays;

import javax.swing.JOptionPane;

import modelo.Turno;
import ventana.VentanaPantallaTV;

public class ControladorVentanaPantallaTV{

	private VentanaPantallaTV ventanaPantallaTV; 
	int portServidor = 10000;
	static byte[] buffer = new byte[1024]; 
	private DatagramSocket socketUDP;
	private static ControladorVentanaPantallaTV instancia = null;

	private ControladorVentanaPantallaTV() {
		
		try {
			
			int puerto = 10500;
			
			InetAddress direccion = InetAddress.getByName("localHost");
			
			
			while(!puertoDisponible(puerto))
				puerto++;
			socketUDP = new DatagramSocket(puerto); 
			String reg = "Soy un televisor y me quiero conectar con el servidor";
			
			Arrays.fill(buffer, (byte) 0);
			this.verificaServidor();
			
			this.ventanaPantallaTV = new VentanaPantallaTV();
			this.ventanaPantallaTV.setControlador(this);
			this.esperandoNotificaciones(socketUDP);
		
			
		} 
		catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
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
			socketUDP.receive(entrada);
			
			//Turno turno = entrada.getClass(); 
			
			//muestraTurno(entrada);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //recibe dni
	}**/
	
	public void esperandoNotificaciones(DatagramSocket socketUDP) {
		// TODO Auto-generated method stub
		while(true) {
			byte[] buffer2 = new byte[2048];
			DatagramPacket entrada = new DatagramPacket(buffer2, buffer2.length);
			try {
				socketUDP.receive(entrada);
				
				String mensaje = new String(entrada.getData());
				mensaje = mensaje.trim();
				int puertoEntrada = entrada.getPort();
				
				if(mensaje.equals("cambio")) {
					if(puertoEntrada>portServidor && puertoEntrada <10011){
						this.portServidor = puertoEntrada;
					}
				}else {
					ByteArrayInputStream byteStream = new ByteArrayInputStream(entrada.getData());
		            ObjectInputStream objectStream = new ObjectInputStream(byteStream);
		            Turno t = (Turno) objectStream.readObject();				
					if(puertoEntrada == portServidor) {
						this.ventanaPantallaTV.actualizaTurnos(t);
					}
				}
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void muestraTurno(Turno turno) {
		this.ventanaPantallaTV.actualizaTurnos(turno);

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
			if(portServidor == 10011) {
				JOptionPane.showMessageDialog(null, "No hay servidores disponibles a los que conectarse"); 
				System.exit(0);
			}
		
	}
	
	
	
	
	
	
	
}