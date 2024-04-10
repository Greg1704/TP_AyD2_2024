package modelo;

public class Turno {
	public String dni;
	public String numeroDeBox;
	
	
	public Turno(String dni) {
		this.dni = dni;
		this.numeroDeBox = "0";
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
	
}
