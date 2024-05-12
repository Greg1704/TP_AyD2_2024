package controlador;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;

import ventana.VentanaMonitor;

public class ControladorVentanaMonitor {

	private int puertoServerPrincipal = 10000;
	private DatagramSocket socketUDP;
	static byte[] buffer = new byte[1024];
	final int portMonitor = 11000;
	String reg;
	private HashMap<Integer, Boolean> servidoresDisp = new HashMap<>();
	private VentanaMonitor vm;
	private static ControladorVentanaMonitor instancia = null;
	private boolean estaVivo = true;
	
	
	private ControladorVentanaMonitor() {
		//DatagramSocket socketUDP;
		try {
			socketUDP = new DatagramSocket(portMonitor);
			
			this.vm = new VentanaMonitor();
			this.vm.setControlador(this,this.servidoresDisp);
			this.esperandoNotificaciones(socketUDP);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static ControladorVentanaMonitor getInstancia() {
		if (instancia == null)
			instancia = new ControladorVentanaMonitor();
		return instancia;
	}
	
	
	public void esperandoNotificaciones(DatagramSocket socketUDP) {
		// TODO Auto-generated method stub

		
			try {
				while(true) {
					buffer = new byte[1024];
					DatagramPacket entrada = new DatagramPacket(buffer, buffer.length);
					socketUDP.receive(entrada);
					socketUDP.setSoTimeout(0);
					
					String mensaje = new String(entrada.getData());
					mensaje = mensaje.trim();
					int puertoEntrada = entrada.getPort();
					
					if(puertoEntrada >= 10000 && puertoEntrada <= 10010) {
						if(!this.servidoresDisp.containsKey(puertoEntrada)) {
							if(puertoEntrada == puertoServerPrincipal) {
								this.servidoresDisp.put(puertoEntrada, true);
								this.pingEcho(portMonitor, socketUDP);
							}else
								this.servidoresDisp.put(puertoEntrada, false);
							
							this.vm.actualizaServDisp(servidoresDisp);
						}else if(mensaje.equals("pong")){
							this.estaVivo = true;
							socketUDP.setSoTimeout(6000);
							//System.out.println("*Ruido de latido*");
						}else if(mensaje.equals("reemplazo")) {
							this.estaVivo = true;
							this.servidoresDisp.put(puertoServerPrincipal, true);
							this.vm.actualizaServDisp(servidoresDisp);

						}
					}else {
						//System.out.println("Puerto no habilitado");
					}
				}
			}catch (SocketTimeoutException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Se perdio el servidor principal, cambiando al servidor secundario"); 
				this.servidoresDisp.remove(puertoServerPrincipal);
				this.vm.actualizaServDisp(servidoresDisp);
				this.secundarioAPrincipal();
				this.esperandoNotificaciones(socketUDP);
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Arrays.fill(buffer, (byte) 0);
			System.out.println("Murio el while");
	}
	

	
	
	private void pingEcho (int puertoServidor, DatagramSocket socketUDP) throws UnknownHostException,SocketTimeoutException {
		
		Timer t = new Timer();
		String reg = "ping";
		InetAddress direccion = InetAddress.getByName("localHost");
		
		
		t.scheduleAtFixedRate(new TimerTask() { 
        @Override
	        public void run() {
	        	try {
	        		if(estaVivo) {
		        		byte[] buffer = new byte[1024];
						buffer = reg.getBytes();
			        	DatagramPacket salida = new DatagramPacket(buffer, buffer.length,direccion,puertoServerPrincipal);
						socketUDP.send(salida);
						estaVivo = false;
						//System.out.println("¿Late o no late?");
	        		}
	
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
		}, 500, 3000);
	
	}
	
	private void secundarioAPrincipal() {
		try {
			
			Set<Integer> keySet = this.servidoresDisp.keySet();
			Integer[] keys = keySet.toArray(new Integer[0]);  
			if(keys.length > 0) {
				String reg = "cambio";
				InetAddress direccion = InetAddress.getByName("localHost");
				byte[] buffer = new byte[1024];
				buffer = reg.getBytes();
				System.out.println(keys[0]);
		    	DatagramPacket salida = new DatagramPacket(buffer, buffer.length,direccion,keys[0]);
		    	this.puertoServerPrincipal = keys[0];
				socketUDP.send(salida);
				socketUDP.setSoTimeout(6000);
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
