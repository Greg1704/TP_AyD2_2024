package template;
import modelo.Cliente;

public class pruebafalopajuli {
	public Cliente c;
	public static InfoClienteArch info;
	
	public static void main (String args[]) {
		String filePath = "Ejecutables/TemplateTXT.txt";
		Cliente c = new Cliente("43456832","joven","210501");
		TemplateMethod tm = new TemplateTXT();
		
		InfoClienteArch info = tm.readFile(filePath, c);
        if (info != null) {
            System.out.println("Información del cliente encontrada: " + info.getDni());
        } else {
            System.out.println("Cliente no encontrado.");
        }
        
	
	}
		
}		/*
		String[] filePaths = {
	            "Ejecutables/TemplateTXT.txt",
	            "Ejecutables/TemplateXML.xml",
	            "Ejecutables/TemplateJSON.json"
	        };
		*/
		
		

		
		/* for (String filePath : filePaths) {
            TemplateMethod reader = getFileReader(filePath);
            if (reader != null) {
                InfoClienteArch info = reader.readFile(filePath, c);
                System.out.println("Resultados de búsqueda en " + filePath + ":");
                if (info != null) {
                    System.out.println("INFOOO" + info.getDni());
                } else {
                    System.out.println("Cliente no encontrado.");
                }
            } else {
                System.out.println("No se encontró un lector adecuado para el archivo: " + filePath);
            }
        }
	}

	private static TemplateMethod getFileReader(String filePath) {
		if (filePath.endsWith(".txt")) {
			return new TemplateTXT();			
		} else if (filePath.endsWith(".xml")) {
			return new TemplateXML();
		} else if (filePath.endsWith(".json")) {
			return new TemplateJSON();
		}
			return null;
	}	
	*/	
		
	
	
