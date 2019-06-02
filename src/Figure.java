import java.util.ArrayList;
import java.util.List;

import Exceptions.OutOfChessboardException;
import processing.core.PApplet;
import processing.core.PShape;

public abstract class Figure {

	protected final PApplet parent;
	Chessboard chessboard;
	Player player;
	Vector3 position;
	PShape shape;
	boolean selection;
	boolean moved;
	
	public Figure(PApplet parent, Chessboard chessboard, Vector3 position, Player player) {
		this.parent = parent;
		this.chessboard = chessboard;
		this.player = player;
		this.position = position;
		this.selection = false;
		this.moved = false;
	}
	
	public abstract List<Vector3> getPossibleMoves();
	
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
		this.shape = parent.loadShape(path);
	}
	
	public List<Vector3> removeAnomalies(List<Vector3> list)
	{
		for(int i=0; i<list.size(); ++i)
			if(list.get(i).getX() > 7 || list.get(i).getX() < 0 ||
			   list.get(i).getY() > 7 || list.get(i).getY() < 0)
				{
				list.remove(i);
				i--;
				}
		
		return list;
	}
	
	public void display()
	{
		parent.pushMatrix();
		parent.translate(0,-0.02f,0);
		parent.shape(getShape(), 5, 5); //pliki svg s¹ wielkoœci 40x40, a pola 50x50 st¹d offset 5,5
		/*if(selection)
			displayPossibleToMoveTiles();*/
		parent.popMatrix();
	}

	public void displayPossibleToMoveTiles()
	{
		List<Vector3> vList = getPossibleMoves();
		
		//parent.rotateX(PApplet.PI/2);
		for(int i=0; i<vList.size(); ++i)
		{
			Vector3 v = new Vector3(vList.get(i).getX()*50,// - position.getX()*50,		
									-0.01f,
									vList.get(i).getY()*50// - position.getY()*50
									);

			new Tile( parent, v, Color.aGreen).display();
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

	public Player getPlayer() {
		return player;
	}
	
	public List<Vector3> standardProcedure(int[] tabX, int[] tabY, int distance, boolean fightOnCollision) {
		
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
					if(collision  == false)
						if(chessboard.checkCollision(v) == null)
							list.add(v);
						else
						{
							if(chessboard.checkCollision(v).getPlayer().getId() != player.getId() && fightOnCollision)
								list.add(v);
							collision = true;
						}
				}
				catch (OutOfChessboardException e)
				{
					collision = true;
				}
			}
			collision = false;
		}
		return list;
	}

}
