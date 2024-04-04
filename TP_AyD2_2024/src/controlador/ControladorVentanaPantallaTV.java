package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ventana.VentanaPantallaTV;

public class ControladorVentanaPantallaTV implements ActionListener{

private VentanaPantallaTV ventanaPantallaTV; 
	

	public ControladorVentanaPantallaTV() {
		super();
		this.ventanaPantallaTV = new VentanaPantallaTV();
		this.ventanaPantallaTV.setControlador(this);
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}
	
}