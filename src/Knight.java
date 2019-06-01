import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;

public class Knight extends Figure{

	public Knight(PApplet parent, Vector3 position, Color color) {
		super(parent, position, color);

		switch(super.color)
		{
			case white: super.setShape("chess_svgs/white_knight.svg");	break;
			case black: super.setShape("chess_svgs/black_knight.svg");	break;
		}
	}

	@Override
	public List<Vector3> getPossibleMoves() {

		List<Vector3> list= new ArrayList<Vector3>();
		list.add(new Vector3(position.getX()+1, position.getY()+2));
		list.add(new Vector3(position.getX()+1, position.getY()-2));
		list.add(new Vector3(position.getX()-1, position.getY()+2));
		list.add(new Vector3(position.getX()-1, position.getY()-2));
		list.add(new Vector3(position.getX()+2, position.getY()+1));
		list.add(new Vector3(position.getX()+2, position.getY()-1));
		list.add(new Vector3(position.getX()-2, position.getY()+1));
		list.add(new Vector3(position.getX()-2, position.getY()-1));
		return super.removeAnomalies(list);
	}

}