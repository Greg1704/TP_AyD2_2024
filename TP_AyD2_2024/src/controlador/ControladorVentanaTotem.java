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
	DatagramSocket socketUDP;
	int puerto = 10100;
	boolean envio = false;
	int reintento = 2;
	
	//hay que poner un action listener para que cuando se apriere el solicitar turno se mande esto al servidor
	//tambien habria que ver para que el servidor no se cierre y abra cada vez que se apriete el boton
	
	private ControladorVentanaTotem() { 
		
		try {
			
			InetAddress direccion = InetAddress.getByName("localHost");
			
		
			while(!puertoDisponible(puerto))
				puerto++;
			socketUDP = new DatagramSocket(puerto); 
			String reg = "Soy un totem y me quiero conectar con el servidor";
			
			Arrays.fill(buffer, (byte) 0);
			this.verificaServidor();
			
			
			this.ventanaTotem = new VentanaTotem();
			this.ventanaTotem.setControlador(this);
			this.ventanaTotem.setActionListener(this);
			
			
			esperandoNotificaciones();
		
	    }catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
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
	
	
	public void esperandoNotificaciones() {  //TENGO QUE UNIFICAR TODOS LOS RECEIVE(ENTRADA ACA PORQUE SINO SE ROMPE TODO)
		// TODO Auto-generated method stub
		while(true) {
			byte[] buffer2 = new byte[2048];
			DatagramPacket entrada = new DatagramPacket(buffer2, buffer2.length);
			if (!envio) {
				try {
					//System.out.println(socketUDP.getSoTimeout());
					socketUDP.setSoTimeout(500);
					socketUDP.receive(entrada);
					socketUDP.setSoTimeout(0);
					
					String mensaje = new String(entrada.getData());
					mensaje = mensaje.trim();
					int puertoEntrada = entrada.getPort();
					//InetAddress direccion = entrada.getAddress();
					
					System.out.println("carambola");
					
					
					if(mensaje.equals("cambio")){
						System.out.println("Se actualizo el puerto :D");
						this.portServidor = puertoEntrada;
					}
				}catch (SocketTimeoutException e2) {
					//this.esperandoNotificaciones();
		        } catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
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
					//DatagramSocket socketUDP = new DatagramSocket(); 
					//dni = "DNIT " + dni;
					//System.out.println(dni);
					buffer = dni.getBytes();
					DatagramPacket salida = new DatagramPacket(buffer, buffer.length,direccion,portServidor);
					socketUDP.send(salida);
					socketUDP.setSoTimeout(1000);
					envio = true; 
					//System.out.println(socketUDP.getSoTimeout()); 
					DatagramPacket entrada = new DatagramPacket(buffer, buffer.length);
					socketUDP.receive(entrada);
					socketUDP.setSoTimeout(0);
					envio = false;
					JOptionPane.showMessageDialog(null, "DNI recibido"); 
					

				}catch (SocketTimeoutException e2) {
					int result = JOptionPane.showOptionDialog(null, "Servidor fuera de línea",null, JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, new Object[] { "Reintentar conexión" }, "Reintentar conexión");
					if (result == 0 && this.reintento != 0) { 
						 ActionEvent eventoSimulado = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Confirmar");
						 actionPerformed(eventoSimulado);
						 this.reintento = this.reintento - 1;
					}else {
						envio = false;
						JOptionPane.showMessageDialog(null, "Reintentos fallidos, vuelva a reintentar en unos segundos o cierre la ventana"); 
					}
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