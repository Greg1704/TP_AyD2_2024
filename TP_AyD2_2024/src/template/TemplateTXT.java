package template;

import modelo.Cliente;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class TemplateTXT extends TemplateMethod {
    private static Scanner scanner;
    public InfoClienteArch infoCliente;
    
    @Override
    void openArch(String filePath) {
        try {
            scanner = new Scanner(new File(filePath));
            System.out.println("TXT abrido: " + filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    InfoClienteArch BuscarClienteArch(String filePath,Cliente cliente) {
    	boolean encontro = false;
    	openArch(filePath);
    	try {
            while (scanner.hasNextLine() && !encontro ) {
                String line = scanner.nextLine(); 
                if (line.contains(cliente.getDni()) && line.contains(cliente.getGrupo()) && line.contains(cliente.getFecha())) {
                	System.out.println("Cliente: " + line);
                	encontro = true;
                	infoCliente = new InfoClienteArch(cliente.getDni(),cliente.getGrupo(), cliente.getFecha());
                }
            }
            if (!encontro) {
            	infoCliente = null;
            }
            return infoCliente;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	closeArch();
        }
    	return infoCliente;
    }

    @Override
    void closeArch() {
        if (scanner != null) {
            scanner.close();
        }
    }
}
