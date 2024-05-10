package modelo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;

public class pruebafalopaGrego {

	public static void main(String[] args) { 
		
		GestionServidor e = GestionServidor.getInstance();
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
	    ObjectOutputStream objectStream;
		try {
			objectStream = new ObjectOutputStream(byteStream);
			objectStream.writeObject(e);
	        objectStream.flush();
	        byte[] objectBytes = byteStream.toByteArray();
	        System.out.println(objectBytes.length);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
}
