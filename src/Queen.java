import java.util.List;

import processing.core.PApplet;

public class Queen extends Figure {

	
	public Queen(PApplet parent, Chessboard chessboard, Vector3 position, Player player) {
		super(parent, chessboard, position, player);

		switch(player.getColor())
		{
			case white: super.setShape("chess_svgs/white_queen.svg");	break;
			case black: super.setShape("chess_svgs/black_queen.svg");	break;
			default: throw new FileMissingException("Brak pliku z podanym kolorem figury"); 
		}
	}
	
	@Override
	public List<Vector3> getPossibleMoves() {

		int[] tabX = {1,-1,0,0,1,1,-1,-1};
		int[] tabY = {0,0,1,-1,1,-1,1,-1};

		return standardProcedure(tabX,tabY,chessboard.getSize(),CollisionEvent.both);
	}

}