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
		/*List<Vector3> list= new ArrayList<Vector3>();
		boolean collision = false;
		Vector3 v;
		for(int i=1; i<=2; ++i)
		{
			switch(player.getId())
			{
				case one:
						v = new Vector3(position.getX(), position.getY()+i);
						if(collision  == false && (i==1 || i==2 && position.getY() == 1))
							if(chessboard.checkCollision(v) == false)
								list.add(v);
							else
								collision = true;
						break;
					
				case two:
					v = new Vector3(position.getX(), position.getY()-i);
					if(collision  == false && (i==1 || i==2 && position.getY() == 6))
						if(chessboard.checkCollision(v) == false)
							list.add(v);
						else
							collision = true;
					break;
			}
		}*/
		int[] tabX = {0};
		int[] tabY = {0};
		switch(player.getId())
		{
			case one:
				tabY = new int[] {1};
				break;
			case two:
				tabY = new int[] {-1};
				break;
		}
		
		if(moved)
			return standardProcedure(tabX, tabY, 1, false);
		else
			return standardProcedure(tabX, tabY, 2, false);
	}
	
}
