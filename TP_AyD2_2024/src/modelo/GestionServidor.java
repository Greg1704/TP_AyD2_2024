package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import abstractFactory.AbstractFactoryJSON;
import abstractFactory.AbstractFactoryTXT;
import abstractFactory.AbstractFactoryXML;
import interfaces.IAbstractFactory;
import interfaces.IPersistencia;
import strategy.FactoryStrategy;
import strategy.StrategyAfinidad;
import strategy.StrategyLlegada;
import strategy.StrategyRangoEtario;
import template.TemplateJSON;
import template.TemplateMethod;
import template.TemplateTXT;
import template.TemplateXML;

public class GestionServidor implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HashMap<Integer, String> conexiones;
	private GestionDeTurnos gdt;
	private HashMap<Integer, Integer> boxesOcupados; //<N Box,Puerto Box>
	private ArrayList<Turno> turnosEnPantalla;
	private Estadisticas estadisticas;
	private IPersistencia persistencia;
	private TemplateMethod template;

	
	
	public GestionServidor(String strategy, String persistencia)  {
		super();
		FactoryStrategy fs = new FactoryStrategy();
		this.persistencia = this.tipoPersistencia(persistencia);
		this.template = this.tipoTemplate(persistencia);
		this.estadisticas = new Estadisticas();
		this.conexiones = new HashMap<>();
		this.gdt = new GestionDeTurnos(fs.getStrategy(strategy)); //Se podria implementar factory para embellecer esto
		this.boxesOcupados = new HashMap<>();  //<N Box,Puerto Box>
		this.turnosEnPantalla = new ArrayList<Turno>();
	}
	
	public HashMap<Integer, String> getConexiones() {
		return conexiones;
	}
	public void setConexiones(HashMap<Integer, String> conexiones) {
		this.conexiones = conexiones;
	}
	public GestionDeTurnos getGdt() {
		return gdt;
	}
	public void setGdt(GestionDeTurnos gdt) {
		this.gdt = gdt;
	}
	public HashMap<Integer, Integer> getBoxesOcupados() {
		return boxesOcupados;
	}
	public void setBoxesOcupados(HashMap<Integer, Integer> boxesOcupados) {
		this.boxesOcupados = boxesOcupados;
	}
	public ArrayList<Turno> getTurnosEnPantalla() {
		return turnosEnPantalla;
	}
	public void setTurnosEnPantalla(ArrayList<Turno> turnosEnPantalla) {
		this.turnosEnPantalla = turnosEnPantalla;
	}

	public Estadisticas getEstadisticas() {
		return estadisticas;
	}

	public void setEstadisticas(Estadisticas estadisticas) {
		this.estadisticas = estadisticas;
	}
	
	public IPersistencia getPersistencia() {
		return persistencia;
	}

	public void setPersistencia(IPersistencia persistencia) {
		this.persistencia = persistencia;
	}

	public IPersistencia tipoPersistencia(String persistencia) {
		return (IPersistencia) this.tipoFactory(persistencia);
	}
	
	
	public TemplateMethod getTemplate() {
		return template;
	}

	public void setTemplate(TemplateMethod template) {
		this.template = template;
	}

	public IAbstractFactory tipoFactory(String persistencia) {
		if(persistencia.equalsIgnoreCase("TXT")) {
			return  (AbstractFactoryTXT) new AbstractFactoryTXT();
		}else if(persistencia.equalsIgnoreCase("XML")) {
			return  (AbstractFactoryXML) new AbstractFactoryXML();
		}else if(persistencia.equalsIgnoreCase("JSON")) {
			return  (AbstractFactoryJSON) new AbstractFactoryJSON();
		}
		return null;
	}
	
	public TemplateMethod tipoTemplate(String persistencia) {
		if(persistencia.equalsIgnoreCase("TXT")) {
			return (TemplateTXT) new TemplateTXT();
		}else if(persistencia.equalsIgnoreCase("XML")) {
			return (TemplateXML) new TemplateXML();
		}else if(persistencia.equalsIgnoreCase("JSON")) {
			return  (TemplateJSON) new TemplateJSON();
		}
		return null;
	}

	
}
