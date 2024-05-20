package interfaces;

import java.util.Queue;

import modelo.*;

public interface IStrategyColas {
	public Turno devolverTurno(GestionDeTurnos gdt);
	public Turno extraerElementoConCondicion(Queue<Turno> cola);

}
