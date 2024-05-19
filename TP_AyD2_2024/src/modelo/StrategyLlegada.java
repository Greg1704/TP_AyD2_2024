package modelo;

import interfaces.StrategyColas;

public class StrategyLlegada implements StrategyColas{
	
	

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

}
