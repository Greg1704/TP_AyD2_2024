package modelo;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;

import interfaces.StrategyColas;

public class StrategyAfinidad implements StrategyColas,Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String afinidad;
	

	public StrategyAfinidad(String afinidad) {
		super();
		this.afinidad = afinidad;
	}


	@Override
	public Turno devolverTurno(GestionDeTurnos gdt) {
		// TODO Auto-generated method stub
		return this.extraerElementoConCondicion(gdt.getColaDeTurnos());
	}
	
	public Turno extraerElementoConCondicion(Queue<Turno> cola) {
        Queue<Turno> colaAuxiliar = new LinkedList<>();
        Turno elementoExtraido = null;
        
        // Recorremos la cola original
        while (!cola.isEmpty()) {
            Turno actual = cola.poll();
            
            if (elementoExtraido == null && actual.getAfinidad().equalsIgnoreCase(this.afinidad)) {
                // Si encontramos el elemento que cumple la condición, lo extraemos
                elementoExtraido = actual;
            } else {
                // Si no es el elemento que buscamos, lo pasamos a la cola auxiliar
                colaAuxiliar.add(actual);
            }
        }
        
        // Volvemos a pasar los elementos de la cola auxiliar a la cola original
        while (!colaAuxiliar.isEmpty()) {
            cola.add(colaAuxiliar.poll());
        }
        
        return elementoExtraido;
    }

}
