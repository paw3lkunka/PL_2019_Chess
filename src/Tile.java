import Exceptions.ColorMissingException;
import processing.core.PApplet;

enum Color
{
	black,
	white,
	aGreen;
}


public class Tile extends PositionedObject 
{

	Color color;
	Figure figure;

	public Tile(PApplet parent, Vector3 pos, Color color) 
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
		
		if(figure != null)
			figure.display();
		super.parent.popMatrix();
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
