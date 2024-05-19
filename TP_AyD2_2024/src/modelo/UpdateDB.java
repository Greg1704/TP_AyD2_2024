package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UpdateDB {
	File file = new File("DB_Clientes.txt");
	String filePathDB = "DB_clientes.txt"; //ver bien esto
	
	
	
	
	public void cargaDB(Cliente cliente) {
		
		if (!existeDB(cliente.getDni())); { 
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePathDB, true))) {
				
	            bw.write(cliente.getDni() + "," + cliente.getGrupo() + "," + cliente.getFecha());
	            bw.newLine();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		}
	}
	
	public boolean existeDB(String dni) {
		try (BufferedReader br = new BufferedReader(new FileReader(filePathDB))) { 
			String line;
			while ((line = br.readLine()) != null) {
	            // Suponiendo que los datos están separados por comas
	            String[] parts = line.split(",");
	            if (parts.length == 3 && parts[0].trim().equals(dni)) {
	            	return true;
	            }
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false; 
		
	}
	
	
	public Cliente solicitaDB(String dni) {
		Cliente cliente = null;
		
		 try (BufferedReader br = new BufferedReader(new FileReader(filePathDB))) { 
	            String line;
	            while ((line = br.readLine()) != null) {
	                // Suponiendo que los datos están separados por comas
	                String[] parts = line.split(",");
	                if (parts.length == 3 && parts[0].trim().equals(dni)) {
	                    // DNI: parts[0], String: parts[1], Fecha: parts[2]
	                    cliente = new Cliente(parts[0].trim(), parts[1].trim(), parts[2].trim());
	                    
	                }
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        return cliente; // Retorna la lista de resultados

		
	}
	
	
	public void reiniciaDB() { //esto es solo para pruebas, borrar despues  
		
	}
}
