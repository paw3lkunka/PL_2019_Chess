package Figures;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import Exceptions.FileMissingException;
import Player.Player;
import Processing.Chessboard;
import Processing.Vector3;

// TODO: Auto-generated Javadoc
/**
 * The Class Pawn.
 * @author Piotr Ruciñski
 */
public class Pawn extends Figure {

	/**
	 * Instantiates a new pawn.
	 *
	 * @param chessboard the chessboard
	 * @param position the position on chessboard
	 * @param player the player it belongs to
	 */
	public Pawn(Chessboard chessboard, Vector3 position, Player player) {
		super(chessboard, position, player);

		switch(player.getColor())
		{
			case white: super.setShape("chess_svgs/white_pawn.svg");	break;
			case black: super.setShape("chess_svgs/black_pawn.svg");	break;
			default: throw new FileMissingException("Brak pliku z podanym kolorem figury"); 
		}
	}
	
	/* (non-Javadoc)
	 * @see Figures.Figure#getPossibleMoves()
	 */
	@Override
	public List<Vector3> getPossibleMoves() {

		int[] tabX = {-1,1};
		int[] tabY = {0};
		List<Vector3> list = new ArrayList<Vector3>();
		
		switch(getPlayer().getId())
		{
			case one:
				tabY = new int[] {1,1};
				break;
			case two:
				tabY = new int[] {-1,-1};
				break;
		}
		
		list = standardProcedure(tabX, tabY, 1, CollisionEvent.attack);
		tabX = new int[] {0};
		
		if(getMoved())
			list.addAll( standardProcedure(tabX, tabY, 1, CollisionEvent.move) );
		else
			list.addAll( standardProcedure(tabX, tabY, 2, CollisionEvent.move) );
		return list;
	}

	/* (non-Javadoc)
	 * @see Figures.Figure#toString()
	 */
	public String toString()
	{
		switch ( getPlayer().getId() )
		{
		case one: return "1Pa";
		case two: return "2Pa";
		default:  return " Pa";
		}
	}
	
	/* (non-Javadoc)
	 * @see Figures.Figure#saveXML(org.w3c.dom.Document)
	 */
	public Element saveXML(Document document) {

        Element root = super.saveXML(document);
        root.setAttribute("figure", "Pawn");

        return root;

	}


}
