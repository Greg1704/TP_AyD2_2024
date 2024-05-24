package strategy;

import interfaces.IStrategyColas;

public class FactoryStrategy {
	
	public IStrategyColas getStrategy(String strategy) {
		if(strategy.equalsIgnoreCase("Llegada")) {
			return (StrategyLlegada) new StrategyLlegada();
		}else if(strategy.equalsIgnoreCase("Afinidad")) {
			return (StrategyAfinidad) new StrategyAfinidad();
		}else if(strategy.equalsIgnoreCase("RangoEtario")) {
			return (StrategyRangoEtario) new StrategyRangoEtario();
		}
		return null;
		
	}
}
