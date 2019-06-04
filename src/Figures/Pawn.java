package Figures;

import java.util.ArrayList;
import java.util.List;
import Exceptions.FileMissingException;
import Player.Player;
import Processing.Chessboard;
import Processing.Vector3;

public class Pawn extends Figure {

	public Pawn(Chessboard chessboard, Vector3 position, Player player) {
		super(chessboard, position, player);

		switch(player.getColor())
		{
			case white: super.setShape("chess_svgs/white_pawn.svg");	break;
			case black: super.setShape("chess_svgs/black_pawn.svg");	break;
			default: throw new FileMissingException("Brak pliku z podanym kolorem figury"); 
		}
	}
	
	@Override
	public List<Vector3> getPossibleMoves() {

		int[] tabX = {-1,1};
		int[] tabY = {0};
		List<Vector3> list = new ArrayList<Vector3>();
		
		switch(getPlayer().getId())
		{
			case one:
				tabY = new int[] {1,1};
				break;
			case two:
				tabY = new int[] {-1,-1};
				break;
		}
		
		list = standardProcedure(tabX, tabY, 1, CollisionEvent.attack);
		tabX = new int[] {0};
		
		if(getMoved())
			list.addAll( standardProcedure(tabX, tabY, 1, CollisionEvent.move) );
		else
			list.addAll( standardProcedure(tabX, tabY, 2, CollisionEvent.move) );
		return list;
	}

	public String toString()
	{
		return getPlayer().getName()+" P  "+getPosition().toChessString();
	}
}
