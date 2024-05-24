package modelo;


import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Map;

import interfaces.IStateServidor;
import state.Principal;
import state.Secundario;

public class Servidor {
	private long tiempoEspera;
	private LocalTime tiempoAtendido;
	private int portMonitor = 11000;
	private GestionServidor gestionServidor; 
	private IStateServidor estado;
	
	
	public Servidor() {
		//ServerSocket servidor = null; 
				//Socket sc = null;
				
				//HashMap<Integer, String> conexiones;
				//GestionDeTurnos gdt;
				//HashMap<Integer, Integer> boxesOcupados;
				//ArrayList<Turno> turnosEnPantalla;
				//Estadisticas estadisticas = new Estadisticas();
				//ArrayList<Integer> boxesOcupados = new ArrayList<Integer>();
		try {
		
			InetAddress direccion = InetAddress.getByName("localHost");
			byte[] bufferInicial = new byte[1024];
			int port = 10000;
			String reg = "";
			
			direccion = InetAddress.getByName("localHost");
			
			while(!puertoDisponible(port))
				port++;
								
			DatagramSocket socketUDP = new DatagramSocket(port); 
								
			bufferInicial = reg.getBytes();
			DatagramPacket salida = new DatagramPacket(bufferInicial, bufferInicial.length,direccion,portMonitor);
			socketUDP.send(salida);
								
			System.out.println("Servidor iniciado");
															
	
			// Ruta del archivo
			String rutaArchivo = "Ejecutables/ArchivoConfiguracion.txt";
			estado = new Secundario(this);
	
			// Variables para guardar las palabras
			String strategy = null;
			String persistencia = null;
	
			try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
			    // Leer la primera línea
			    strategy = br.readLine();
			    // Leer la segunda línea
			    persistencia = br.readLine();
			} catch (IOException e) {
			    e.printStackTrace();
			}
			gestionServidor = new GestionServidor(strategy); 
			while(true) {
					byte[] buffer = new byte[5120];	
					byte[] buffer_Est = new byte[5120];	
					
					DatagramPacket entrada = new DatagramPacket(buffer, buffer.length);
					socketUDP.receive(entrada); 
					
					
					
	
					
					//System.out.println(referencia);
					
					//System.out.println(mensaje);
					//System.out.println(conexiones);
					Arrays.fill(buffer, (byte) 0);
					System.out.println("----------------------------------------");
			}
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	public boolean puertoDisponible(int puerto) {
        try {
            DatagramSocket socket = new DatagramSocket(puerto);
            socket.close(); 
            return true; 
        } catch (SocketException e) {
            return false; 
        }
    }

	public IStateServidor getEstado() {
		return estado;
	}


	public void setEstado(IStateServidor estado) {
		this.estado = estado;
	}


	public long getTiempoEspera() {
		return tiempoEspera;
	}


	public void setTiempoEspera(long tiempoEspera) {
		this.tiempoEspera = tiempoEspera;
	}


	public LocalTime getTiempoAtendido() {
		return tiempoAtendido;
	}


	public void setTiempoAtendido(LocalTime tiempoAtendido) {
		this.tiempoAtendido = tiempoAtendido;
	}


	public int getPortMonitor() {
		return portMonitor;
	}


	public void setPortMonitor(int portMonitor) {
		this.portMonitor = portMonitor;
	}


	public GestionServidor getGestionServidor() {
		return gestionServidor;
	}


	public void setGestionServidor(GestionServidor gestionServidor) {
		this.gestionServidor = gestionServidor;
	}
	
	
	
	
	
}