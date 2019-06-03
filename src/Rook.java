import java.util.List;

import processing.core.PApplet;

public class Rook extends Figure {

	
	public Rook(PApplet parent, Chessboard chessboard, Vector3 position, Player player) {
		super(parent, chessboard, position, player);

		switch(player.getColor())
		{
			case white: super.setShape("chess_svgs/white_rook.svg");	break;
			case black: super.setShape("chess_svgs/black_rook.svg");	break;
			default: throw new FileMissingException("Brak pliku z podanym kolorem figury"); 
		}
	}
	
	@Override
	public List<Vector3> getPossibleMoves() {
		//List<Vector3> list= new ArrayList<Vector3>();
		//Vector3 v;
		int[] tabX = {1,-1,0,0};
		int[] tabY = {0,0,1,-1};
		//boolean collision = false;
		
		/*for(int j=0; j<4; ++j)
			for(int i=1; i<=7; ++i)
			{
				v = new Vector3(position.getX()+i*tabX[j], position.getY()+i*tabY[j]);
				
				if(collision[j]  == false)
					if(chessboard.checkCollision(v) == false)
						list.add(v);
					else
						collision[j] = true;
			}*/
		return standardProcedure(tabX,tabY,chessboard.getSize(),CollisionEvent.both);//return list;
	}

}
