package modelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class UpdateDB {
	File file = new File("DB_Clientes.txt");
	
	public void cargaDB() { 
		
	    
	      
	}
	
	
	
	public arraylist solicitaDB() { 
		Scanner inputFile;
		try {
			inputFile = new Scanner(file);
			while (inputFile.hasNext())
		      {
		         String familyName = inputFile.nextLine();

		         System.out.println(familyName);

		       }
		      inputFile.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
	
	public void reinicia() { //esto es solo para pruebas, borrar despues  
		
	}
}
