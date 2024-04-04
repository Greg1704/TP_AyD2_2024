package controlador;


import ventana.VentanaSupervisor;


public class ControladorVentanaSupervisor{

	private VentanaSupervisor ventanasupervisor; 
	
	
	public ControladorVentanaSupervisor() {
		super();
		this.ventanasupervisor = new VentanaSupervisor();
		this.ventanasupervisor.setControlador(this);
	}
	
}

