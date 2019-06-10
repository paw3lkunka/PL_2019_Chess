package Processing;
import java.util.ArrayList;
import java.util.List;

import Exceptions.ColorMissingException;
import Figures.Figure;
import processing.core.PApplet;

/**
 * The Class Tile.
 * @author Piotr Ruciñski
 */
public class Tile extends PositionedObject 
{

	/** The color. */
	TColor color;
	
	/** The figure. */
	Figure figure;

	/**
	 * Instantiates a new tile.
	 *
	 * @param parent the parent
	 * @param pos the pos
	 * @param color the color
	 */
	public Tile(PApplet parent, Vector3 pos, TColor color) 
	{
		super(parent, pos);
		this.color = color;
	}
	
	
	
	/**
	 * Display tile.
	 */
	public void display()
	{
		super.parent.noStroke();
		this.fill();
		
		super.parent.pushMatrix();
		super.place();
		super.parent.rotateX(PApplet.PI/2);
		super.parent.square(0,0,50);
		
		displayFigure();
		
		super.parent.popMatrix();
	}

	
	/**
	 * Display figure.
	 */
	public void displayFigure()
	{
		if(figure != null) {
			parent.translate(0,-0.02f,0);
			parent.shape(figure.getShape(), 5, 5); //pliki svg s¹ wielkoœci 40x40, a pola 50x50 st¹d offset 5,5
		}
	}

	/**
	 * Gets the figure.
	 *
	 * @return the figure on the tile
	 */
	public Figure getFigure() {
		return figure;
	}

	/**
	 * Sets the figure.
	 *
	 * @param figure the new figure
	 */
	public void setFigure(Figure figure) {
		this.figure = figure;
	}
	
	/**
	 * Fill.
	 */
	public void fill()
	{
		switch(color)
		{
			case white: super.parent.fill(255, 255, 255, 255);	break;
			case black: super.parent.fill(  0,   0,   0, 255);	break;
			case aGreen:super.parent.fill(  0, 255,   0, 100);	break;
			default: throw new ColorMissingException("Brak podanego koloru pola");
		}
	}
	
	/**
	 * To lines string.
	 *
	 * @return the list of Strings representing tiles in TUI
	 */
	public List<String> toLinesString()
	{
		List<String> str = new ArrayList<String>();
		str.add("=====");
		if(figure == null)
			str.add("|   |");
		else
			str.add("|"+figure.toString()+"|");
		str.add("=====");
		return str;
	}
	
}
