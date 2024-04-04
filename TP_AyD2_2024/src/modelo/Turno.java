package modelo;

public class Turno {
	public int dni;
	public int numeroDeBox;
	
	
	public Turno(int dni) {
		this.dni = dni;
		this.numeroDeBox = 0;
	}


	public int getDni() {
		return dni;
	}


	public void setDni(int dni) {
		this.dni = dni;
	}


	public int getNumeroDeBox() {
		return numeroDeBox;
	}


	public void setNumeroDeBox(int numeroDeBox) {
		this.numeroDeBox = numeroDeBox;
	}
	
	
	
}
