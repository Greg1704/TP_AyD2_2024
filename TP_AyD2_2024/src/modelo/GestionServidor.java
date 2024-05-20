package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import Strategy.FactoryStrategy;

public class GestionServidor implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HashMap<Integer, String> conexiones;
	private GestionDeTurnos gdt;
	private HashMap<Integer, Integer> boxesOcupados; //<N Box,Puerto Box>
	private ArrayList<Turno> turnosEnPantalla;
	private Estadisticas estadisticas;

	
	
	public GestionServidor(String strategy)  {
		super();
		FactoryStrategy fs = new FactoryStrategy();
		
		this.estadisticas = new Estadisticas();
		this.conexiones = new HashMap<>();
		this.gdt = new GestionDeTurnos(fs.getStrategy(strategy)); //Se podria implementar factory para embellecer esto
		this.boxesOcupados = new HashMap<>();  //<N Box,Puerto Box>
		this.turnosEnPantalla = new ArrayList<Turno>();
	}
	
	public HashMap<Integer, String> getConexiones() {
		return conexiones;
	}
	public void setConexiones(HashMap<Integer, String> conexiones) {
		this.conexiones = conexiones;
	}
	public GestionDeTurnos getGdt() {
		return gdt;
	}
	public void setGdt(GestionDeTurnos gdt) {
		this.gdt = gdt;
	}
	public HashMap<Integer, Integer> getBoxesOcupados() {
		return boxesOcupados;
	}
	public void setBoxesOcupados(HashMap<Integer, Integer> boxesOcupados) {
		this.boxesOcupados = boxesOcupados;
	}
	public ArrayList<Turno> getTurnosEnPantalla() {
		return turnosEnPantalla;
	}
	public void setTurnosEnPantalla(ArrayList<Turno> turnosEnPantalla) {
		this.turnosEnPantalla = turnosEnPantalla;
	}

	public Estadisticas getEstadisticas() {
		return estadisticas;
	}

	public void setEstadisticas(Estadisticas estadisticas) {
		this.estadisticas = estadisticas;
	}

	
}
