import java.util.List;
import processing.core.PApplet;

public class Bishop extends Figure {

	
	public Bishop(PApplet parent, Chessboard chessboard, Vector3 position, Player player) {
		super(parent, chessboard, position, player);

		switch(player.getColor())
		{
			case white: super.setShape("chess_svgs/white_knight.svg");	break;
			case black: super.setShape("chess_svgs/black_knight.svg");	break;
			default: break;
		}

	}

	@Override
	public List<Vector3> getPossibleMoves() {
		int[] tabX = {1,1,-1,-1};
		int[] tabY = {1,-1,1,-1};
		/*boolean collision = false;
		
		for(int j=0; j<tabX.length; ++j)
		{
			for(int i=1; i<chessboard.getSize(); ++i)
			{
				v = new Vector3(position.getX()+i*tabX[j], position.getY()+i*tabY[j]);
				
				try 
				{
					if(collision  == false)
						if(chessboard.checkCollision(v) == null)
							list.add(v);
						else
						{
							if(chessboard.checkCollision(v).getPlayer().getId() != player.getId())
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
		}*/
		return standardProcedure(tabX,tabY,chessboard.getSize(),true);
	}
	
	

}
