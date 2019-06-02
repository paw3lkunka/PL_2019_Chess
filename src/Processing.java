//import java.util.Random;

import processing.core.PApplet;
import processing.core.PImage;

public class Processing extends PApplet /*implements Runnable*/ {
	Program program;
	PImage bg;
	Chessboard c;
	Figure selection;
	
	public void run(Program program) {
		this.program = program;
		program.setVisible(false);
		PApplet.main("Processing");
	}
	
	public Processing() {
		super();
	}
	
    public void settings(){
    	//fullScreen(P3D);
    	size(400, 400, P3D); 
    }

    public void setup(){    
    	  frameRate(30);
    	  c = new Chessboard(this, new Vector3(0,0,0));
    	  selection = null;
    	  /*bg = loadImage("sky.jpg");
    	  bg.resize(pixelWidth, pixelHeight);*/
    	 }

  
    
    public void draw() {
    	
    	  lights();
    	  background(127);
    	  
    	  //background(bg);
    	  
    	  translate(width/2, height/2, 0);
    	  rotateX(-PI/2);
    	  translate(-200,0,-200);
    	  //rotateX(PI/3);
    	  //drawCordinateSystem();
    	  c.display();
  		if(selection != null)
			selection.displayPossibleToMoveTiles();
    }	
    
    
  public void keyPressed() {
	  
	  if(key==27) {
		  //tu chcia³em zrobiæ wyjœcie do menu i chowanie tego gównianego appeltu ale oczywiœcie nie dzia³a
	  }
	  
	  switch(key)
	  {
	  case CODED:
		  switch(keyCode)
		  {
		  	case UP:	break;
		  	case DOWN:	break;
		  } break;
	  default:
		  switch(keyCode)
		  {
		  	case 'W': break;
		  	case 'S': break;
		  }
	  }
  }
  

  public void mouseClicked() {
  
	  int x = mouseX/50;
	  int y = mouseY/50;
	  

	  if( selection == null )
	  {
		  selection = c.getTile(x, y).getFigure();
		  if(selection != null)
			  selection.switchSelection();
	  }
	  else if( selection == c.getTile(x, y).getFigure() )
	  {
		  selection.switchSelection();
		  selection = null;
	  }
	  else if( selection != null && selection.getPossibleMoves().contains(new Vector3(x,y)))
	  {
		  c.moveFigure(selection.getPosition(), new Vector3(x,y));
		  selection.switchSelection();
		  selection = null;
	  }
	  else
	  {
		  selection.switchSelection();
		  selection = c.getTile(x, y).getFigure();
		  if(selection != null)
			  selection.switchSelection();
	  }
  }

  public void keyReleased() {
	  
	  switch(key)
	  {
	  case CODED:
		  if(keyCode == UP || keyCode == DOWN)
			  ;
		  break;
	  default:
		  if(keyCode == 'W' || keyCode == 'S')
			  ;
	  }
  	}  
  
  	public void drawCordinateSystem()
  	{
  	  strokeWeight(2);
  	  stroke(255,0,0);
  	  line(-100,0,0,100,0,0);
  	  stroke(0,255,0);
  	  line(0,-100,0,0,100,0);
  	  stroke(0,0,255);
  	  line(0,0,-100,0,0,100);
  	}
}
