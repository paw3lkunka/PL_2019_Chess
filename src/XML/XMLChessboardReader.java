package XML;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import Processing.Chessboard;
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
		
		try {
		File file = new File(path);
		DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
		Document document = documentBuilder.parse(file);
		
		document.getDocumentElement().normalize();

		c = new Chessboard(parent, pos, document.getDocumentElement());
		
		
		} catch (Exception e) {
			e.printStackTrace();
	    }
		
		return c;
	}
}
