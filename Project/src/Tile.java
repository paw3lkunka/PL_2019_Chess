import processing.core.PApplet;

enum Color
{
	black,
	white;
}


public class Tile extends PositionedObject 
{

	Vector3 color;
	Figure figure;

	public Tile(PApplet parent, Vector3 pos, Color color) 
	{
		super(parent, pos);
		switch(color)
		{
			case white: this.color = new Vector3(255,255,255); 	break;
			case black: this.color = new Vector3(0,0,0);		break;
		}
	}
	
	public void display()
	{
		super.parent.noStroke();
		super.parent.fill(color.getX(), color.getY(), color.getZ());
		
		super.parent.pushMatrix();
		super.place();
		super.parent.rotateX(PApplet.PI/2);
		super.parent.square(0,0,50);
		super.parent.popMatrix();
	}
	
	
}
