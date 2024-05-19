package modelo;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Cliente {
	String Fecha; //ver si es string
	String RangoEtario;
	String Grupo; 
	String Dni;
	public Cliente(String dni, String grupo, String fecha) {
		super();
		this.Fecha = fecha;
		this.RangoEtario = calculoRangoEtario(this.Fecha); 
		this.Grupo = grupo;
		this.Dni = dni;
	}
	private String calculoRangoEtario(String fecha) {
		
		int edad =  calculateAge(fecha); //xx/xx/xxxx  
		
		if (edad<25) { 
			return "Joven";
		} else if(edad >60) { 
			return "Adulto mayor";
		} else { 
			return "Adulto";
		}
			
	}
	

    public static int calculateAge(String birthDateString) {
        // Define el formateador para el formato dd/MM/yyyy
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        try {
            // Parsear la fecha de nacimiento
            LocalDate birthDate = LocalDate.parse(birthDateString, formatter);
            // Obtener la fecha actual
            LocalDate currentDate = LocalDate.now();
            // Calcular el período entre la fecha de nacimiento y la fecha actual
            Period age = Period.between(birthDate, currentDate);
            // Retornar la cantidad de años como la edad
            return age.getYears();
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            // Retorna -1 en caso de error de parseo
            return -1;
        }
    }
    
    
	
	public void setGrupo(String grupo) {
		Grupo = grupo;
	}
	public String getRangoEtario() {
		return RangoEtario;
	}
	public String getFecha() {
		return Fecha;
	}
	public String getGrupo() {
		return Grupo;
	}
	public String getDni() {
		return Dni;
	} 
	
	

}
