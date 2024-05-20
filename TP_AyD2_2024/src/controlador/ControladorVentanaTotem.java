package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Arrays;

import javax.swing.JOptionPane;

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
	boolean falloReintento = false;
	
	//hay que poner un action listener para que cuando se apriere el solicitar turno se mande esto al servidor
	//tambien habria que ver para que el servidor no se cierre y abra cada vez que se apriete el boton
	
	private ControladorVentanaTotem() { 
		
		try {
			
			
		
			while(!puertoDisponible(puerto))
				puerto++;
			socketUDP = new DatagramSocket(puerto); 
			
			Arrays.fill(buffer, (byte) 0);
			this.verificaServidor();
			
			
			this.ventanaTotem = new VentanaTotem();
			this.ventanaTotem.setControlador(this);
			this.ventanaTotem.setActionListener(this);
			
			
			esperandoNotificaciones();
		
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
			System.out.println(envio);
			if (!envio) {
				try {
					//System.out.println(socketUDP.getSoTimeout());
					System.out.println("Estoy entrando");
					socketUDP.setSoTimeout(500);
					socketUDP.receive(entrada);
					socketUDP.setSoTimeout(0);
					
					String mensaje = new String(entrada.getData());
					mensaje = mensaje.trim();
					int puertoEntrada = entrada.getPort();
					//InetAddress direccion = entrada.getAddress();
					
					
					
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
			String fechaNacimiento = this.ventanaTotem.getFechaNacimiento();
			
			//HABRIA QUE IMPLEMENTAR LA LOGICA EN EL IF PARA PASAR EL DATO Y LUEGO CREAR EL CLIENTE PARA ENVIARLO POR EL SOCKET
			
			if (dni.length() == 8) {
				
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
					this.ventanaTotem.setDni("");
					this.reintento = 2;

				}catch (SocketTimeoutException e2) {
					int result = JOptionPane.showOptionDialog(null, "Servidor fuera de línea",null, JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, new Object[] { "Reintentar conexión" }, "Reintentar conexión");
					if (result == 0) {
						if (this.reintento > 0) {
							reintento = reintento - 1;
							envio = false;
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							ActionEvent eventoSimulado = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Confirmar");
							actionPerformed(eventoSimulado);
							 
						}else {
							this.reintento = 2;
							this.ventanaTotem.setDni("");
							JOptionPane.showMessageDialog(null, "Reintentos fallidos, vuelva a reintentar en unos segundos o cierre la ventana"); 
							this.falloReintento = true;
							this.verificaServidor();
							this.envio = false;
							this.falloReintento = false;
							
						}
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
		int servidorAevadir = 9999;
		if(falloReintento) {
			 servidorAevadir = this.portServidor;
			 this.portServidor = 10000;
		}
		
		while(!conseguimosServidor && this.portServidor<10011) {
			if(portServidor != servidorAevadir) {
				try {
					conseguimosServidor = true;
					direccion = InetAddress.getByName("localHost");
					buffer = reg.getBytes();
					DatagramPacket salida = new DatagramPacket(buffer, buffer.length,direccion,this.portServidor);
					socketUDP.send(salida);
					socketUDP.setSoTimeout(1000);
					
					DatagramPacket entrada = new DatagramPacket(buffer,buffer.length);
					socketUDP.receive(entrada);
					socketUDP.setSoTimeout(0);
					JOptionPane.showMessageDialog(null, "Conectado al servidor  "); 
				}catch (SocketTimeoutException e2) {
					System.out.println("Servidor no disponible");
					conseguimosServidor = false;
					this.portServidor++;
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				this.portServidor++;
			}
		}
		if(this.portServidor == 10011) {
			JOptionPane.showMessageDialog(null, "No hay servidores disponibles a los que conectarse"); 
			System.exit(0);
		}
		
	}

}