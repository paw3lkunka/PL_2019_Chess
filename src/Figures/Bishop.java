package Figures;

import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import Player.Player;
import Processing.Chessboard;
import Processing.Vector3;

public class Bishop extends Figure {

	
	public Bishop(Chessboard chessboard, Vector3 position, Player player) {
		super(chessboard, position, player);

		switch(player.getColor())
		{
			case white: super.setShape("chess_svgs/white_bishop.svg");	break;
			case black: super.setShape("chess_svgs/black_bishop.svg");	break;
			default: break;
		}

	}

	@Override
	public List<Vector3> getPossibleMoves() {
		int[] tabX = {1,1,-1,-1};
		int[] tabY = {1,-1,1,-1};

		return standardProcedure(tabX,tabY, getChessboard().getSize(), CollisionEvent.both);
	}
	
	public String toString()
	{
		return getPlayer().getName()+" B "+getPosition().toChessString();
	}

	
	public Element saveXML(Document document) {

        Element root = super.saveXML(document);
        root.setAttribute("figure", "Bishop");

        return root;

	}

}
