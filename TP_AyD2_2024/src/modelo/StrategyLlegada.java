package modelo;

import interfaces.StrategyColas;

public class StrategyLlegada implements StrategyColas{
	
	

	public StrategyLlegada() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Turno devolverTurno(GestionDeTurnos gdt) {
		// TODO Auto-generated method stub
		return gdt.extraerPrimerTurno();
	}

}
