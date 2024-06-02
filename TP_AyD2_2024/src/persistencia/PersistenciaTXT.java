package persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
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

import interfaces.IPersistencia;
import modelo.Cliente;

public class PersistenciaTXT implements IPersistencia,Serializable{
	private String LOG_FILE_PATH = "log/log/Log_Clientes_TXT.txt";
	private String FILE_PATH = "log/DB/DB_Clientes_TXT.txt";
	private String FILE_PATH_Dir = "log/DB";
	

	@Override
	public void saveLog(String log) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(LOG_FILE_PATH, true))) {
            bw.write(log); //hay que ver donde acomodar el log
            bw.newLine();
        } catch (IOException e) {
            //e.printStackTrace();
        }
	}


	@Override
	public void saveClientInfo(Cliente cliente) {
		if (!existeDB(cliente.getDni())) { 
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
	            bw.write(cliente.getDni() + "," + cliente.getGrupo() + "," + cliente.getFecha());
	            bw.newLine();
	        } catch (IOException e) {
	            //e.printStackTrace();
	        }
		}
		
	}
	
	public boolean existeDB(String dni) {
		try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) { 
			String line;
			while ((line = br.readLine()) != null) {
	            // Suponiendo que los datos están separados por comas
	            String[] parts = line.split(",");
	            if (parts.length == 3 && parts[0].trim().equals(dni)) {
	            	return true;
	            }
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("El archivo no existe, se creara a continuacion");
		}
		return false; 
		
	}
	
	public String getFILE_PATH() {
		return FILE_PATH;
	}


	@Override
	public void updateFormato() {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		
        String archTipo = buscarTipoArchivo();
		if (!archTipo.equalsIgnoreCase("txt")) { //txt
			try {
				File file = new File(FILE_PATH);
				//crear el archivo DB 
				//crear el archivo log
				List<Cliente> clientes = null;
				
								
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
					for (Cliente cliente : clientes) {
						try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
				            bw.write(cliente.getDni() + "," + cliente.getGrupo() + "," + cliente.getFecha());
				            bw.newLine();
				        } catch (IOException e) {
				            e.printStackTrace();
				        }
			        }
				} else if (archTipo.equalsIgnoreCase("xml")) { //!xml
					String pathXML = (FILE_PATH.substring(0, FILE_PATH.length() - 7) + "XML.xml");
					 // Leer y parsear el archivo XML
					System.out.println(pathXML);
			        File xmlFile = new File(pathXML);
			        DocumentBuilderFactory dbFactory1 = DocumentBuilderFactory.newInstance();
			        DocumentBuilder dBuilder1 = dbFactory1.newDocumentBuilder();
			        Document doc1 = dBuilder1.parse(xmlFile);
			
			        // Normalizar el documento XML
			        doc1.getDocumentElement().normalize();
			
			        // Obtener el elemento <clients>, o crearlo si no existe
			        Element clientsElement = (Element) doc1.getElementsByTagName("clients").item(0);
			        if (clientsElement == null) {
			            clientsElement = doc1.createElement("clients");
			            doc1.getDocumentElement().appendChild(clientsElement);
			        }
			
			        // Verificar si el cliente ya existe
			        clientes = new ArrayList<Cliente>();
			        NodeList clientList = clientsElement.getElementsByTagName("client");			    
			        
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
			        for (Cliente cliente : clientes) {
						try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
				            bw.write(cliente.getDni() + "," + cliente.getGrupo() + "," + cliente.getFecha());
				            bw.newLine();
				        } catch (IOException e) {
				            e.printStackTrace();
				        }
			        }
				}				
				
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
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
