import java.util.ArrayList;
import java.util.List;
import processing.core.PApplet;

public class Chessboard extends PositionedObject {
	
	private final int size=8;
	List<List<Tile>> tiles;
	
	Player one;
	Player two;

	public Chessboard(PApplet parent, Vector3 pos) {
		super(parent, pos);
		tiles = new ArrayList<List<Tile>>();
		
	    for(int i=0; i<size; ++i)
	    {
	    	tiles.add(new ArrayList<Tile>());
	        for(int j=0; j<size; ++j)
	        {
	        	if( (i+j)%2 == 0 )
	        		tiles.get(i).add( new Tile(parent, new Vector3(j*50,0,i*50),Color.white) );
	        	else
	        		tiles.get(i).add( new Tile(parent, new Vector3(j*50,0,i*50),Color.black) );
	        }
	    }

	    one = new Player("Bia³a", PlayerID.one, Color.white, Sex.female, Skill.profesional, 10, 12);
	    two = new Player("Czarny", PlayerID.two, Color.black, Sex.male, Skill.beginner, 1, 5);
	    
	    //white
	    getTile(1,0).setFigure(new Knight(parent, this, new Vector3(1,0), one));
	    getTile(6,0).setFigure(new Knight(parent, this, new Vector3(6,0), one));
	    
	    getTile(2,0).setFigure(new Bishop(parent, this, new Vector3(2,0), one));
	    getTile(5,0).setFigure(new Bishop(parent, this, new Vector3(5,0), one));
	    
	    getTile(3,1).setFigure(new Pawn(parent, this, new Vector3(3,1), one));
	    
	    //black
	    getTile(1,7).setFigure(new Knight(parent, this, new Vector3(1,7), two));
	    getTile(6,7).setFigure(new Knight(parent, this, new Vector3(6,7), two));

	    getTile(2,7).setFigure(new Bishop(parent, this, new Vector3(2,7), two));
	    getTile(5,7).setFigure(new Bishop(parent, this, new Vector3(5,7), two));

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
		return tiles.get(j).get(i);
	}

	public Tile getTile(Vector3 v)
	{
		return tiles.get((int) v.getY()).get((int) v.getX());
	}
	
	public List<Vector3> getOccupiedTiles() 
	{
		List<Vector3> list= new ArrayList<Vector3>();
		for(int i=0; i<size; ++i)
			for(int j=0; j<size; ++j)
				if( tiles.get(j).get(i).getFigure() != null )
					list.add( new Vector3(i,j));
		
		return list;
	}
	
	public boolean checkCollision(Vector3 v)
	{
		if(v.getX() > 7 || v.getX() < 0 ||
		   v.getY() > 7 || v.getY() < 0)
			return true;
		else if(getTile(v).getFigure() != null)
			return true;
		else 
			return false;
	}
}
