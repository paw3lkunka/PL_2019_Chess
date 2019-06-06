package Processing;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import Exceptions.FigureCollisionException;
import Exceptions.NoCollisionException;
import Exceptions.OutOfChessboardException;
import Figures.Bishop;
import Figures.FColor;
import Figures.King;
import Figures.Knight;
import Figures.Pawn;
import Figures.Queen;
import Figures.Rook;
import Player.Player;
import Player.PlayerID;
import Player.Sex;
import Player.Skill;
import processing.core.PApplet;

public class Chessboard extends PositionedObject {
	
	private final int size=8;
	private List<List<Tile>> tiles;
	private Player one;
	private Player two;

	public Chessboard(PApplet parent, Vector3 pos) {
		super(parent, pos);
		tiles = new ArrayList<List<Tile>>();
		
	    for(int i=0; i<size; ++i)
	    {
	    	tiles.add(new ArrayList<Tile>());
	        for(int j=0; j<size; ++j)
	        {
	        	if( (i+j)%2 == 0 )
	        		tiles.get(i).add( new Tile(parent, new Vector3(j*50,0,i*50),TColor.white) );
	        	else
	        		tiles.get(i).add( new Tile(parent, new Vector3(j*50,0,i*50),TColor.black) );
	        }
	    }

	    one = new Player("Bia³a", PlayerID.one, FColor.white, Sex.female, Skill.profesional, 10, 12);
	    two = new Player("Czarny", PlayerID.two, FColor.black, Sex.male, Skill.beginner, 1, 5);
	    
	    //white
	    getTile(0,0).setFigure(new Rook(this, new Vector3(0,0), one));
	    getTile(1,0).setFigure(new Knight(this, new Vector3(1,0), one));
	    getTile(2,0).setFigure(new Bishop(this, new Vector3(2,0), one));
	    getTile(3,0).setFigure(new Queen(this, new Vector3(3,0), one));
	    getTile(4,0).setFigure(new King(this, new Vector3(4,0), one));
	    getTile(5,0).setFigure(new Bishop(this, new Vector3(5,0), one));
	    getTile(6,0).setFigure(new Knight(this, new Vector3(6,0), one));
	    getTile(7,0).setFigure(new Rook(this, new Vector3(7,0), one));
	    
	    for(int i=0; i<8; ++i)
	    	getTile(i,1).setFigure(new Pawn(this, new Vector3(i,1), one));
	    
	    //black
	    getTile(0,7).setFigure(new Rook(this, new Vector3(0,7), two));
	    getTile(1,7).setFigure(new Knight(this, new Vector3(1,7), two));
	    getTile(2,7).setFigure(new Bishop(this, new Vector3(2,7), two));
	    getTile(3,7).setFigure(new Queen(this, new Vector3(3,7), two));
	    getTile(4,7).setFigure(new King(this, new Vector3(4,7), two));
	    getTile(5,7).setFigure(new Bishop(this, new Vector3(5,7), two));
	    getTile(6,7).setFigure(new Knight(this, new Vector3(6,7), two));
	    getTile(7,7).setFigure(new Rook(this, new Vector3(7,7), two));
	    
	    for(int i=0; i<8; ++i)
	    	getTile(i,6).setFigure(new Pawn(this, new Vector3(i,6), two));
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
	
	public void checkCollision(Vector3 v) throws OutOfChessboardException, FigureCollisionException, NoCollisionException
	{
		if(v.getX() > 7 || v.getX() < 0 ||
		   v.getY() > 7 || v.getY() < 0)
			throw new OutOfChessboardException("Kordynaty: "+v.toString()+" znajduj¹ siê poza plansz¹");
		else if(getTile(v).getFigure() != null)
			throw new FigureCollisionException("Kolizja z figur¹",  getTile(v).getFigure());
		else 
			throw new NoCollisionException("Brak kolizji");
	}

	public int getSize() {
		return size;
	}
	
	public PApplet getParent() {
		return parent;
	}
	
	public void moveFigure(Vector3 from, Vector3 to)
	{
		PApplet.println(getTile(from).getFigure() + " ==> "+to.toChessString());	//wyswietlenie na konsoli
		getTile(to).setFigure( getTile(from).getFigure() ); //ruch figury na pole
		getTile(from).setFigure( null );					//usuniecie figury z poprzedniego pola
		getTile(to).getFigure().setPosition(to); 			//nadanie nowej pozycji
		getTile(to).getFigure().moved();
	}
	
	public Element saveXML(Document document)
	{

            // root element
            Element root = document.createElement("Chessboard");
            
            Element players = document.createElement("Players");
            	Element player1 = one.saveXML(document);
            	Element player2 = two.saveXML(document);
            	players.appendChild(player1);
            	players.appendChild(player2);
            root.appendChild(players);
            
            Element figures = document.createElement("Figures");
            List<Vector3> tiles = getOccupiedTiles();
            for(int i=0; i<tiles.size(); ++i)
                figures.appendChild(getTile(tiles.get(i)).getFigure().saveXML(document) );
            root.appendChild(figures);

            return root;
	}
}
