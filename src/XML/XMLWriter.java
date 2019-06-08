package XML;

import java.io.File;
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

/**
 * The Class XMLWriter.
 * @author Piotr Ruciñski
 *
 * @param <T> the generic type
 */
public class XMLWriter <T extends XMLSerializable> {
	
	/** The path. */
	private String path;
	
	/**
	 * Instantiates a new XML writer.
	 *
	 * @param path the path of the file it is writing in.
	 */
	public XMLWriter(String path) {
		this.path=path;
	}
	
	/**
	 * Save.
	 *
	 * @param t the object of T type
	 */
	public void save(T t) {
		
		StreamResult stream;
		
		try {
			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
			Document document = documentBuilder.newDocument();
			
            // root element
			document.appendChild(t.saveXML(document));
           
            DOMSource source = new DOMSource(document);
            stream = new StreamResult(new File(path));
            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, stream);
          
            
		} catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        } finally {
        	
        }
	}

}
