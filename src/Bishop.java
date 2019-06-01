import java.util.ArrayList;
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
		List<Vector3> list= new ArrayList<Vector3>();
		Vector3 v;
		int[] tabX = {1,1,-1,-1};
		int[] tabY = {1,-1,1,-1};
		boolean[] collision = {false,false,false,false};
		
		for(int j=0; j<4; ++j)
			for(int i=1; i<=7; ++i)
			{
				v = new Vector3(position.getX()+i*tabX[j], position.getY()+i*tabY[j]);
				PApplet.println(v);
				if(collision[j]  == false)
					if(chessboard.checkCollision(v) == false)
						list.add(v);
					else
						collision[j] = true;
					
				/*if(collision[1] == false)
					list.add(new Vector3(position.getX()+i, position.getY()-i));
				if(collision[2] == false)
					list.add(new Vector3(position.getX()-i, position.getY()+i));
				if(collision[3] == false)
					list.add(new Vector3(position.getX()-i, position.getY()-i));*/
	
			}
		return list; //super.removeAnomalies(list);
	}
	
	

}
