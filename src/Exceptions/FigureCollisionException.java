package Exceptions;

import Figures.Figure;

public class FigureCollisionException extends Exception {

	private static final long serialVersionUID = 1L;
	private final Figure figure;
	
	public FigureCollisionException(String msg, Figure figure) {
		super(msg);
		this.figure = figure;
	}
	
	public Figure getCollidedFigure() {
		return figure;
	}
		
}