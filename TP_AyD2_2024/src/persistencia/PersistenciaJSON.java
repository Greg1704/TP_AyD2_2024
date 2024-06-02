package persistencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.awt.Window.Type;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

import interfaces.IPersistencia;
import modelo.Cliente;

public class PersistenciaJSON implements IPersistencia,Serializable{	
	
	
	
	private String LOG_FILE_PATH = "Ejecutables/log/Log_Clientes_JSON.json";
	private String FILE_PATH = "Ejecutables/log/DB/DB_Clientes_JSON.json";
	private String FILE_PATH_Dir = "Ejecutables/log/DB/DB_Clientes_JSON.json";
	
	
	
	@Override
	public void saveLog(String log) {
		List<String> logs = new ArrayList<>();
		List<String> logsAux = new ArrayList<>();
		Gson gson = new Gson();
		
		try(FileReader reader = new FileReader(LOG_FILE_PATH)){
			java.lang.reflect.Type logsListType = new TypeToken<List<String>>() {}.getType();
			logsAux = gson.fromJson(reader, logsListType);
			
			if (logsAux != null) 
				logs = logsAux;
			logs.add(log);	
			
			try(FileWriter writer = new FileWriter(LOG_FILE_PATH)){ 
				gson.toJson(logs,writer);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void saveClientInfo(Cliente client) {
        List<Cliente> clientes = new ArrayList<>();
        Gson gson = new Gson();

        // Leer el archivo JSON
        try (FileReader reader = new FileReader(FILE_PATH)) {
            java.lang.reflect.Type clienteListType = new TypeToken<List<Cliente>>() {}.getType();

            // Leer y parsear el archivo JSON
            try {
                clientes = gson.fromJson(reader, clienteListType);
            } catch (Exception e) {
                // Si hay un error al parsear (por ejemplo, si no es un array), inicializar la lista
                System.err.println("Error parsing JSON file, initializing with an empty list.");
                clientes = new ArrayList<>();
            }

            // Asegurarse de que la lista no sea nula
            if (clientes == null) {
                clientes = new ArrayList<>();
            }

            System.out.println(client);
            System.out.println(clientes.toString());

            // Agregar el nuevo cliente si no está en la lista
            if (!clientes.contains(client)) {
                clientes.add(client);

                // Escribir la lista actualizada de nuevo en el archivo
                try (FileWriter writer = new FileWriter(FILE_PATH)) {
                    gson.toJson(clientes, writer);
                }
            }
        } catch (FileNotFoundException e) {
            // Si el archivo no existe, inicializar con una lista vacía y crear el archivo
            System.out.println("File not found. Creating a new file.");
            try {
                clientes = new ArrayList<>();
                clientes.add(client);

                try (FileWriter writer = new FileWriter(FILE_PATH)) {
                    gson.toJson(clientes, writer);
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	public String getFILE_PATH() {
		return FILE_PATH;
	}

	
	@Override
	public void updateFormato() {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		
        String archTipo = buscarTipoArchivo();
		if (archTipo != "json") { //json
			try {
				FileWriter file = new FileWriter(FILE_PATH);
				file.write("{}");
	            file.flush();
	            
				//crear el archivo DB 
				//crear el archivo log
				
				if (archTipo == "xml") { //!xml
					String pathXML = (FILE_PATH.substring(0, FILE_PATH.length() - 9) + "XML.xml");
					 // Leer y parsear el archivo XML
			        File xmlFile = new File(FILE_PATH);
			        DocumentBuilderFactory dbFactory1 = DocumentBuilderFactory.newInstance();
			        DocumentBuilder dBuilder = dbFactory1.newDocumentBuilder();
			        Document doc = dBuilder.parse(xmlFile);
			
			        // Normalizar el documento XML
			        doc.getDocumentElement().normalize();
			
			        // Obtener el elemento <clients>, o crearlo si no existe
			        Element clientsElement = (Element) doc.getElementsByTagName("clients").item(0);
			        if (clientsElement == null) {
			            clientsElement = doc.createElement("clients");
			            doc.getDocumentElement().appendChild(clientsElement);
			        }
			
			        // Verificar si el cliente ya existe
			        NodeList clientList = clientsElement.getElementsByTagName("client");
			        List<Cliente> clientes = new ArrayList<Cliente>();
			        
			        for (int i = 0; i < clientList.getLength(); i++) {
			            Node clientNode = clientList.item(i);
			            
			            Element clientElement = (Element) clientNode;
		                String dni = clientElement.getElementsByTagName("DNI").item(0).getTextContent();
		                String grupo = clientElement.getElementsByTagName("Afinidad").item(0).getTextContent();
		                String fecha = clientElement.getElementsByTagName("Fecha").item(0).getTextContent();
			            Cliente clienteAux = new Cliente(dni, grupo, fecha);
			            clientes.add(clienteAux);
		            }
			        
					//borro el archivo
			        xmlFile.delete();
					
				} else if (archTipo == "txt") { //TXT
					String pathTXT = (FILE_PATH.substring(0, FILE_PATH.length() - 7) + "TXT.txt");
					Scanner scanner = new Scanner(new File(pathTXT));
					List<Cliente> clientes = new ArrayList<Cliente>();
					while (scanner.hasNextLine()) {
						String line = scanner.nextLine();
						String[] partes = line.split(",");
						String dni = partes[0];
	                    String grupo = partes[1];
	                    String fecha = partes[2];
						
						Cliente clientAux = new Cliente(dni, grupo, fecha);
						clientes.add(clientAux);
					}
					
					for (Cliente cliente : clientes) {
			            saveClientInfo(cliente);
			        }
					
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
				//saveUpdate(Array<String>, Array<Cliente>);
			
				
		}
	}
	
	 public String buscarTipoArchivo() {
	        StringBuilder resultado = new StringBuilder();
	        File directorio = new File(FILE_PATH_Dir);

	        // Verifica si el path es un directorio
	        if (directorio.isDirectory()) {
	            // Obtiene la lista de archivos en el directorio
	            File[] archivos = directorio.listFiles();
	            
	            // Verifica si hay archivos en el directorio
	            if (archivos != null) {
	                for (File archivo : archivos) {
	                    // Obtiene el nombre del archivo
	                    String nombreArchivo = archivo.getName();
	                    // Verifica la extensión del archivo
	                    if (nombreArchivo.endsWith(".xml")) {
	                        resultado.append("XML, ");
	                    } else if (nombreArchivo.endsWith(".json")) {
	                        resultado.append("JSON, ");
	                    } else if (nombreArchivo.endsWith(".txt")) {
	                        resultado.append("TXT, ");
	                    }
	                }
	            }

	            // Elimina la última coma y espacio si hay resultados
	            if (resultado.length() > 0) {
	                resultado.setLength(resultado.length() - 2);
	                return resultado.toString();
	            } else {
	                return "No se encontraron archivos XML, JSON o TXT";
	            }
	        } else {
	            return "El path proporcionado no es un directorio";
	        }
	    }
		
	
	
	

}

