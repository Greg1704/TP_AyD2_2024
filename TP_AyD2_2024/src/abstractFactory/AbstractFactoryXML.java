package abstractFactory;

import interfaces.IAbstractFactory;
import interfaces.IPersistencia;
import persistencia.PersistenciaXML;

public class AbstractFactoryXML implements IAbstractFactory{

	@Override
	public IPersistencia createPersistence() {
		// TODO Auto-generated method stub
		return new PersistenciaXML();
	}

}
