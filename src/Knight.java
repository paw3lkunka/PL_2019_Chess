import java.util.List;

import processing.core.PApplet;

public class Knight extends Figure{

	public Knight(PApplet parent, Chessboard chessboard, Vector3 position, Player player) {
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

		/*List<Vector3> list= new ArrayList<Vector3>();
		list.add(new Vector3(position.getX()+1, position.getY()+2));
		list.add(new Vector3(position.getX()+1, position.getY()-2));
		list.add(new Vector3(position.getX()-1, position.getY()+2));
		list.add(new Vector3(position.getX()-1, position.getY()-2));
		list.add(new Vector3(position.getX()+2, position.getY()+1));
		list.add(new Vector3(position.getX()+2, position.getY()-1));
		list.add(new Vector3(position.getX()-2, position.getY()+1));
		list.add(new Vector3(position.getX()-2, position.getY()-1));*/
		int[] tabX = {1,1,-1,-1,2,2,-2,-2};
		int[] tabY = {2,-2,2,-2,1,-1,1,-1};
		return standardProcedure(tabX,tabY,1,true);
	}

}
