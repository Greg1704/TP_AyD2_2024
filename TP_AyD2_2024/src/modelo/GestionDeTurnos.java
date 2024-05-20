package modelo;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;

import interfaces.IStrategyColas;

public class GestionDeTurnos implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Queue<Turno> colaDeTurnos;
	private IStrategyColas strategy;

    public GestionDeTurnos(IStrategyColas strategy) {
    	this.strategy = strategy;
        this.colaDeTurnos = new LinkedList<>(); 
    }

    public void añadirTurno(Cliente cliente) {
        Turno t = new Turno(cliente.getDni(),cliente);
        colaDeTurnos.add(t);
        t.getCronometro().iniciar();  
        
    }

    public void removerTurno(String dni) {
        colaDeTurnos.removeIf(turno -> turno.getCliente().getDni().equals(dni));
    }

    public Turno extraerPrimerTurno() {  //Esto debería ser modificado para que funcione en cuestión del Patron Strategy)
		return this.strategy.devolverTurno(this); 
    }
    
    public boolean isColaTurnosVacia() {
    	return colaDeTurnos.isEmpty();
    }

    public void mostrarCola() {
        System.out.println("Tamaño de la cola = " + colaDeTurnos.size());
        for (Turno elemento : colaDeTurnos) {
            System.out.println(elemento.getCliente().getDni() + " " + elemento.getNumeroDeBox() + "---");
        }
    }

	public Queue<Turno> getColaDeTurnos() {
		return colaDeTurnos;
	}

	public void setColaDeTurnos(Queue<Turno> colaDeTurnos) {
		this.colaDeTurnos = colaDeTurnos;
	}

	public IStrategyColas getStrategy() {
		return strategy;
	}

	public void setStrategy(IStrategyColas strategy) {
		this.strategy = strategy;
	}
    
    
}
