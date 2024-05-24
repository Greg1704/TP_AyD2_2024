package abstractFactory;

import interfaces.IAbstractFactory;
import interfaces.IPersistencia;
import persistencia.PersistenciaJSON;

public class AbstractFactoryJSON implements IAbstractFactory{

	@Override
	public IPersistencia createPersistence() {
		// TODO Auto-generated method stub
		return new PersistenciaJSON();
	}

}
