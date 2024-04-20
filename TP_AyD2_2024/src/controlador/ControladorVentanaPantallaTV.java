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
	final static int portServidor = 10000;
	static byte[] buffer = new byte[1024]; 
	private DatagramSocket socketUPD;
	private static ControladorVentanaPantallaTV instancia = null;

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
			socketUPD.setSoTimeout(1000);
			
			DatagramPacket entrada = new DatagramPacket(buffer, buffer.length);
			socketUPD.receive(entrada);
			socketUPD.setSoTimeout(0);
			
			this.ventanaPantallaTV = new VentanaPantallaTV();
			this.ventanaPantallaTV.setControlador(this);
			this.esperandoNotificaciones(socketUPD);
		
			
		}catch (SocketTimeoutException e2) {
			JOptionPane.showMessageDialog(null, "Servidor fuera de linea");
			this.socketUPD.close();
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
			socketUPD.receive(entrada);
			
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
				
				ByteArrayInputStream byteStream = new ByteArrayInputStream(entrada.getData());
	            ObjectInputStream objectStream = new ObjectInputStream(byteStream);
	            Turno t = (Turno) objectStream.readObject();
				
				/*String mensaje = new String(entrada.getData());
				mensaje = mensaje.trim();*/
				int puertoEntrada = entrada.getPort();
				//InetAddress direccion = entrada.getAddress();
				//System.out.println(t);
				
				if(puertoEntrada == 10000) {
					this.ventanaPantallaTV.actualizaTurnos(t);
				}else {
					//System.out.println("Puerto no habilitado");
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
	
	
	
	
	
	
	
}