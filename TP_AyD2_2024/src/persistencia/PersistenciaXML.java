package persistencia;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import interfaces.IPersistencia;
import modelo.Cliente;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class PersistenciaXML implements IPersistencia{
		
	private String LOG_FILE_PATH = "TP_AyD2_2024/log/Log_Clientes_XML.xml";
	private String FILE_PATH = "TP_AyD2_2024/log/DB_Clientes_XML.xml";
	
	
	
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
		/*List<Cliente> clientes = new ArrayList<>();
		File file = new File(FILE_PATH);
		
		try {
			clientes =  xmlMapper.readValue(file, xmlMapper.getTypeFactory().constructCollectionType(List.class, Cliente.class));
			if (!clientes.contains(cliente)) {
				clientes.add(cliente);
				xmlMapper.writeValue(file, clientes);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

}
