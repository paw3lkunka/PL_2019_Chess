//import java.util.Random;

import processing.core.PApplet;
import processing.core.PImage;

public class Processing extends PApplet {

	public static void main(String[] args) {
		PApplet.main("Processing");
	}

	PImage bg;
	Chessboard c;
	
    public void settings(){
    	fullScreen(P3D);
    	//size(640, 360, P3D); 
    }

    public void setup(){    
    	  frameRate(30);
    	  c = new Chessboard(this, new Vector3(0,0,0));
    	  /*bg = loadImage("sky.jpg");
    	  bg.resize(pixelWidth, pixelHeight);*/
    	 }

  
    
    public void draw() {
    	
    	  lights();
    	  background(127);
    	  
    	  //background(bg);
    	  
    	  translate(width/2, height/2, -60);
    	  rotateX(-PI/2);
    	  translate(-200,0,-200);
    	  //rotateX(PI/3);
    	  drawCordinateSystem();
    	  c.display();
    }	
    
    
  public void keyPressed() {

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
  

 /* public void mouseClicked() {
  }*/

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