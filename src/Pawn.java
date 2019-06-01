import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;

public class Pawn extends Figure {

	public Pawn(PApplet parent, Chessboard chessboard, Vector3 position, Player player) {
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

		switch(player.getId())
		{
			case one: 
				list.add(new Vector3(position.getX(), position.getY()+1));
				if(position.getY() == 1)
					list.add(new Vector3(position.getX(), position.getY()+2));
				break;
				
			case two:	
				list.add(new Vector3(position.getX(), position.getY()-1));	
				if(position.getY() == 6)
					list.add(new Vector3(position.getX(), position.getY()-2));
				break;
			default: throw new FileMissingException("Brak pliku z podanym kolorem figury");
		}
		
		return super.removeAnomalies(list);
	}
	
}
