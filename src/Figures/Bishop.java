package Figures;

import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import Player.Player;
import Processing.Chessboard;
import Processing.Vector3;

/**
 * The Class Bishop.
 * @author Piotr Ruciñski
 */
public class Bishop extends Figure {

	
	/**
	 * Instantiates a new bishop.
	 *
	 * @param chessboard the chessboard
	 * @param position the position on chessboard
	 * @param player the player it belongs to
	 */
	public Bishop(Chessboard chessboard, Vector3 position, Player player) {
		super(chessboard, position, player);

		switch(player.getColor())
		{
			case white: super.setShape("chess_svgs/white_bishop.svg");	break;
			case black: super.setShape("chess_svgs/black_bishop.svg");	break;
			default: break;
		}

	}

	/* (non-Javadoc)
	 * @see Figures.Figure#getPossibleMoves()
	 */
	@Override
	public List<Vector3> getPossibleMoves() {
		int[] tabX = {1,1,-1,-1};
		int[] tabY = {1,-1,1,-1};

		return standardProcedure(tabX,tabY, getChessboard().getSize(), CollisionEvent.both);
	}
	
	/* (non-Javadoc)
	 * @see Figures.Figure#toString()
	 */
	public String toString()
	{
		switch ( getPlayer().getId() )
		{
		case one: return "1Bi";
		case two: return "2Bi";
		default:  return " Bi";
		}
	}

	
	/* (non-Javadoc)
	 * @see Figures.Figure#saveXML(org.w3c.dom.Document)
	 */
	public Element saveXML(Document document) {

        Element root = super.saveXML(document);
        root.setAttribute("figure", "Bishop");

        return root;

	}



}
