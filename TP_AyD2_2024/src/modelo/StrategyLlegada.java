package modelo;

import java.io.Serializable;
import java.util.Queue;

import interfaces.StrategyColas;

public class StrategyLlegada implements StrategyColas,Serializable{
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StrategyLlegada() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Turno devolverTurno(GestionDeTurnos gdt) {
		Turno turno = gdt.getColaDeTurnos().poll();
        turno.getCronometro().detener();
		return turno; 
	}

	@Override
	public Turno extraerElementoConCondicion(Queue<Turno> cola) {
		// TODO Auto-generated method stub
		return null;
	}

}
