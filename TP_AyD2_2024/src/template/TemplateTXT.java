package template;

import modelo.Cliente;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TemplateTXT extends TemplateMethod {
    private static Scanner scanner;
    public InfoClienteArch infoCliente;
    
    @Override
    void openArch(String filePath) {
        try {
            scanner = new Scanner(new File(filePath));
            System.out.println("TXT abierto: " + filePath);
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
                if (line.contains(cliente.getDni())) {
                	System.out.println("Cliente: " + line);
                	String grupo ="Gold";
                	if(line.contains("Gold")) {
                		grupo = "Gold";
                	}else if(line.contains("Platinum")) {
                		grupo = "Platinum";
                	}else if(line.contains("Black")) {
                		grupo = "Black";
                	}
                	encontro = true;
                	infoCliente = new InfoClienteArch(cliente.getDni(),grupo, cliente.getFecha());
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
