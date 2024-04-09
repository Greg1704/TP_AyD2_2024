package modelo;

public class Turno {
	public String dni;
	public int numeroDeBox;
	
	
	public Turno(String dni) {
		this.dni = dni;
		this.numeroDeBox = 0;
	}


	public String getDni() {
		return dni;
	}


	public void setDni(String dni) {
		this.dni = dni;
	}


	public int getNumeroDeBox() {
		return numeroDeBox;
	}


	public void setNumeroDeBox(int numeroDeBox) {
		this.numeroDeBox = numeroDeBox;
	}
	
}
