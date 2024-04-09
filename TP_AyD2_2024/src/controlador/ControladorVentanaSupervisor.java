package controlador;


import ventana.VentanaSupervisor;


public class ControladorVentanaSupervisor{

	private VentanaSupervisor ventanasupervisor; 
	private static ControladorVentanaSupervisor instancia = null;
	
	
	private ControladorVentanaSupervisor() {
		super();
		this.ventanasupervisor = new VentanaSupervisor();
		this.ventanasupervisor.setControlador(this);
	}
	
	public static ControladorVentanaSupervisor getInstancia() {
		if (instancia == null)
			instancia = new ControladorVentanaSupervisor();
		return instancia;
	}
	
}

