package Strategy;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;

import interfaces.IStrategyColas;
import modelo.GestionDeTurnos;
import modelo.Turno;

public class StrategyAfinidad implements IStrategyColas,Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String afinidad = ""; //Gold, Platinum, Black
	

	public StrategyAfinidad() {
		super();
	}


	@Override
	public Turno devolverTurno(GestionDeTurnos gdt) {
		// TODO Auto-generated method stub
		this.afinidad = "Black";
		Turno turno = this.extraerElementoConCondicion(gdt.getColaDeTurnos());
		if(turno == null) {
			this.afinidad = "Platinum";
			turno = this.extraerElementoConCondicion(gdt.getColaDeTurnos());
			if(turno == null) {
				this.afinidad = "Gold";
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
            
            if (elementoExtraido == null && actual.getCliente().getGrupo().equalsIgnoreCase(this.afinidad)) {
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
