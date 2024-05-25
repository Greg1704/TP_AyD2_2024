package persistencia;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.awt.Window.Type;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import interfaces.IPersistencia;
import modelo.Cliente;

public class PersistenciaJSON implements IPersistencia{	
	
	
	
	private String LOG_FILE_PATH = "TP_AyD2_2024/";
	private String FILE_PATH = "TP_AyD2_2024/log/DB_Clientes_JSON.json";
	
	
	
	
	
	@Override
	public void saveLog(String log) {
		List<String> logs = new ArrayList<>();
		Gson gson = new Gson();
		
		try(FileReader reader = new FileReader(LOG_FILE_PATH)){
			java.lang.reflect.Type logsListType = new TypeToken<List<String>>() {}.getType();
			logs = gson.fromJson(reader, logsListType);
			logs.add(log);
			
			try(FileWriter writer = new FileWriter(LOG_FILE_PATH)){ 
				gson.toJson(logs,writer);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void saveClientInfo(Cliente client) {
		List<Cliente> clientes = new ArrayList<>();
		List<Cliente> clientesAux = new ArrayList<>();
		Gson gson = new Gson();
		
		
		try(FileReader reader = new FileReader(FILE_PATH)) { 
			java.lang.reflect.Type clienteListType = new TypeToken<List<Cliente>>() {}.getType();
			
			clientesAux = gson.fromJson(reader, clienteListType);
			if (clientesAux != null)
				clientes = clientesAux;
			
			System.out.println(client);
			System.out.println(clientes.toString());
			
			if (!clientes.contains(client)) {
				clientes.add(client);
				
				try(FileWriter writer = new FileWriter(FILE_PATH)){ 
					gson.toJson(clientes,writer);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Working Directory = " + System.getProperty("user.dir"));
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
	

}

