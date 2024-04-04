package modelo;

import java.util.ArrayList;
import java.util.List;

public class GestionDeTurnos {
	 List<Turno> colaDeTurnos;

	public GestionDeTurnos() {
		this.colaDeTurnos = new ArrayList<Turno>();
	}
	
	public void añadirTurno(int dni) {
		Turno t = new Turno(dni);
		colaDeTurnos.add(t);
	}
	
	public void removerTurno(int dni) {
		int i = 0;
		while(i<colaDeTurnos.size() && colaDeTurnos.get(i).getDni() != dni)
			i++;
		if(i<colaDeTurnos.size())
			colaDeTurnos.remove(i);
	}
	 
	 
}
