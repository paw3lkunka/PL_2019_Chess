package XML;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import Processing.Chessboard;
import Processing.Processing;
import Processing.Vector3;
import processing.core.PApplet;

public class XMLChessboardReader {
	
	private String path;
	
	public XMLChessboardReader (String path){
		this.path = path;
	}
	
	public Chessboard loadChessboard(PApplet parent, Vector3 pos)
	{
		
		Chessboard c = null;
		Document document;
		try {
		File file = new File(path);
		DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
		document = documentBuilder.parse(file);
		
		document.getDocumentElement().normalize();

		c = new Chessboard(parent, pos, document.getDocumentElement());
		((Processing) parent).setC(c);
		
		} catch (Exception e) {
			e.printStackTrace();
	    } finally {
	    	
	    }
		
		return c;
	}
}
