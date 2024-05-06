package modelo;

import java.util.ArrayList;
import java.util.HashMap;

public class GestionServidor {
	public HashMap<Integer, String> conexiones = new HashMap<>();
	public GestionDeTurnos gdt = new GestionDeTurnos();
	public HashMap<Integer, Integer> boxesOcupados = new HashMap<>();  //<N Box,Puerto Box>
	public ArrayList<Turno> turnosEnPantalla = new ArrayList<Turno>();
	
	
	public GestionServidor(HashMap<Integer, String> conexiones, GestionDeTurnos gdt,
			HashMap<Integer, Integer> boxesOcupados, ArrayList<Turno> turnosEnPantalla) {
		super();
		this.conexiones = conexiones;
		this.gdt = gdt;
		this.boxesOcupados = boxesOcupados;
		this.turnosEnPantalla = turnosEnPantalla;
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
	
}
