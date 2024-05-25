package template;
import java.util.ArrayList;

import modelo.Cliente;

abstract  class TemplateMethod {
	
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
