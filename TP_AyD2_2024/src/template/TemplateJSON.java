package template;

import modelo.Cliente;
import java.io.File;
import java.io.IOException;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;

public class TemplateJSON extends TemplateMethod{
	
	public InfoClienteArch infoCliente;

	@Override
	void openArch(String filePath) {
		// TODO Auto-generated method stub
		
	}

	@Override
	 InfoClienteArch BuscarClienteArch(String filePath, Cliente cliente) {
        
		try (FileReader reader = new FileReader(filePath)) {
            Gson gson = new Gson();
            List<Cliente> clientes = gson.fromJson(reader, new TypeToken<List<Cliente>>() {}.getType());
            
            for (Cliente c : clientes) {
            	if (c.getDni().equals(cliente.getDni())) {
                    infoCliente = new InfoClienteArch(c.getDni(), c.getGrupo(), c.getFecha());
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
