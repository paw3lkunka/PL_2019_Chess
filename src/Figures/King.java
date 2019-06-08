package Figures;

import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import Exceptions.FileMissingException;
import Player.Player;
import Processing.Chessboard;
import Processing.Vector3;

public class King extends Figure {

	
	public King(Chessboard chessboard, Vector3 position, Player player) {
		super(chessboard, position, player);

		switch(player.getColor())
		{
			case white: super.setShape("chess_svgs/white_king.svg");	break;
			case black: super.setShape("chess_svgs/black_king.svg");	break;
			default: throw new FileMissingException("Brak pliku z podanym kolorem figury"); 
		}
	}
	
	@Override
	public List<Vector3> getPossibleMoves() {

		int[] tabX = {1,-1,0,0,1,1,-1,-1};
		int[] tabY = {0,0,1,-1,1,-1,1,-1};

		return standardProcedure(tabX,tabY,1, CollisionEvent.both);
	}

	public String toString()
	{
		switch ( getPlayer().getId() )
		{
		case one: return "1Ki";
		case two: return "2Ki";
		default:  return " Ki";
		}
	}
	
	public Element saveXML(Document document) {

        Element root = super.saveXML(document);
        root.setAttribute("figure", "King");

        return root;

	}


}