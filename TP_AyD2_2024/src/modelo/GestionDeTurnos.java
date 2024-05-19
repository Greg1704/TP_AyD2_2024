package modelo;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;

public class GestionDeTurnos implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Queue<Turno> colaDeTurnos;

    public GestionDeTurnos() {
        this.colaDeTurnos = new LinkedList<>(); 
    }

    public void añadirTurno(String dni) {
        Turno t = new Turno(dni);
        colaDeTurnos.add(t);
        t.getCronometro().iniciar();  
        
    }

    public void removerTurno(String dni) {
        colaDeTurnos.removeIf(turno -> turno.getDni().equals(dni));
    }

    public Turno extraerPrimerTurno() {  //Esto debería ser modificado para que funcione en cuestión del Patron Strategy)
        Turno turno = colaDeTurnos.poll();
        turno.getCronometro().detener();
		return turno; 
    }
    
    public boolean isColaTurnosVacia() {
    	return colaDeTurnos.isEmpty();
    }

    public void mostrarCola() {
        System.out.println("Tamaño de la cola = " + colaDeTurnos.size());
        for (Turno elemento : colaDeTurnos) {
            System.out.println(elemento.getDni() + " " + elemento.getNumeroDeBox() + "---");
        }
    }

	public Queue<Turno> getColaDeTurnos() {
		return colaDeTurnos;
	}

	public void setColaDeTurnos(Queue<Turno> colaDeTurnos) {
		this.colaDeTurnos = colaDeTurnos;
	}
    
    
}
