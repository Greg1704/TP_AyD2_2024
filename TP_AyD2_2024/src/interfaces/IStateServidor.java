package interfaces;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public interface IStateServidor {
	
	public void principal();
	
	public void secundario();
	
	public void accion(DatagramSocket socketUDP, DatagramPacket entrada, byte[] buffer) throws IOException, InterruptedException, ClassNotFoundException;

}
