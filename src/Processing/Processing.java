package Processing;


import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.KeyListener;

import Menu.Program;
import Player.*;
import XML.XMLChessboardReader;
import XML.XMLWriter;
import processing.core.PApplet;

/**
 * The Class Processing.
 * @author Piotr Ruci�ski
 * @author Pawe� Kunka
 */
public class Processing extends PApplet {
	
	/** The program. */
	private Program program;
	
	/** The c. */
	//private PImage bg;
	private Chessboard c;
	
	/** The is running. */
	private boolean isRunning = false;
	
	/** The in. */
	private Scanner in;
	
	/** The t 1. */
	private InputThread t1 = new InputThread();
	
	/**
	 * Instantiates a new processing.
	 *
	 * @param program the program
	 */
	public Processing(Program program) {
		super();
		this.program = program;
	}
	
    /* (non-Javadoc)
     * @see processing.core.PApplet#settings()
     */
    public void settings(){
    	//fullScreen(P3D);
    	size(400, 400, P3D); 
    }

    /* (non-Javadoc)
     * @see processing.core.PApplet#setup()
     */
    public void setup(){
    	  frameRate(30);
    	  in = new Scanner(System.in);
    	  /*newGame(
    			  new Player("Bia�a", PlayerID.one, FColor.white, Sex.female, Skill.profesional, 10, 12),
    			  new Player("Czarny", PlayerID.two, FColor.black, Sex.male, Skill.beginner, 1, 5)
    			  );*///new XMLChessboardReader("saves/autosave").loadChessboard(this, new Vector3(0,0,0)); //new Chessboard(this, new Vector3(0,0,0));
    	  getSurface().setResizable(false);
		  getSurface().setAlwaysOnTop(true);
    	  /*bg = loadImage("sky.jpg");
    	  bg.resize(pixelWidth, pixelHeight);*/
		  t1.stop();
    	 }

  
    
    /* (non-Javadoc)
     * @see processing.core.PApplet#draw()
     */
    public void draw() {
    	
    	lights();
    	background(127);
    	  
    	//background(bg);
    	  
    	  
    	translate(width/2, height/2, 0);
    	rotateX(-PI/2);
    	translate(-200,0,-200);
    	if(c != null)
    		switch(c.getMode())
    		{
    		case GUI: 
		    	c.display();
			  	if(getC().getSelection() != null)
			  		getC().getSelection().displayPossibleToMoveTiles();
    			break;
    			
    		case TUI:
    			//new Thread();
    			//c.input();
    			break;
    			
    		case Both: 
		    	c.display();
			  	if(getC().getSelection() != null)
			  		getC().getSelection().displayPossibleToMoveTiles();
    			break;
    		}
    		
    }	
    
    /* (non-Javadoc)
     * @see processing.core.PApplet#mouseClicked()
     */
    public void mouseClicked() {
    	  
  	  int x = mouseX/50;
  	  int y = mouseY/50;
  	  
  	  if(c.getMode() != Mode.TUI)
  		  c.interaction(x, y);

    }
    
    /* (non-Javadoc)
     * @see processing.core.PApplet#keyPressed()
     */
    public void keyPressed() {
  	  
  	  if(key==27) {
  		  exit();
  		key = 0;
  	  }
    }
    
  /**
   * Run. Set game window visible and playable, hide menu JFrame.
   */
  //====================OBS�UGA OKNA====================
	public void run() {			//nale�y u�y� przy ka�dorazowym wywo�aniu okna
		program.setVisible(false);
		getSurface().setVisible(true);
		resume();
		loop();
		getSurface().setLocation(100, 100);
		setIsRunning(true);
		if(c.getMode() != Mode.GUI)
			t1.start();
  	    
	}
  
	/* (non-Javadoc)
	 * @see processing.core.PApplet#exit()
	 */
	public void exit() {		//do ukrycia okna gry
		  t1.stop();
		  program.setVisible(true);
		  noLoop();
		  getSurface().setVisible(false);
	}
  
	/**
	 * End. Close game in valid way. Make backup saves/autosave.
	 */
	public void end()			//koniec dzia�ania programu
	{
		  try {
			  saveGame("saves/autosave");
		  } catch(Exception e) {
			  e.printStackTrace();
		  } finally  {
			  t1.stop();
			  try {
						TimeUnit.MILLISECONDS.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
			  
			  in.close();
			  super.exit();
		  }
	}

  /**
   * Save game.
   *
   * @param path the path
   */
  //====================OBS�UGA GRY====================
  public void saveGame(String path) {
	  new XMLWriter<Chessboard>(path).save(c);
  }
  	
  /**
   * Load game.
   *
   * @param path the path of the file to load
   */
  public void loadGame(String path) {
	  new XMLChessboardReader(path).loadChessboard(this, new Vector3(0,0,0));
  }
  
  /**
   * New game.
   *
   * @param one the player one - white
   * @param two the player two - black
   */
  public void newGame(Player one, Player two) {
	  c = new Chessboard(this, new Vector3(0,0,0), one, two );
  }
  
  /**
   * New game.
   */
  public void newGame() {		//uzywa� tylko je�li gra ju� jest i nie zmieniono graczy
	  c = new Chessboard(this, new Vector3(0,0,0), c.getOne(), c.getTwo() );
  }
  
  
  
/**
 * Gets the checks if is running.
 *
 * @return the checks if is running
 */
//====================get set====================
	public boolean getIsRunning() {
		return isRunning;
	}

	/**
	 * Sets the checks if is running.
	 *
	 * @param isRunning the new checks if is running
	 */
	public void setIsRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	/**
	 * Gets the c.
	 *
	 * @return the c
	 */
	public Chessboard getC() {
		return c;
	}

	/**
	 * Sets the c.
	 *
	 * @param c the new c
	 */
	public void setC(Chessboard c) {
		this.c = c;
	}

	/**
	 * Gets the program.
	 *
	 * @return the program
	 */
	public Program getProgram() {
		return program;
	}

	/**
	 * Gets the in.
	 *
	 * @return the in
	 */
	public Scanner getIn() {
		return in;
	}

	
	/**
	 * The Class InputThread.
	 */
	//====================klasa w�tku====================
	class InputThread implements Runnable {
	    
    	/** The exit. */
    	private volatile boolean exit = false;
	    
	    /* (non-Javadoc)
    	 * @see java.lang.Runnable#run()
    	 */
    	public void run() {
	        while(!exit){
	        	if(c != null)
	        		c.input();
	        }
	        
	        System.out.println("\nThread is stopped....");
	    }
	    
	    /**
    	 * Stop.
    	 */
    	public void stop(){
	        exit = true;
	        if(c != null)
	        	System.out.println("\nGame paused... type anything and press enter");
	    }
	    
	    /**
    	 * Start.
    	 */
    	public void start() {
    		
    		exit = false;
	    	run();
	    }
    	
	}
	
}
