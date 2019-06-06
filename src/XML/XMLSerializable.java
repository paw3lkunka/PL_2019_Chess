package XML;

import org.w3c.dom.Document;

public interface XMLSerializable<T> {

	public void createElement(Document document);
	public T createObject(Document document);
}
