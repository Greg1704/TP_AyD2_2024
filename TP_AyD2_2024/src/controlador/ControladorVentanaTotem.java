package controlador;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import modelo.Totem;

public class ControladorVentanaTotem {
	public Totem totem;
	final static int port = 10000;
	
	//hay que poner un action listener para que cuando se apriere el solicitar turno se mande esto al servidor
	public static void conexionServidor() {
		DataInputStream in;
		DataOutputStream out;
		
		String dni = "43184902";
		
		Socket sc = Socket(port);
		
		in = new DataInputStream(sc.getInputStream());
		out = new DataOutputStream(sc.getOutputStream());
		
		out.writeUTF(dni);
		
		String mensaje = in.readUTF();
		System.out.println(mensaje);
		
		sc.close();
	}

}
