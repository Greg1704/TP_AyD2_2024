package persistencia;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import interfaces.IPersistencia;
import modelo.Cliente;

public class PersistenciaXML implements IPersistencia{
	private static final XmlMapper xmlMapper = new XmlMapper();
	
	
	
	private String LOG_FILE_PATH = "";
	private String FILE_PATH = "";
	
	
	
	@Override
	public void saveLog(String log) {
		List<String> logs = new ArrayList<>();
		File file = new File(LOG_FILE_PATH);
		
		try {
			logs = xmlMapper.readValue(file, xmlMapper.getTypeFactory().constructCollectionType(List.class, String.class));
			logs.add(log);
			String jsonLog = xmlMapper.writeValueAsString(log);
			xmlMapper.writeValue(file, jsonLog);
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void saveClientInfo(Cliente cliente) {
		List<Cliente> clientes = new ArrayList<>();
		File file = new File(FILE_PATH);
		
		try {
			clientes =  xmlMapper.readValue(file, xmlMapper.getTypeFactory().constructCollectionType(List.class, Cliente.class));
			if (!clientes.contains(cliente)) {
				clientes.add(cliente);
				xmlMapper.writeValue(file, clientes);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
