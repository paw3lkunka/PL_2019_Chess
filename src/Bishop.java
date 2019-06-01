import java.util.List;

import processing.core.PApplet;

public class Bishop extends Figure {

	public Bishop(PApplet parent, Vector3 position, Color color) {
		super(parent, position, color);

		switch(super.color)
		{
			case white: super.setShape("chess_svgs/white_knight.svg");	break;
			case black: super.setShape("chess_svgs/black_knight.svg");	break;
		}
	}

	@Override
	public List<Vector3> getPossibleMoves() {
		// TODO Auto-generated method stub
		return null;
	}

}
