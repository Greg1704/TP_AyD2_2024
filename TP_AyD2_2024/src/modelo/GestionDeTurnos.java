package modelo;

import java.util.LinkedList;
import java.util.Queue;

public class GestionDeTurnos {
    private Queue<Turno> colaDeTurnos;

    public GestionDeTurnos() {
        this.colaDeTurnos = new LinkedList<>(); 
    }

    public void añadirTurno(String dni) {
        Turno t = new Turno(dni);
        colaDeTurnos.add(t);
    }

    public void removerTurno(String dni) {
        colaDeTurnos.removeIf(turno -> turno.getDni().equals(dni));
    }

    public Turno extraerPrimerTurno() {
        return colaDeTurnos.poll();
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
}
