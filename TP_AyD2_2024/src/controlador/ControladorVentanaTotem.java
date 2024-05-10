package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import ventana.VentanaTotem;


public class ControladorVentanaTotem implements ActionListener{
	public VentanaTotem ventanaTotem;
	int portServidor = 10000;
	static byte[] buffer = new byte[1024];
	private static ControladorVentanaTotem instancia = null;
	DatagramSocket socketUPD;
	
	//hay que poner un action listener para que cuando se apriere el solicitar turno se mande esto al servidor
	//tambien habria que ver para que el servidor no se cierre y abra cada vez que se apriete el boton
	
	private ControladorVentanaTotem() { 
		
		try {
			
			int puerto = 10100;
			
			InetAddress direccion = InetAddress.getByName("localHost");
			
			
			while(!puertoDisponible(puerto))
				puerto++;
			socketUPD = new DatagramSocket(puerto); 
			String reg = "Soy un totem y me quiero conectar con el servidor";
			
			Arrays.fill(buffer, (byte) 0);
			buffer = reg.getBytes();
			DatagramPacket salida = new DatagramPacket(buffer, buffer.length,direccion,portServidor);
			socketUPD.send(salida);
			socketUPD.setSoTimeout(1000);
			
			DatagramPacket entrada = new DatagramPacket(buffer,buffer.length);
			socketUPD.receive(entrada);
			socketUPD.setSoTimeout(0);
			JOptionPane.showMessageDialog(null, "Conectado al servidor"); 
			
			
			this.ventanaTotem = new VentanaTotem();
			this.ventanaTotem.setControlador(this);
			this.ventanaTotem.setActionListener(this);
			
			
			esperandoNotificaciones(socketUPD);
			
		}catch (SocketTimeoutException e2) {
			JOptionPane.showMessageDialog(null, "Servidor fuera de linea"); 
        }  
		catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	public static ControladorVentanaTotem getInstancia() {
		if (instancia == null)
			instancia = new ControladorVentanaTotem();
		return instancia;
	}
	
	
	public void esperandoNotificaciones(DatagramSocket socketUDP) {  //TENGO QUE UNIFICAR TODOS LOS RECEIVE(ENTRADA ACA PORQUE SINO SE ROMPE TODO)
		// TODO Auto-generated method stub
		while(true) {
			byte[] buffer2 = new byte[2048];
			DatagramPacket entrada = new DatagramPacket(buffer2, buffer2.length);
			try {
				socketUDP.receive(entrada);
				socketUPD.setSoTimeout(0);
				String mensaje = new String(entrada.getData());
				mensaje = mensaje.trim();
				int puertoEntrada = entrada.getPort();
				//InetAddress direccion = entrada.getAddress();
				//System.out.println(t);
				
				
				if(mensaje.equals("cambio")){
					System.out.println("Se actualizo el puerto :D");
					this.portServidor = puertoEntrada;
				}else if(mensaje.equals("Recibido")) {
					JOptionPane.showMessageDialog(null, "DNI recibido"); 
				}
			}catch (SocketTimeoutException e2) {
				JOptionPane.showOptionDialog(null, "Servidor fuera de línea",null, JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, new Object[] { "Reintentar conexión" }, "Reintentar conexión");
	        } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equalsIgnoreCase("Confirmar")) {
			String dni = this.ventanaTotem.getDni();
			if (dni.length() == 8) {
				this.ventanaTotem.setDni("");
				InetAddress direccion;
				try {
					direccion = InetAddress.getByName("localHost");
					//DatagramSocket socketUPD = new DatagramSocket(); 
					//dni = "DNIT " + dni;
					//System.out.println(dni);
					buffer = dni.getBytes();
					DatagramPacket salida = new DatagramPacket(buffer, buffer.length,direccion,portServidor);
					socketUPD.send(salida);
					socketUPD.setSoTimeout(2000);

				}catch (SocketTimeoutException e2) {
					JOptionPane.showOptionDialog(null, "Servidor fuera de línea",null, JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, new Object[] { "Reintentar conexión" }, "Reintentar conexión");
		        } 
				catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else { 
				this.ventanaTotem.errorLargo();
			}
		}
		Arrays.fill(buffer, (byte) 0);
	}

}