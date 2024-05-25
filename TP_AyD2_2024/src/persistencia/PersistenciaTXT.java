package persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import interfaces.IPersistencia;
import modelo.Cliente;

public class PersistenciaTXT implements IPersistencia{
	private String LOG_FILE_PATH = "";
	private String FILE_PATH = ""; //TODO:acomodar
	

	@Override
	public void saveLog(String log) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(LOG_FILE_PATH, true))) {
            bw.write(log); //hay que ver donde acomodar el log
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}


	@Override
	public void saveClientInfo(Cliente cliente) {
		if (!existeDB(cliente.getDni())); { 
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
	            bw.write(cliente.getDni() + "," + cliente.getGrupo() + "," + cliente.getFecha());
	            bw.newLine();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		}
		
	}
	
	public boolean existeDB(String dni) {
		try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) { 
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
	
	public String getFILE_PATH() {
		return FILE_PATH;
	}
	
}
