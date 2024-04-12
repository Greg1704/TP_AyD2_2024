package modelo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class PantallaTV {
	static byte[] buffer = new byte[1024];
	
	
	public PantallaTV() {
		super();
	}


	public void esperandoNotificaciones(DatagramSocket socketUDP) {
		// TODO Auto-generated method stub

		while(true) {
			DatagramPacket entrada = new DatagramPacket(buffer, buffer.length);
			try {
				socketUDP.receive(entrada);
				
				String mensaje = new String(entrada.getData());
				mensaje = mensaje.trim();
				int puertoEntrada = entrada.getPort();
				InetAddress direccion = entrada.getAddress();
				
				System.out.println(mensaje);
				
				if(puertoEntrada == 10000) {
					System.out.println("Soy un televisor llamando la atención");
				}else {
					System.out.println("Puerto no habilitado");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
