package XML;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public interface XMLSerializable {

	public Element saveXML(Document document);
}
