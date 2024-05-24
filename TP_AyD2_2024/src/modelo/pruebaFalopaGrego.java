package modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import abstractFactory.AbstractFactoryJSON;
import abstractFactory.AbstractFactoryTXT;
import abstractFactory.AbstractFactoryXML;
import interfaces.IAbstractFactory;
import interfaces.IPersistencia;
import persistencia.PersistenciaJSON;

public class pruebaFalopaGrego {
	 private IAbstractFactory persistenceFactory;

	    public pruebaFalopaGrego(IAbstractFactory persistenceFactory) {
	        this.persistenceFactory = persistenceFactory;
	    }

	    public void saveData() {
	    	IPersistencia persistence = persistenceFactory.createPersistence();

	        // Ejemplo de uso
	        persistence.saveLog("Este es un log del sistema.");
	        List<Cliente> clients = new ArrayList<>();
	        Cliente client = new Cliente("Grego", "gold", "17/04/2001");
	        // Agregar clientes a la lista
	        persistence.saveClientInfo(client);
	    }

	    public static void main(String[] args) {
	    	IAbstractFactory factory = new AbstractFactoryJSON();
	    	pruebaFalopaGrego app = new pruebaFalopaGrego(factory);
	        app.saveData();

	        // Puedes cambiar la fábrica según el formato deseado
	        factory = new AbstractFactoryTXT();
	        app = new pruebaFalopaGrego(factory);
	        app.saveData();

	        factory = new AbstractFactoryXML();
	        app = new pruebaFalopaGrego(factory);
	        app.saveData();
	    }

}
