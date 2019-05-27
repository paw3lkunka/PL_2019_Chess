import java.util.Vector;
import processing.core.PApplet;

public class Chessboard extends PositionedObject {
	
	private final int size=8;
	Vector<Vector<Tile>> tiles;

	public Chessboard(PApplet parent, Vector3 pos) {
		super(parent, pos);
		tiles = new Vector<Vector<Tile>>();
		
	    for(int i=0; i<size; ++i)
	    {
	    	tiles.add(new Vector<Tile>());
	        for(int j=0; j<size; ++j)
	        {
	        	if( (i+j)%2 == 0 )
	        		tiles.get(i).add( new Tile(parent, new Vector3(j*50,0,i*50),Color.white) );
	        	else
	        		tiles.get(i).add( new Tile(parent, new Vector3(j*50,0,i*50),Color.black) );
	        }
	    }
	}

	public void display()
	{
		super.place();
		
		for(int i=0; i<size; ++i)
			for(int j=0; j<size; ++j)
				tiles.get(i).get(j).display();
	}
	
	public Tile getTile(int i, int j)
	{
		return tiles.get(i).get(j);
	}
	
}
