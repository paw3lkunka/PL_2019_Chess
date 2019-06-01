import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;

public class Rook extends Figure {

	
	public Rook(PApplet parent, Chessboard chessboard, Vector3 position, Player player) {
		super(parent, chessboard, position, player);

		switch(player.getColor())
		{
			case white: super.setShape("chess_svgs/white_knight.svg");	break;
			case black: super.setShape("chess_svgs/black_knight.svg");	break;
			default: throw new FileMissingException("Brak pliku z podanym kolorem figury"); 
		}
	}
	
	@Override
	public List<Vector3> getPossibleMoves() {
		List<Vector3> list= new ArrayList<Vector3>();
		for(int i=-7; i<=7; ++i)
		{
			if(i!=0)
			{
				list.add(new Vector3(position.getX()+i, position.getY()));
				list.add(new Vector3(position.getX(), position.getY()+i));
			}	
		}
		return super.removeAnomalies(list);
	}

}
