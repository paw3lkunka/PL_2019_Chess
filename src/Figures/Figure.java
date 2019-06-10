package Figures;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import Exceptions.FigureCollisionException;
import Exceptions.NoCollisionException;
import Exceptions.OutOfChessboardException;
import Player.Player;
import Processing.Chessboard;
import Processing.TColor;
import Processing.Tile;
import Processing.Vector3;
import XML.XMLSerializable;
import processing.core.PShape;

/**
 * The collision event enum.
 * @author Piotr Ruciñski
 */
enum CollisionEvent {
	/** The attack. */
	attack,
	/** The move. */
	move,
	/** The both. */
	both;
}

/**
 * The Class Figure.
 * @author Piotr Ruciñski
 */
public abstract class Figure implements XMLSerializable {

	/** The chessboard. */
	private final Chessboard chessboard;
	
	/** The player. */
	private final Player player;
	
	/** The position. */
	private Vector3 position;
	
	/** The shape. */
	private PShape shape;
	
	/** The selection. */
	private boolean selection;
	
	/** The moved. */
	private boolean moved;
	
	/**
	 * Instantiates a new figure.
	 *
	 * @param chessboard the chessboard
	 * @param position the position on chessboard
	 * @param player the player it belongs to
	 */
	public Figure(Chessboard chessboard, Vector3 position, Player player) {
		this.chessboard = chessboard;
		this.player = player;
		this.position = position;
		this.selection = false;
		this.moved = false;
	}
	
	
	/**
	 * Gets the possible moves.
	 *
	 * @return the possible moves in Vector3 form
	 */
	public abstract List<Vector3> getPossibleMoves();
	
	/** 
	 * Creates label of figure to TUI
	 * 
	 * @return String containing label
	 */
	public abstract String toString();
	//public abstract Element saveXML();

	/* (non-Javadoc)
	 * @see XML.XMLSerializable#saveXML(org.w3c.dom.Document)
	 */
	public Element saveXML(Document document) {

            // root element
            Element root = document.createElement("Figure");
            	root.appendChild(getPosition().saveXML(document));
            
            Element idgracza = document.createElement("PlayerID");
            idgracza.appendChild(document.createTextNode( getPlayer().getId().toString() ));
            root.appendChild(idgracza);

        return root;
            
	}
	
	/**
	 * Display possible to move tiles on Processing GUI.
	 */
	public void displayPossibleToMoveTiles()
	{
		List<Vector3> vList = getPossibleMoves();
		for(int i=0; i<vList.size(); ++i)
		{
			Vector3 v = new Vector3(vList.get(i).getX()*50,// - position.getX()*50,		
									-0.01f,
									vList.get(i).getY()*50// - position.getY()*50
									);

			new Tile( chessboard.getParent(), v, TColor.aGreen).display();
		}
	}
	
	/**
	 * Switch figure selection.
	 */
	public void switchSelection()
	{
		this.selection = !selection;
	}
	
	/**
	 * Moved sets moved to true.
	 */
	public void moved()
	{
		this.moved = true;
	}
	
	/**
	 * Standard procedure of checking collisions on given distance.
	 *
	 * @param tabX the tab X representing single movement in X direction
	 * @param tabY the tab Y representing single movement in Y direction
	 * @param distance the distance to check collision
	 * @param cevent determines type of movement
	 * @return the list
	 */
	public List<Vector3> standardProcedure(int[] tabX, int[] tabY, int distance,  CollisionEvent cevent) {
		
		List<Vector3> list= new ArrayList<Vector3>();
		boolean collision = false;
		Vector3 v;
		
		for(int j=0; j<tabX.length; ++j)
		{
			for(int i=1; i<=distance; ++i)
			{
				v = new Vector3(position.getX()+i*tabX[j], position.getY()+i*tabY[j]);
				
				try 
				{
					if(collision == false)
						chessboard.checkCollision(v);
				}
				catch (OutOfChessboardException e)
				{
					collision = true;
				}
				catch (NoCollisionException e)
				{
					if(cevent != CollisionEvent.attack)
						list.add(v);
				}
				catch (FigureCollisionException e)
				{
					if(e.getCollidedFigure().getPlayer().getId() != player.getId() 
					  && cevent != CollisionEvent.move)
						list.add(v);
					collision = true;
				}
			}
			collision = false;
		}
		return list;
	}

	/**
	 * Gets the player.
	 *
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}
	
	/**
	 * Gets the position.
	 *
	 * @return the position
	 */
	public Vector3 getPosition() {
		return position;
	}

	/**
	 * Sets the position.
	 *
	 * @param position the new position
	 */
	public void setPosition(Vector3 position) {
		this.position = position;
	}

	/**
	 * Gets the shape.
	 *
	 * @return the shape
	 */
	public PShape getShape() {
		return shape;
	}

	/**
	 * Sets the shape.
	 *
	 * @param path the new shape
	 */
	public void setShape(String path) {
		this.shape = chessboard.getParent().loadShape(path);
	}

	/**
	 * Gets the chessboard.
	 *
	 * @return the chessboard
	 */
	public Chessboard getChessboard() {
		return chessboard;
	}

	/**
	 * Gets the moved.
	 *
	 * @return the moved
	 */
	public boolean getMoved() {
		return moved;
	}

	/**
	 * Sets the moved.
	 *
	 * @param moved the new moved
	 */
	public void setMoved(boolean moved) {
		this.moved = moved;
	}

}
