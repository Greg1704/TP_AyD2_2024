package modelo;

public class Cliente {
	String Fecha; //ver si es string
	String grupo; 
	String dni;
	public Cliente(String dni, String grupo, String fecha) {
		super();
		Fecha = fecha;
		this.grupo = grupo;
		this.dni = dni;
	}
	public String getFecha() {
		return Fecha;
	}
	public void setFecha(String fecha) {
		Fecha = fecha;
	}
	public String getGrupo() {
		return grupo;
	}
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	} 
	
	

}
