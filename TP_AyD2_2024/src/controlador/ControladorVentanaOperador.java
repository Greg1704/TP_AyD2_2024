package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ventana.VentanaOperador;


public class ControladorVentanaOperador implements ActionListener{

	private VentanaOperador ventanaOperador; 
	
	
	public ControladorVentanaOperador() {
		super();
		this.ventanaOperador = new VentanaOperador();
		this.ventanaOperador.setControlador(this);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equalsIgnoreCase("Llamar siguiente")) { 
			System.out.println("hola");
		}
	}
	
}

