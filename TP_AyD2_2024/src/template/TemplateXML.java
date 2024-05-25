package template;

import modelo.Cliente;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class TemplateXML extends TemplateMethod {
    public InfoClienteArch infoCliente;

    @Override
    void openArch(String filePath) {
            System.out.println("XML File Opened: " + filePath);
    }

    @Override
    void closeArch() {
        System.out.println("XML File Processing Completed.");
    }

    @Override
    InfoClienteArch BuscarClienteArch(String filePath, Cliente cliente) {
    	try {
            openArch(filePath);
    		File file = new File(filePath);
            if (file.exists()) {
            	XmlMapper xmlMapper = new XmlMapper();
            	List<Cliente> clientes = xmlMapper.readValue(file, xmlMapper.getTypeFactory().constructCollectionType(List.class, Cliente.class));
            	for (Cliente c : clientes) {
                	if (c.getDni().equals(cliente.getDni())) {
                    	infoCliente = new InfoClienteArch(c.getDni(), c.getGrupo(), c.getFecha()); //no entiendo xq el error si todo develve string 
                    	return infoCliente;
                	}
            	}
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    	closeArch();
        return infoCliente = null;
    }
}
