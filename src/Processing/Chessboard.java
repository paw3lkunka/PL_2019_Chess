package Processing;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import Exceptions.FigureCollisionException;
import Exceptions.NoCollisionException;
import Exceptions.OutOfChessboardException;
import Figures.Bishop;
import Figures.FColor;
import Figures.Figure;
import Figures.King;
import Figures.Knight;
import Figures.Pawn;
import Figures.Queen;
import Figures.Rook;
import Player.Player;
import Player.PlayerID;
import XML.XMLSerializable;
import processing.core.PApplet;

/**
 * The Class Chessboard.
 * Class operating with game data - initializes chessboard 
 * @author Piotr Ruciñski
 */
public class Chessboard extends PositionedObject  implements XMLSerializable {
	
	/** The size. */
	private final int size=8;
	
	/** The tiles. */
	private List<List<Tile>> tiles;
	
	/** The one. */
	private Player one;
	
	/** The two. */
	private Player two;
	
	/** The selection. */
	private Figure selection;
	
	/** The turn. */
	private PlayerID turn;
	
	/** The mode. */
	private Mode mode = Mode.Both;

	/**
	 * Instantiates a new chessboard.
	 *
	 * @param parent the instance of PApplet containing chessboard
	 * @param pos the position of chessboard in Applet
	 * @param one the player number one - white
	 * @param two the player number two - black
	 */
	public Chessboard(PApplet parent, Vector3 pos, Player one, Player two) {
		super(parent, pos);
  	  	this.selection = null;
  	  	this.turn = PlayerID.one;
	    this.one = one; 
	    this.two = two;

		tilesInit();
		figuresInit();
		if(mode != Mode.GUI)
			PApplet.println(toString());
		PApplet.println("Turn of player "+turn.toString());
	}

	/**
	 * Instantiates a new chessboard based on XML element.
	 *
	 * @param parent the instance of PApplet containing chessboard
	 * @param pos the position of chessboard in Applet
	 * @param element the element from XML
	 */
	public Chessboard(PApplet parent, Vector3 pos, Element element) {
		super(parent, pos);
		
  	  	this.selection = null;	
		tilesInit();
		
		NodeList nList = element.getElementsByTagName("Players").item(0).getChildNodes();
		NodeList gracze = ((Element) nList).getElementsByTagName("Player");
		this.one = new Player( (Element)gracze.item(0) , PlayerID.one , FColor.white);
		this.two = new Player( (Element)gracze.item(1) , PlayerID.two , FColor.black);

		nList = element.getElementsByTagName("Figures").item(0).getChildNodes();
		NodeList figury = ((Element) nList).getElementsByTagName("Figure");
		for(int i=0; i<figury.getLength(); ++i)
			addFigure((Element)figury.item(i));
		
		this.turn = PlayerID.valueOf( element.getElementsByTagName("Turn").item(0).getTextContent() );
		PApplet.println("Turn of player "+turn.toString());
	}
	
	/**
	 * Tiles init.
	 */
	private void tilesInit()
	{
		this.tiles = new ArrayList<List<Tile>>();
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
	}
	
	/**
	 * Figures init.
	 */
	private void figuresInit()
	{
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
	
	/**
	 * Display chessboard.
	 */
	public void display()
	{
		super.place();
		for(int i=0; i<size; ++i)
			for(int j=0; j<size; ++j)
				tiles.get(i).get(j).display();
	}
	
	
	/**
	 * Gets the occupied by figures tiles.
	 *
	 * @return the occupied tiles in Vector3 form.
	 */
	public List<Vector3> getOccupiedTiles() 
	{
		List<Vector3> list= new ArrayList<Vector3>();
		for(int i=0; i<size; ++i)
			for(int j=0; j<size; ++j)
				if( tiles.get(j).get(i).getFigure() != null )
					list.add( new Vector3(i,j));
		
		return list;
	}
	
	
	/**
	 * Adds the figure to chessboard based on XML element.
	 *
	 * @param figure the figure we need to add to the chessboard
	 */
	public void addFigure(Element figure)		//dodaje figure na plansze
	{
		Player player = null;
		//PApplet.print( figure.getElementsByTagName("PlayerID").item(0).getTextContent() );
		switch(figure.getElementsByTagName("PlayerID").item(0).getTextContent()) {
		case "one":
			player = one;
			break;
		case "two":
			player = two;
			break;
		}
			
		
		Vector3 pos = new Vector3( (Element)(figure.getElementsByTagName("Vector3").item(0)) );
		
		switch(figure.getAttributes().item(0).getTextContent()) 
		{
		case "Rook": 	getTile(pos).setFigure( new Rook(this, pos, player) );	break;
		case "Queen": 	getTile(pos).setFigure( new Queen(this, pos, player) );	break;
		case "Bishop":	getTile(pos).setFigure( new Bishop(this, pos, player) );	break;
		case "King":	getTile(pos).setFigure( new King(this, pos, player) );	break;
		case "Knight": 	getTile(pos).setFigure( new Knight(this, pos, player) );	break;
		case "Pawn": 	getTile(pos).setFigure( new Pawn(this, pos, player) );	break;
		}
	}
	
	/**
	 * Check collision.
	 *
	 * @param v the vector3
	 * @throws OutOfChessboardException the out of chessboard exception
	 * @throws FigureCollisionException the figure collision exception
	 * @throws NoCollisionException the no collision exception
	 */
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
	  
	
  	/**
	   * Change turn.
	   */
	  public void changeTurn() 
	{
  		if(turn == PlayerID.one)
  			turn = PlayerID.two;
  		else
  			turn = PlayerID.one;

		  PApplet.println("Turn of player "+turn.toString());
  	}
	
	/**
	 * Move figure from tile to tile in Vector3 form.
	 *
	 * @param from - Vector3 of from tile
	 * @param to - Vector3 of to tile
	 */
	public void moveFigure(Vector3 from, Vector3 to)
	{
		getTile(to).setFigure( getTile(from).getFigure() ); //ruch figury na pole
		getTile(from).setFigure( null );					//usuniecie figury z poprzedniego pola
		getTile(to).getFigure().setPosition(to); 			//nadanie nowej pozycji
		getTile(to).getFigure().moved();
		
		if(mode != Mode.GUI)
			PApplet.println(toString());
		PApplet.println(getTile(to).getFigure() +"  "+ from.toChessString() + " ==> "+to.toChessString());	//wyswietlenie na konsoli
		
		Player winner = checkEnd();
		if(winner != null)
			{
			PApplet.println(winner.toString()+" won");
			//tutaj rzeczy do wygranej, trzeba jakos do sqla zapisac tych gosci
			one.setGames(one.getGames()+1);
			two.setGames(two.getGames()+1);
			winner.setWins(winner.getWins()+1);
			
			try {
				if(one == winner) {
					((Processing)parent).getProgram().getMySql().updateWin(one.getName());
					((Processing)parent).getProgram().getMySql().updateLoss(two.getName());
				} else {
					((Processing)parent).getProgram().getMySql().updateWin(two.getName());
					((Processing)parent).getProgram().getMySql().updateLoss(one.getName());
				}
			} catch(SQLException e) {
				e.printStackTrace();	
			} finally {
				((Processing)parent).exit();
			}
		
			}
	}
	
	/**
	 * @see XML.XMLSerializable#saveXML(org.w3c.dom.Document)
	 */
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
            
            Element tura = document.createElement("Turn");
            tura.appendChild(document.createTextNode(turn.toString()));
            root.appendChild(tura);

            return root;
	}
	
	/**
	 * Interaction based on position of mouse / chosen with keyboard.
	 *
	 * @param x the x
	 * @param y the y
	 */
	public void interaction(int x, int y) 
	{
		  if( selection == null )							//Nic nie jest wybrane -> mo¿liwoœæ wyboru figury
		  {
			  selection = getTile(x, y).getFigure();		
			  if(selection != null && selection.getPlayer().getId() == turn)
				  selection.switchSelection();
			  else
				  selection = null;
		  }
		  else if( selection == getTile(x, y).getFigure() )		//Wybrana jakaœ figura i klikniêcie na t¹ sam¹ figurê -> odklikniêcie
		  {
			  selection.switchSelection();
			  selection = null;
		  }
		  else if( selection != null && selection.getPossibleMoves().contains(new Vector3(x,y))) //Wybrana jakaœ figura i klikniêcie na pole ruchu -> ruch figury i zmiana tury
		  {
			  moveFigure(selection.getPosition(), new Vector3(x,y));
			  changeTurn();
			  selection.switchSelection();
			  selection = null;
		  }
		  else		//Wybrana jakaœ figura i klikniêcie na inn¹ -> mo¿liwoœæ wyboru innej figury
		  {
			  selection.switchSelection();
			  selection = getTile(x, y).getFigure();
			  if(selection != null && selection.getPlayer().getId() == turn)
				  selection.switchSelection();
			  else
				  selection = null;
		  }
	}
	
	/**
	 * Check end. If the game is over.
	 *
	 * @return the player who won
	 */
	public Player checkEnd() 
	{
		boolean onelife = false;
		boolean twolife = false;
		List<Vector3> tiles = getOccupiedTiles();
		for(int i=0; i<tiles.size(); ++i)
			if( getTile(tiles.get(i)).getFigure().getClass().getName() == "Figures.King" )
				if( getTile(tiles.get(i)).getFigure().getPlayer().getId() == PlayerID.one )
					onelife = true;
				else
					twolife = true;
		
		if( !onelife )
			return two;
		else if( !twolife )
			return one;
		else
			return null;
	}	
	
	/** 
	 * Creates string containing chessboard in TUI form
	 * 
	 * @return string containing current chessboard state.
	 */
	public String toString()
	{
		String str = "\n";
		for(int j=0; j<size; ++j)
			for(int k=0; k<3; ++k, str+="\n")
				for(int i=0; i<size; ++i)
					str += tiles.get(j).get(i).toLinesString().get(k);
		return str;
	}
	
	/**
	 * Input with keyboard.
	 */
	public void input()
	{
		int y=0;
		int x=0;
		
		try {
			while(!(x>0 && x<=size && y>0 && y<=size))
			{
			PApplet.print("Podaj pozycje x , y: ");
			x = ((Processing)parent).getIn().nextInt();
			y = ((Processing)parent).getIn().nextInt();
			}
			interaction((x-1)%size,(y-1)%size);
		} catch(java.util.InputMismatchException e) {
			PApplet.print("Spróbuj podaæ pozycje w  postaci pary liczb");
		}

	}
	
	/**
	 * Gets the tile.
	 *
	 * @param i the vertical index on chessboard
	 * @param j the horizontal index on chessboard
	 * @return the tile
	 */
	//====================get set====================
	public Tile getTile(int i, int j)
	{
		return tiles.get(j).get(i);
	}

	/**
	 * Gets the tile.
	 *
	 * @param v the vector3 of tile position
	 * @return the tile
	 */
	public Tile getTile(Vector3 v)
	{
		return tiles.get((int) v.getY()).get((int) v.getX());
	}

	/**
	 * Gets the size.
	 *
	 * @return the size
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * Gets the parent.
	 *
	 * @return the parent
	 */
	public PApplet getParent() {
		return parent;
	}

	/**
	 * Gets the selection.
	 *
	 * @return the selection
	 */
	public Figure getSelection() {
		return selection;
	}

	/**
	 * Sets the selection.
	 *
	 * @param selection the new selection
	 */
	public void setSelection(Figure selection) {
		this.selection = selection;
	}

	/**
	 * Gets the turn.
	 *
	 * @return the turn
	 */
	public PlayerID getTurn() {
		return turn;
	}

	/**
	 * Sets the turn.
	 *
	 * @param turn the new turn
	 */
	public void setTurn(PlayerID turn) {
		this.turn = turn;
	}

	/**
	 * Gets the one.
	 *
	 * @return the one
	 */
	public Player getOne() {
		return one;
	}

	/**
	 * Gets the two.
	 *
	 * @return the two
	 */
	public Player getTwo() {
		return two;
	}

	/**
	 * Gets the mode.
	 *
	 * @return the mode
	 */
	public Mode getMode() {
		return mode;
	}
	
	/**
	 * Sets the mode.
	 *
	 * @param mode the new mode
	 */
	public void setMode(Mode mode) {
		this.mode = mode;
	}

}
