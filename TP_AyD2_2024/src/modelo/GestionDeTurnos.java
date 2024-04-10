package modelo;

import java.util.ArrayList;
import java.util.List;

public class GestionDeTurnos {
	 private List<Turno> colaDeTurnos;

	 
	 
	public GestionDeTurnos() {
		this.colaDeTurnos = new ArrayList<Turno>();
	}
	
	public void añadirTurno(String dni) {
		Turno t = new Turno(dni);
		colaDeTurnos.add(t);
	}
	
	public void removerTurno(String dni) {
		int i = 0;
		while(i<colaDeTurnos.size() && !colaDeTurnos.get(i).getDni().equals(dni))
			i++;
		if(i<colaDeTurnos.size())
			colaDeTurnos.remove(i);
		else
			System.out.println("turno no encontrado");
	}
	
	public Turno extraerPrimerTurno() {
		Turno t = colaDeTurnos.get(0);
		colaDeTurnos.remove(0);
		return t;
	}
	
	public void llevarTurnoAPantalla() {
		Turno turno = colaDeTurnos.get(0);
		
		//LO QUE VAYA A PASAR DE MEDIO PARA CONECTAR ESTO CON EL TELEVISOR
	}
	
	public void mostrarCola() {  //No funciona el for, solo muestra el primer dni
		System.out.println("Tamanio de la cola =  " +colaDeTurnos.size());
		for (Turno elemento : colaDeTurnos) {
            System.out.print(elemento.getDni() + "  " + elemento.getNumeroDeBox() + "---");
        }
	}
	 
	 
}
