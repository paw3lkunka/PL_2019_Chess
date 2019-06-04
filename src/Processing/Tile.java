package Processing;
import Exceptions.ColorMissingException;
import Figures.Figure;
import processing.core.PApplet;

public class Tile extends PositionedObject 
{

	TColor color;
	Figure figure;

	public Tile(PApplet parent, Vector3 pos, TColor color) 
	{
		super(parent, pos);
		this.color = color;
	}
	
	
	
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

	
	public void displayFigure()
	{
		if(figure != null) {
			parent.translate(0,-0.02f,0);
			parent.shape(figure.getShape(), 5, 5); //pliki svg s¹ wielkoœci 40x40, a pola 50x50 st¹d offset 5,5
		}
	}

	public Figure getFigure() {
		return figure;
	}

	public void setFigure(Figure figure) {
		this.figure = figure;
	}
	
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
	
}
