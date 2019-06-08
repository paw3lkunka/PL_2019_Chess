package Figures;

import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import Exceptions.FileMissingException;
import Player.Player;
import Processing.Chessboard;
import Processing.Vector3;

// TODO: Auto-generated Javadoc
/**
 * The Class Rook.
 * @author Piotr Ruciñski
 */
public class Rook extends Figure {

	
	/**
	 * Instantiates a new rook.
	 *
	 * @param chessboard the chessboard
	 * @param position the position on chessboard
	 * @param player the player it belongs to
	 */
	public Rook(Chessboard chessboard, Vector3 position, Player player) {
		super(chessboard, position, player);

		switch(player.getColor())
		{
			case white: super.setShape("chess_svgs/white_rook.svg");	break;
			case black: super.setShape("chess_svgs/black_rook.svg");	break;
			default: throw new FileMissingException("Brak pliku z podanym kolorem figury"); 
		}
	}
	
	/* (non-Javadoc)
	 * @see Figures.Figure#getPossibleMoves()
	 */
	@Override
	public List<Vector3> getPossibleMoves() {

		int[] tabX = {1,-1,0,0};
		int[] tabY = {0,0,1,-1};

		return standardProcedure(tabX,tabY, getChessboard().getSize(), CollisionEvent.both);
	}
	
	/* (non-Javadoc)
	 * @see Figures.Figure#toString()
	 */
	public String toString()
	{
		switch ( getPlayer().getId() )
		{
		case one: return "1Ro";
		case two: return "2Ro";
		default:  return " Ro";
		}
	}

	/* (non-Javadoc)
	 * @see Figures.Figure#saveXML(org.w3c.dom.Document)
	 */
	public Element saveXML(Document document) {

        Element root = super.saveXML(document);
        root.setAttribute("figure", "Rook");

        return root;
	}


}
