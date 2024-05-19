package modelo;

import java.io.Serializable;

public class Turno implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String dni;
	private String numeroDeBox;
	private Cronometro cronometro;
	private String afinidad;//Normal, Gold, Platinum, Black(Si nos sentimos muy importantes)
	private int edad; //SUJETO A CAMBIOS, NO ESTAMOS SEGUROS DE COMO TOMAR EN CUENTA EL RANGO ETARIO ACTUALMENTE
	
	
	public Turno(String dni) {
		this.dni = dni;
		this.numeroDeBox = "0";
		this.cronometro = new Cronometro();
	}


	public String getDni() {
		return dni;
	}


	public void setDni(String dni) {
		this.dni = dni;
	}


	public String getNumeroDeBox() {
		return numeroDeBox;
	}


	public void setNumeroDeBox(String numeroDeBox) {
		this.numeroDeBox = numeroDeBox;
	}


	@Override
	public String toString() {
		return "Turno [dni=" + dni + ", numeroDeBox=" + numeroDeBox + "]";
	}
	
	public Cronometro getCronometro() {
		return cronometro;
    }


	public String getAfinidad() {
		return afinidad;
	}


	public void setAfinidad(String afinidad) {
		this.afinidad = afinidad;
	}
	
}
