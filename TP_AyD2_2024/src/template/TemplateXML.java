package template;

import modelo.Cliente;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class TemplateXML extends TemplateMethod {
    @Override
    void openArch(String filePath) {
        System.out.println("XML File abrido: " + filePath);
    }

    @Override
    void closeArch() {
        System.out.println("XML cerrado");
    }

    @Override
    InfoClienteArch BuscarClienteArch(String filePath, Cliente cliente) {
        openArch(filePath); 

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            File xmlFile = new File(filePath);

            Document doc = builder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            
            NodeList nodeList = doc.getElementsByTagName("client");


            for (int i = 0; i < nodeList.getLength(); i++) {
                Element clientElement = (Element) nodeList.item(i);
                String clientDNI = clientElement.getElementsByTagName("DNI").item(0).getTextContent();

                if (clientDNI.equals(cliente.getDni())) {
                    closeArch(); 
                    return new InfoClienteArch(
                            clientElement.getElementsByTagName("DNI").item(0).getTextContent(),
                            clientElement.getElementsByTagName("Afinidad").item(0).getTextContent(),
                            clientElement.getElementsByTagName("Fecha").item(0).getTextContent());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        closeArch();
        return null; 
    }
}
