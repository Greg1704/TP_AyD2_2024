package template;
import java.util.ArrayList;

import modelo.Cliente;

abstract  class TemplateMethod {
	
	public final void readFile(String filePath, Cliente cliente) {
        openArch(filePath);
		BuscarClienteArch(filePath,cliente);
        closeArch();
    }

    abstract void openArch(String filePath);
    abstract InfoClienteArch BuscarClienteArch(String filePath,Cliente cliente);
    abstract void closeArch();
    
}
