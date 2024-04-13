package modelo;

import java.util.LinkedList;
import java.util.Queue;

public class GestionDeTurnos {
    private Queue<Turno> colaDeTurnos;

    public GestionDeTurnos() {
        this.colaDeTurnos = new LinkedList<>(); // Cambiar a LinkedList para utilizar una cola
    }

    public void añadirTurno(String dni) {
        Turno t = new Turno(dni);
        colaDeTurnos.add(t);
    }

    public void removerTurno(String dni) {
        colaDeTurnos.removeIf(turno -> turno.getDni().equals(dni));
    }

    public Turno extraerPrimerTurno() {
        return colaDeTurnos.poll(); // Utilizar poll() para extraer y eliminar el primer elemento de la cola
    }

    public void llevarTurnoAPantalla() {
        Turno turno = colaDeTurnos.peek(); // Utilizar peek() para ver el primer elemento de la cola sin eliminarlo
        if (turno != null) {
            // LO QUE VAYA A PASAR DE MEDIO PARA CONECTAR ESTO CON EL TELEVISOR
        }
    }

    public void mostrarCola() {
        System.out.println("Tamaño de la cola = " + colaDeTurnos.size());
        for (Turno elemento : colaDeTurnos) {
            System.out.println(elemento.getDni() + " " + elemento.getNumeroDeBox() + "---");
        }
    }
}
