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

import ventana.VentanaLogin;
import ventana.VentanaOperador;


public class ControladorVentanaOperador implements ActionListener{

	private VentanaOperador ventanaOperador; 
	private VentanaLogin vl;
	private String siguiente = "false";
	private InetAddress direccion; 
	private DatagramSocket socketUDP;
	static byte[] buffer = new byte[1024];
	boolean envio = false;
	int portServidor = 10000;
	private static ControladorVentanaOperador instancia = null;
	private int puerto;
	private int reintento = 2;
	private String nBox;
	boolean falloReintento = false;

	//Operador op;
	
	
	private ControladorVentanaOperador() {
		
		this.vl = new VentanaLogin();
		this.vl.setControladorOperador(this);
		this.vl.esperarBoton();
		
		while(this.vl.getTextFieldUsuario().getText().equals("admin")) {
			JOptionPane.showMessageDialog(null, "Usuario y/o contraseña invalido");
			this.vl.dispose();
			this.vl = new VentanaLogin();
			this.vl.setControladorOperador(this);
			this.vl.esperarBoton();
		}
		
		try {
			puerto = 10300;
			
			
			
			direccion = InetAddress.getByName("localHost");
			
			while(!puertoDisponible(puerto))
				puerto++;
			socketUDP = new DatagramSocket(puerto); 
			
			this.nBox = this.ingresarNumeroDeBox();
			
			this.nBox = this.verificaServidor();
			
			this.ventanaOperador = new VentanaOperador();
			this.ventanaOperador.setControlador(this);
			this.setNumeroBox(this.nBox);
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//op = new Operador(1);
		Arrays.fill(buffer, (byte) 0);

		this.esperandoNotificaciones(socketUDP);
		
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
	
	public String ingresarNumeroDeBox() {
        String input = "";
        boolean esNumero = false;
        
        // Ciclo para asegurarnos de que el usuario ingrese solo números
        while (!esNumero) {
            // Mostramos la ventana emergente para que el usuario ingrese el texto
            input = JOptionPane.showInputDialog("Ingrese el numero de box que desea:");
            
            // Verificamos si el input es un número
            try {
                Integer.parseInt(input);
                // Si no se genera ninguna excepción, significa que es un número
                esNumero = true;
            } catch (NumberFormatException e) {
                // Si se genera una excepción, significa que no es un número
                JOptionPane.showMessageDialog(null, "Por favor, ingresa solo números.");
            }
        }
        
        return input;
    }
	
	public static ControladorVentanaOperador getInstancia() {
		if (instancia == null)
			instancia = new ControladorVentanaOperador();
		return instancia;
	}

	public void actionPerformed(ActionEvent e) { //deberia conectarse con el servidor y enviar un "true" (hay que ver como sacar el string y poner un boolean o algo) diciendo que hay siguiente.
		if (e.getActionCommand().equalsIgnoreCase("Llamar siguiente")) { 
			//byte[] buffer = new byte[1024]; 
			siguiente = "Solicito un turno";
			try {
				
				byte[] buffer = new byte[1024];
				Arrays.fill(buffer, (byte) 0);
				buffer = siguiente.getBytes();
				DatagramPacket salida = new DatagramPacket(buffer, buffer.length,direccion,portServidor);
				
				socketUDP.send(salida);		
				socketUDP.setSoTimeout(1000);
				envio = true;
				
				buffer = new byte[1024];
				DatagramPacket entrada = new DatagramPacket(buffer, buffer.length);
				socketUDP.receive(entrada);
				String mensaje = new String(entrada.getData());
				mensaje = mensaje.trim();
				envio = false;
				System.out.println(mensaje);
				
				socketUDP.setSoTimeout(0);
				if(mensaje.equals("hay turno")) {
					this.ventanaOperador.setVisible(false);
					
					int siOno = JOptionPane.showConfirmDialog(null,"Se presento el cliente?",null, JOptionPane.YES_NO_OPTION);
					
					if (siOno == JOptionPane.YES_OPTION) {
						String acepto = "acepto";
						Arrays.fill(buffer, (byte) 0);
						buffer = acepto.getBytes();
						DatagramPacket salidaSi = new DatagramPacket(buffer, buffer.length,direccion,portServidor);
						socketUDP.send(salidaSi);
			        }
					
					this.ventanaOperador.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "No hay turnos en espera en la cola");
				}
				
				this.reintento = 2;
				
				
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
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
						ActionEvent eventoSimulado = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Llamar siguiente");
						actionPerformed(eventoSimulado);
						 
					}else {
						reintento = 2;
						JOptionPane.showMessageDialog(null, "Reintentos fallidos, vuelva a reintentar en unos segundos o cierre la ventana"); 
						this.falloReintento = true;
						this.verificaServidor();
						envio = false;
						this.falloReintento = false;
					}
				
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		Arrays.fill(buffer, (byte) 0);

	}
	
	public void esperandoNotificaciones(DatagramSocket socketUDP) {
		// TODO Auto-generated method stub

			while(true) {
				System.out.println(envio);
				if (!envio) {
					try {
					
						buffer = new byte[1024];
						DatagramPacket entrada = new DatagramPacket(buffer, buffer.length);
						socketUDP.setSoTimeout(500);
						socketUDP.receive(entrada);
						socketUDP.setSoTimeout(0);
						
						String mensaje = new String(entrada.getData());
						mensaje = mensaje.trim();
						int puertoEntrada = entrada.getPort();
						
						if(mensaje.equals("cambio")){
							System.out.println("Se actualizo el puerto :D");
							this.portServidor = puertoEntrada;
						}
						
					}
					catch (SocketTimeoutException e2) {
						//int result = JOptionPane.showOptionDialog(null, "Servidor fuera de línea",null, JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null , null);						
			
			        } 
					catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Arrays.fill(buffer, (byte) 0);
				}
			} 
			
		
	}
	
	public void setNumeroBox(String box) {
		this.ventanaOperador.setBox(box);
	}
	
	public String verificaServidor() {
		boolean conseguimosServidor = false;
		String reg = this.nBox;
		InetAddress direccion;
		this.vl.dispose();
		String mensaje ="";
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
					
					mensaje = new String(entrada.getData());
					mensaje = mensaje.trim();
										
					
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
		if(portServidor == 10011) {
			JOptionPane.showMessageDialog(null, "No hay servidores disponibles a los que conectarse"); 
			System.exit(0);
		}
		
		return mensaje;
		
	}
	
}

