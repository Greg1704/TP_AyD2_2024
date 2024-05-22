package persistencia;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

import interfaces.IPersistencia;
import modelo.Cliente;

public class PersistenciaJSON implements IPersistencia{	
	private static final ObjectMapper objectMapper = new ObjectMapper();
	
	
	private String LOG_FILE_PATH = "";
	private String FILE_PATH = "";
	
	
	
	
	@Override
	public void saveLog(String log) {
		List<String> logs = new ArrayList<>();
		File file = new File(LOG_FILE_PATH);
		
		try {
			logs = objectMapper.readValue(file, new TypeReference<List<String>>() {});
			logs.add(log);
			String jsonLog = objectMapper.writeValueAsString(log);
			objectMapper.writeValue(file, jsonLog);
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void saveClientInfo(Cliente client) {
		List<Cliente> clientes = new ArrayList<>();
		File file = new File(FILE_PATH);
		
		try {
			clientes = objectMapper.readValue(file, new TypeReference<List<Cliente>>() {});
			if (!clientes.contains(client)) {
				clientes.add(client);
				objectMapper.writeValue(file, clientes);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	} 
	

}
