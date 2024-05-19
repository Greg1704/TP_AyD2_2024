package interfaces;

import java.util.Queue;

import modelo.*;

public interface StrategyColas {
	public Turno devolverTurno(GestionDeTurnos gdt);
	public Turno extraerElementoConCondicion(Queue<Turno> cola);

}
