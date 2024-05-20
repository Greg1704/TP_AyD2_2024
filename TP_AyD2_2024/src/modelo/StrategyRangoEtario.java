package modelo;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;

import interfaces.StrategyColas;

public class StrategyRangoEtario implements StrategyColas,Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String rangoEtario = ""; // Joven, Adulto, Adulto mayor
	

	public StrategyRangoEtario() {
		super();
	}



	@Override
	public Turno devolverTurno(GestionDeTurnos gdt) {
		this.rangoEtario = "Adulto mayor";
		Turno turno = this.extraerElementoConCondicion(gdt.getColaDeTurnos());
		if(turno == null) {
			this.rangoEtario = "Adulto";
			turno = this.extraerElementoConCondicion(gdt.getColaDeTurnos());
			if(turno == null) {
				this.rangoEtario = "Joven";
				turno = this.extraerElementoConCondicion(gdt.getColaDeTurnos());
			}
		}
        turno.getCronometro().detener();
		return turno;
	}
	
	public Turno extraerElementoConCondicion(Queue<Turno> cola) {
        Queue<Turno> colaAuxiliar = new LinkedList<>();
        Turno elementoExtraido = null;
        
        // Recorremos la cola original
        while (!cola.isEmpty()) {
            Turno actual = cola.poll();
            
            if (elementoExtraido == null && actual.getCliente().getRangoEtario().equalsIgnoreCase(this.rangoEtario)) {
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
