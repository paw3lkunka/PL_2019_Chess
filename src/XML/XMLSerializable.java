package XML;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * The Interface XMLSerializable.
 * @author Piotr Ruciñski
 */
public interface XMLSerializable {

	/**
	 * Save XML.
	 *
	 * @param document the document we are forming XML document from.
	 * @return the element, an XML element containing data.
	 */
	public Element saveXML(Document document);
}
