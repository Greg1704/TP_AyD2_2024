package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class GestionServidor implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public HashMap<Integer, String> conexiones;
	public GestionDeTurnos gdt;
	public HashMap<Integer, Integer> boxesOcupados; //<N Box,Puerto Box>
	public ArrayList<Turno> turnosEnPantalla;
	public Estadisticas estadisticas;
	private static GestionServidor instancia = null;

	
	
	private GestionServidor()  {
		super();
		this.estadisticas = Estadisticas.getInstance();
		this.conexiones = new HashMap<>();
		this.gdt = new GestionDeTurnos();
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

	public static GestionServidor getInstance() {
		if (instancia == null)
			instancia = new GestionServidor();
		return instancia;
	}
	
}
