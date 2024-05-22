package persistencia;

import java.util.List;
import java.io.File;
import com.fasterxml.jackson.databind.ObjectMapper;

import interfaces.IPersistencia;
import modelo.Cliente;

public class PersistenciaJSON implements IPersistencia{	
	private static final ObjectMapper objectMapper = new ObjectMapper();
	
	@Override
	public void saveLog(String log) {
		String jsonLog = objectMapper.writeValueAsString(log);
	}

	@Override
	public void saveClientInfo(Cliente clients) {

		
	} 
	

}
