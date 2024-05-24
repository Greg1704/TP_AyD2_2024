package abstractFactory;

import interfaces.IAbstractFactory;
import interfaces.IPersistencia;
import persistencia.PersistenciaTXT;

public class AbstractFactoryTXT implements IAbstractFactory{

	@Override
	public IPersistencia createPersistence() {
		// TODO Auto-generated method stub
		return new PersistenciaTXT();
	}

}
