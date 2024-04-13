package modelo;

import java.util.LinkedList;
import java.util.Queue;

public class GestionDeTurnos {
    private Queue<Turno> colaDeTurnos;

    public GestionDeTurnos() {
        this.colaDeTurnos = new LinkedList<>(); 
    }

    //Al crear el turno, se inicia el cronometro
    public void añadirTurno(String dni) {
        Turno t = new Turno(dni);
        colaDeTurnos.add(t);
        t.getCronometro().iniciar();  
    }

    public void removerTurno(String dni) {
        colaDeTurnos.removeIf(turno -> turno.getDni().equals(dni));
    }

    //retirar turno de la cola y detener turno
    public Turno extraerPrimerTurno() {
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
}
