package XML;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import Processing.Chessboard;
import Processing.Processing;
import Processing.Vector3;
import processing.core.PApplet;

/**
 * The Class XMLChessboardReader.
 * @author Piotr Ruciñski
 */
public class XMLChessboardReader {
	
	/** The path. */
	private String path;
	
	/**
	 * Instantiates a new XML chessboard reader.
	 *
	 * @param path the path to the file it reads
	 */
	public XMLChessboardReader (String path){
		this.path = path;
	}
	
	/**
	 * Load chessboard.
	 *
	 * @param parent the PApplet chessboard is used in
	 * @param pos the position of chessboard in PApplet
	 * @return the chessboard
	 */
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
