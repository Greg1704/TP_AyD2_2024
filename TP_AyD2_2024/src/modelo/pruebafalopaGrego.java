package modelo;

import java.util.ArrayList;
import java.util.HashMap;

public class pruebafalopaGrego {

	public static void main(String[] args) { 
		//HashMap<Integer, String> conexiones = new HashMap<>();
		GestionDeTurnos gdt = new GestionDeTurnos();
		//HashMap<Integer, Integer> boxesOcupados = new HashMap<>();  //<N Box,Puerto Box>
		//ArrayList<Turno> turnosEnPantalla = new ArrayList<Turno>();
		
		
		
		GestionServidor gestionServidor =  new GestionServidor(null, gdt, null, null);
		
		
		Turno turno = new Turno(null);
	
		
		System.out.println(gdt);
		System.out.println(gestionServidor.getGdt());
		
		
		
		
	}
	
}
