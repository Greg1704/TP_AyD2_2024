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
	private DatagramSocket socketUPD;
	static byte[] buffer = new byte[1024];
	int portServidor = 10000;
	private static ControladorVentanaOperador instancia = null;
	int puerto;
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
			socketUPD = new DatagramSocket(puerto); 
			
			String reg = ingresarNumeroDeBox();
			
			buffer = reg.getBytes();
			DatagramPacket salida = new DatagramPacket(buffer, buffer.length,direccion,portServidor);
			
			socketUPD.send(salida);
			socketUPD.setSoTimeout(1000);
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.vl.dispose();
		this.ventanaOperador = new VentanaOperador();
		this.ventanaOperador.setControlador(this);
		//op = new Operador(1);
		Arrays.fill(buffer, (byte) 0);

		this.esperandoNotificaciones(socketUPD);
		
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
			siguiente = "true";
			try {
				
				Arrays.fill(buffer, (byte) 0);
				buffer = siguiente.getBytes();
				DatagramPacket salida = new DatagramPacket(buffer, buffer.length,direccion,portServidor);
				
				socketUPD.send(salida);		
				socketUPD.setSoTimeout(1000);
			
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
	
	public void esperandoNotificaciones(DatagramSocket socketUDP) {
		// TODO Auto-generated method stub

		
			try {
				while(true) {
					buffer = new byte[1024];
					DatagramPacket entrada = new DatagramPacket(buffer, buffer.length);
					socketUDP.receive(entrada);
					socketUPD.setSoTimeout(0);
					
					String mensaje = new String(entrada.getData());
					mensaje = mensaje.trim();
					int puertoEntrada = entrada.getPort();
					InetAddress direccion = entrada.getAddress();
					
					if(puertoEntrada == portServidor) {
						if (mensaje.matches("\\d+")) {
							//System.out.println("Soy un operador y mi numero de box es " + mensaje);
							this.setNumeroBox(mensaje);
						}else {
							if(mensaje.equals("hay turno")) {
								this.ventanaOperador.setVisible(false);
								
								int siOno = JOptionPane.showConfirmDialog(null,"Se presento el cliente?",null, JOptionPane.YES_NO_OPTION);
								
								if (siOno == JOptionPane.YES_OPTION) {
									String acepto = "acepto";
									Arrays.fill(buffer, (byte) 0);
									buffer = acepto.getBytes();
									DatagramPacket salidaSi = new DatagramPacket(buffer, buffer.length,direccion,portServidor);
									socketUPD.send(salidaSi);
						        }
								
								this.ventanaOperador.setVisible(true);
							}else {
								JOptionPane.showMessageDialog(null, "No hay turnos en espera en la cola");
							}
						}
					}else if(puertoEntrada >portServidor && puertoEntrada <10011){
						this.portServidor = puertoEntrada;
					}
				}
			}catch (SocketTimeoutException e2) {
				JOptionPane.showMessageDialog(null, "Servidor fuera de linea");
				this.socketUPD.close();
				this.ventanaOperador.dispose();
	        } 
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Arrays.fill(buffer, (byte) 0);
		
	}
	
	public void setNumeroBox(String box) {
		this.ventanaOperador.setBox(box);
	}
	
}

