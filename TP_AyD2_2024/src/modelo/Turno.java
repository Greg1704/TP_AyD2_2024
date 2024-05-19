package modelo;

import java.io.Serializable;

public class Turno implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String numeroDeBox;
	private Cronometro cronometro;
	private Cliente cliente;
	
	
	public Turno(String dni, Cliente cliente) {
		this.numeroDeBox = "0";
		this.cliente = cliente;
		this.cronometro = new Cronometro();
	}


	public String getNumeroDeBox() {
		return numeroDeBox;
	}


	public void setNumeroDeBox(String numeroDeBox) {
		this.numeroDeBox = numeroDeBox;
	}


	@Override
	public String toString() {
		return "Turno [dni=" + this.cliente.getDni() + ", numeroDeBox=" + numeroDeBox + "]";
	}
	
	public Cronometro getCronometro() {
		return cronometro;
    }


	public Cliente getCliente() {
		return cliente;
	}	

	
}
