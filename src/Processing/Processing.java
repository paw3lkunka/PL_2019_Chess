package Processing;


import Menu.Program;
import Player.*;
import XML.XMLChessboardReader;
import XML.XMLWriter;
import processing.core.PApplet;

public class Processing extends PApplet {
	private Program program;
	//private PImage bg;
	private Chessboard c;
	private boolean isRunning = false;
	
	//new Player("Bia³a", PlayerID.one, FColor.white, Sex.female, Skill.profesional, 10, 12);
	// new Player("Czarny", PlayerID.two, FColor.black, Sex.male, Skill.beginner, 1, 5);

	
	public Processing(Program program) {
		super();
		this.program = program;
	}
	
    public void settings(){
    	//fullScreen(P3D);
    	size(400, 400, P3D); 
    }

    public void setup(){
    	  frameRate(30);
    	  /*newGame(
    			  new Player("Bia³a", PlayerID.one, FColor.white, Sex.female, Skill.profesional, 10, 12),
    			  new Player("Czarny", PlayerID.two, FColor.black, Sex.male, Skill.beginner, 1, 5)
    			  );*///new XMLChessboardReader("saves/autosave").loadChessboard(this, new Vector3(0,0,0)); //new Chessboard(this, new Vector3(0,0,0));
    	  getSurface().setResizable(false);
		  getSurface().setAlwaysOnTop(true);
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
    	if(c != null)
    	{
	    	c.display();
		  	if(getC().getSelection() != null)
		  		getC().getSelection().displayPossibleToMoveTiles();
    	}
    }	
    
    public void mouseClicked() {
    	  
  	  int x = mouseX/50;
  	  int y = mouseY/50;
  	  
  	  c.interaction(x, y);

    }
    
    public void keyPressed() {
  	  
  	  if(key==27) {
  		  exit();
  		  key = 0;
  	  }
    }
    
  //====================OBS£UGA OKNA====================
	public void run() {			//nale¿y u¿yæ przy ka¿dorazowym wywo³aniu okna
		program.setVisible(false);
		getSurface().setVisible(true);
		resume();
		loop();
		getSurface().setLocation(100, 100);
		setIsRunning(true);
  	    
	}
  
	public void exit() {		//do ukrycia okna gry
		  program.setVisible(true);
		  noLoop();
		  getSurface().setVisible(false);
	}
  
	public void end()			//koniec dzia³ania programu
	{
		  try {
			  saveGame("saves/autosave");
		  } catch(Exception e) {
			  e.printStackTrace();
		  } finally  {
			  super.exit();
		  }
	}

  //====================OBS£UGA GRY====================
  public void saveGame(String path) {
	  new XMLWriter<Chessboard>(path).save(c);
  }
  	
  public void loadGame(String path) {
	  new XMLChessboardReader(path).loadChessboard(this, new Vector3(0,0,0));
  }
  
  public void newGame(Player one, Player two) {
	  c = new Chessboard(this, new Vector3(0,0,0), one, two );
  }
  
  public void newGame() {		//uzywaæ tylko jeœli gra ju¿ jest i nie zmieniono graczy
	  c = new Chessboard(this, new Vector3(0,0,0), c.getOne(), c.getTwo() );
  }
  
  
  
//====================get set====================
	public boolean getIsRunning() {
		return isRunning;
	}

	public void setIsRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	public Chessboard getC() {
		return c;
	}

	public void setC(Chessboard c) {
		this.c = c;
	}

	public Program getProgram() {
		return program;
	}
}
