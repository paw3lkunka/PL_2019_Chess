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
import processing.core.PShape;

enum CollisionEvent {
	attack,
	move,
	both;
}

public abstract class Figure {

	private final Chessboard chessboard;
	private final Player player;
	private Vector3 position;
	private PShape shape;
	private boolean selection;
	private boolean moved;
	
	public Figure(Chessboard chessboard, Vector3 position, Player player) {
		this.chessboard = chessboard;
		this.player = player;
		this.position = position;
		this.selection = false;
		this.moved = false;
	}
	
	
	public abstract List<Vector3> getPossibleMoves();
	public abstract String toString();
	//public abstract Element saveXML();

	public Element saveXML(Document document) {

            // root element
            Element root = document.createElement("Figure");
            	root.appendChild(getPosition().saveXML(document));
            
            Element idgracza = document.createElement("PlayerID");
            idgracza.appendChild(document.createTextNode( getPlayer().getId().toString() ));
            root.appendChild(idgracza);

        return root;
            
	}
	
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
	
	public void switchSelection()
	{
		this.selection = !selection;
	}
	
	public void moved()
	{
		this.moved = true;
	}
	
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

	public Player getPlayer() {
		return player;
	}
	
	public Vector3 getPosition() {
		return position;
	}

	public void setPosition(Vector3 position) {
		this.position = position;
	}

	public PShape getShape() {
		return shape;
	}

	public void setShape(String path) {
		this.shape = chessboard.getParent().loadShape(path);
	}

	public Chessboard getChessboard() {
		return chessboard;
	}

	public boolean getMoved() {
		return moved;
	}

	public void setMoved(boolean moved) {
		this.moved = moved;
	}

}
