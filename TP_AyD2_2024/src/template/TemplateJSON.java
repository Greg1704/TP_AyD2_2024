package template;

import modelo.Cliente;
import java.io.File;
import java.io.IOException;
import java.util.List;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TemplateJSON extends TemplateMethod{
	
	public InfoClienteArch infoCliente;

	@Override
	void openArch(String filePath) {
		// TODO Auto-generated method stub
		
	}

	@Override
	InfoClienteArch BuscarClienteArch(String filePath, Cliente cliente) {
	      ObjectMapper objectMapper = new ObjectMapper();
	      try {
	            List<Cliente> clientes = objectMapper.readValue(new File(filePath), new TypeReference<List<Cliente>>() {});
	            for (Cliente c : clientes) {
	                if (c.getDni().equals(cliente.getDni())) {
	                	 infoCliente = new InfoClienteArch(c.getDni(), c.getGrupo(), c.getFecha()); //no entiendo xq el error si todo develve string 
	                     return infoCliente;

	                }
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return infoCliente = null;
	}

	@Override
	void closeArch() {
		// TODO Auto-generated method stub
		
	}

}
