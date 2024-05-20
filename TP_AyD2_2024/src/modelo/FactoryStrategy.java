package modelo;

import interfaces.StrategyColas;

public class FactoryStrategy {
	
	public StrategyColas getStrategy(String strategy, String atributo) {
		if(strategy.equalsIgnoreCase("Llegada")) {
			return (StrategyLlegada) new StrategyLlegada();
		}else if(strategy.equalsIgnoreCase("Afinidad")) {
			return (StrategyAfinidad) new StrategyAfinidad(atributo);
		}else if(strategy.equalsIgnoreCase("RangoEtario")) {
			return (StrategyRangoEtario) new StrategyRangoEtario(atributo);
		}
		return null;
		
	}
}
