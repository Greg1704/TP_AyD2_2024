package persistencia;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;

import interfaces.IPersistencia;
import modelo.Cliente;
import template.InfoClienteArch;

import javax.xml.transform.TransformerFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.NodeList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class PersistenciaXML implements IPersistencia,Serializable{
		
	private String LOG_FILE_PATH = "Ejecutables/log/log/Log_Clientes_XML.xml";
	private String FILE_PATH = "Ejecutables/log/DB/DB_Clientes_XML.xml";
	private String FILE_PATH_Dir = "Ejecutables/log/DB"; //poner en diferentes filepath los log y los DB
	
	
	
	@Override
	public void saveLog(String log) {
		try {
			 File xmlFile = new File(LOG_FILE_PATH);
	            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	            Document doc = dBuilder.parse(xmlFile);

	            // Normalizar el documento XML
	            doc.getDocumentElement().normalize();

	            // Obtener el elemento <logs>, o crearlo si no existe
	            Element logsElement = (Element) doc.getElementsByTagName("logs").item(0);
	            if (logsElement == null) {
	                logsElement = doc.createElement("logs");
	                doc.getDocumentElement().appendChild(logsElement);
	            }

	            // Crear un nuevo elemento <log> y agregar el nuevo log
	            Element newLog = doc.createElement("log");
	            newLog.appendChild(doc.createTextNode(log));

	            // Agregar el nuevo elemento <log> al elemento <logs>
	            logsElement.appendChild(newLog);

	            // Guardar el documento XML modificado
	            TransformerFactory transformerFactory = TransformerFactory.newInstance();
	            Transformer transformer = transformerFactory.newTransformer();
	            DOMSource source = new DOMSource(doc);
	            StreamResult result = new StreamResult(new File(LOG_FILE_PATH));
	            transformer.transform(source, result);

	            System.out.println("Nuevo log agregado y archivo XML guardado correctamente.");

	        } catch (Exception e) {
	            e.printStackTrace();
	        
	        }
		}	

	@Override
	public void saveClientInfo(Cliente cliente) {
		try {
     
	        // Leer y parsear el archivo XML
	        File xmlFile = new File(FILE_PATH);
	        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
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
	        boolean exists = false;
	
	        for (int i = 0; i < clientList.getLength(); i++) {
	            Node clientNode = clientList.item(i);
	            if (clientNode.getNodeType() == Node.ELEMENT_NODE) {
	                Element clientElement = (Element) clientNode;
	                String clientDNI = clientElement.getElementsByTagName("DNI").item(0).getTextContent();
	
	                if (clientDNI.equals(cliente.getDni())) {
	                    exists = true;
	                    break;
	                }
	            }
	        }
	
	        // Agregar el nuevo cliente si no existe
	        if (!exists) {
	            Element newClient = doc.createElement("client");
	
	            Element DNIElement = doc.createElement("DNI");
	            DNIElement.appendChild(doc.createTextNode(cliente.getDni()));
	            newClient.appendChild(DNIElement);
	
	            Element FechaElement = doc.createElement("Fecha");
	            FechaElement.appendChild(doc.createTextNode(cliente.getFecha()));
	            newClient.appendChild(FechaElement);
	            
	            Element AfinidadElement = doc.createElement("Afinidad");
	            AfinidadElement.appendChild(doc.createTextNode(cliente.getGrupo()));
	            newClient.appendChild(AfinidadElement);
	
	            clientsElement.appendChild(newClient);
	
	            // Guardar el documento XML modificado
	            TransformerFactory transformerFactory = TransformerFactory.newInstance();
	            Transformer transformer = transformerFactory.newTransformer();
	            DOMSource source = new DOMSource(doc);
	            StreamResult result = new StreamResult(new File(FILE_PATH));
	            transformer.transform(source, result);
	
	            System.out.println("Nuevo cliente agregado y archivo XML guardado correctamente.");
	        } else {
	            System.out.println("El cliente ya existe en el archivo XML.");
	        }

	    } catch (Exception e) {
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
		if (!archTipo.equalsIgnoreCase("xml")) { //XML
			try {
				List<Cliente> clientes = null;
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.newDocument();
				Element rootElement = doc.createElement("Root");
		        doc.appendChild(rootElement);
		        
		        TransformerFactory transformerFactory = TransformerFactory.newInstance();
	            Transformer transformer = transformerFactory.newTransformer();
	            
	            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
	            DOMSource source = new DOMSource(doc);
	            StreamResult result = new StreamResult(new File(this.FILE_PATH));
	            
	            transformer.transform(source, result);
				//crear el archivo DB 
				//crear el archivo log
				
				if (archTipo.equalsIgnoreCase("json")) { //!json
					String pathJson = (FILE_PATH.substring(0, FILE_PATH.length() - 7) + "JSON.json");
					File fileJson = new File(pathJson);
					//saco el array de los archivos 
					try (FileReader reader = new FileReader(pathJson)) {
			            Gson gson = new Gson();
			            clientes = gson.fromJson(reader, new TypeToken<List<Cliente>>() {}.getType());
			           
			        } catch (IOException e) {
			            e.printStackTrace();
			        }
					//borro el archivo
					fileJson.delete();
					
				} else if (archTipo.equalsIgnoreCase("txt")) { //TXT
					String pathTXT = (FILE_PATH.substring(0, FILE_PATH.length() - 7) + "TXT.txt");
					Scanner scanner = new Scanner(new File(pathTXT));
					clientes = new ArrayList<Cliente>();
					while (scanner.hasNextLine()) {
						String line = scanner.nextLine();
						String[] partes = line.split(",");
						String dni = partes[0];
	                    String grupo = partes[1];
	                    String fecha = partes[2];
						
						Cliente clientAux = new Cliente(dni, grupo, fecha);
						clientes.add(clientAux);
					}					
					//Elimina el txt?
					scanner.close();
					File txtFile = new File(pathTXT);
					// Eliminar el archivo de texto
					if (txtFile.exists()) {
					    if (txtFile.delete()) {
					        System.out.println("El archivo TXT ha sido eliminado correctamente.");
					    } else {
					        System.out.println("No se pudo eliminar el archivo TXT.");
					    }
					} else {
					    System.out.println("El archivo TXT no existe.");
					}
					
						
				}
				for (Cliente cliente : clientes) {
		            saveClientInfo(cliente);
		        }
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TransformerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
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
