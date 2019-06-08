package Figures;

import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import Exceptions.FileMissingException;
import Player.Player;
import Processing.Chessboard;
import Processing.Vector3;

public class Knight extends Figure {

	public Knight(Chessboard chessboard, Vector3 position, Player player) {
		super(chessboard, position, player);

		switch(player.getColor())
		{
			case white: super.setShape("chess_svgs/white_knight.svg");	break;
			case black: super.setShape("chess_svgs/black_knight.svg");	break;
			default: throw new FileMissingException("Brak pliku z podanym kolorem figury"); 
		}
	}

	@Override
	public List<Vector3> getPossibleMoves() {

		int[] tabX = {1,1,-1,-1,2,2,-2,-2};
		int[] tabY = {2,-2,2,-2,1,-1,1,-1};
		return standardProcedure(tabX,tabY,1,CollisionEvent.both);
	}

	public String toString()
	{
		switch ( getPlayer().getId() )
		{
		case one: return "1Kn";
		case two: return "2Kn";
		default:  return " Kn";
		}
	}
	
	public Element saveXML(Document document) {

        Element root = super.saveXML(document);
        root.setAttribute("figure", "Knight");

        return root;

	}


}
