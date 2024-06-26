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
	boolean envio = false;
	private int reintento = 2;
	boolean falloReintento = false;

	

	
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
			
			this.verificaServidor();
	
			this.ventanasupervisor = new VentanaSupervisor();
			this.ventanasupervisor.setControlador(this);
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
		
		    	while (true) {
					System.out.println(envio);
		    		if(!envio) {
			    		try {
							 buffer = new byte[4096]; // Reservar buffer para recibir objeto //VER: chequear  valor buffer
							 DatagramPacket entrada = new DatagramPacket(buffer, buffer.length, direccion, portServidor);
							 socketUDP.setSoTimeout(500);
					    	 socketUDP.receive(entrada);
					    	 socketUDP.setSoTimeout(0);
					    	 int puertoEntrada = entrada.getPort();
					    	 
					    	 String mensaje = new String(entrada.getData());
							 mensaje = mensaje.trim();
							
							 if(mensaje.equals("cambio")){
								System.out.println("Vi el nuevo server");
								this.portServidor = puertoEntrada;
							}
			    		}
			    		catch (SocketTimeoutException e2) {
							//int result = JOptionPane.showOptionDialog(null, "Servidor fuera de línea",null, JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, new Object[] { "Reintentar conexión" }, "Reintentar conexión");

			  	        }catch (IOException e) {
			  		    	e.printStackTrace();
			  		    }
		    		}
		    	 
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
				
				envio = true;
				
				buffer = new byte[4096]; // Reservar buffer para recibir objeto //VER: chequear  valor buffer
				DatagramPacket entrada = new DatagramPacket(buffer, buffer.length, direccion, portServidor);
				socketUDP.receive(entrada);
		    	socketUDP.setSoTimeout(0);
		    	
		    	envio = false;
		    	
		    	JOptionPane.showMessageDialog(null, "Estadisticas actualizadas");
		    	
		    	ByteArrayInputStream byteStream = new ByteArrayInputStream(entrada.getData());
		    	ObjectInputStream objectStream = new ObjectInputStream(byteStream);
		    	estadisticas = (Estadisticas) objectStream.readObject();
		    	this.ventanasupervisor.CargaEstadistica(estadisticas);
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
						ActionEvent eventoSimulado = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Actualizar");
						actionPerformed(eventoSimulado);
						 
					}else {
						reintento = 2;
						JOptionPane.showMessageDialog(null, "Reintentos fallidos, vuelva a reintentar en unos segundos o cierre la ventana"); 
						falloReintento = true;
						this.verificaServidor();
						envio = false;
						falloReintento = false;
					}
				}

  	        } catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
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
		this.vl.dispose();
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
					JOptionPane.showMessageDialog(null, "Conectado al servidor"); 
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

