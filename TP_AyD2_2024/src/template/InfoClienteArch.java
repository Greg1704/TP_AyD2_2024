package template;

public class InfoClienteArch {
    public String dni;
    public String grupo;
    public String fechaNacimiento;

    public InfoClienteArch(String dni, String grupo, String fechaNacimiento) {
		super();
		this.dni = dni;
		this.grupo = grupo;
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getGrupo() {
        return grupo;
    }   

	public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}