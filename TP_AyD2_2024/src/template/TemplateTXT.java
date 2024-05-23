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
            System.out.println("TXT File Opened: " + filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    InfoClienteArch BuscarClienteArch(String filePath,Cliente cliente) {
    	int encontro = 0;
    	openArch(filePath);
    	try {
            while (scanner.hasNextLine() && encontro == 0) {
                String line = scanner.nextLine(); 
                if (line.contains(cliente.getDni()) && line.contains(cliente.getGrupo()) && line.contains(cliente.getFecha())) {
                	System.out.println("Cliente: " + line);
                	encontro = 1;
                	infoCliente.setDni(cliente.getDni());
                	infoCliente.setGrupo(cliente.getGrupo());
                	infoCliente.setDni(cliente.getFecha());
                }
            }
            return infoCliente;
        } catch (Exception e) {
            e.printStackTrace();
        }
    	closeArch();
        return infoCliente;
    }

    @Override
    void closeArch() {
        if (scanner != null) {
            scanner.close();
            System.out.println("TXT cerradoooo");
        }
    }
}
