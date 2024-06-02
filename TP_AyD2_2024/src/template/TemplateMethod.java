package template;
import java.io.Serializable;
import java.util.ArrayList;

import modelo.Cliente;

public abstract  class TemplateMethod implements Serializable{
	
	public final InfoClienteArch readFile(String filePath, Cliente cliente) {
		openArch(filePath);
		InfoClienteArch info = BuscarClienteArch(filePath,cliente);
        closeArch();
        return info;
    }

    abstract void openArch(String filePath);
    
    abstract InfoClienteArch BuscarClienteArch(String filePath,Cliente cliente);
    abstract void closeArch();
    
}
