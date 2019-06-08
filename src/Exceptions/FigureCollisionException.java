package Exceptions;

import Figures.Figure;

/**
 * The Class FigureCollisionException.
 * @author Piotr Ruciñski
 */
public class FigureCollisionException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The figure. */
	private final Figure figure;
	
	/**
	 * Instantiates a new figure collision exception.
	 *
	 * @param msg the message
	 * @param figure the figure it's colliding
	 */
	public FigureCollisionException(String msg, Figure figure) {
		super(msg);
		this.figure = figure;
	}
	
	/**
	 * Gets the collided figure.
	 *
	 * @return the collided figure
	 */
	public Figure getCollidedFigure() {
		return figure;
	}
		
}