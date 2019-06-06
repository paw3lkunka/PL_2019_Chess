package Processing;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import Figures.Figure;
import Menu.Program;
import Player.PlayerID;
import XML.XMLSerializable;
import XML.XMLWriter;
import processing.core.PApplet;

public class Processing extends PApplet implements XMLSerializable{
	private Program program;
	//private PImage bg;
	private Chessboard c;
	private Figure selection;
	private PlayerID turn;
	private boolean isRunning = false;
	
	
	
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

	public void run() {			//nale¿y u¿yæ przy ka¿dorazowym wywo³aniu okna
		program.setVisible(false);
		getSurface().setVisible(true);
		resume();
		loop();
		getSurface().setLocation(100, 100);
		setIsRunning(true);
	}
	
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
    	  c = new Chessboard(this, new Vector3(0,0,0));
    	  selection = null;
    	  turn = PlayerID.one;
    	  getSurface().setResizable(false);
		  getSurface().setAlwaysOnTop(true);
    	  println("Turn of player "+turn.toString());
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
    	c.display();
	  	if(selection != null)
	  		selection.displayPossibleToMoveTiles();
    }	
    
    
  public void keyPressed() {
	  
	  if(key==27) {
		  exit();
		  key = 0;
	  }
  }
  
  public void exit() {		//do ukrycia okna
	  program.setVisible(true);
	  noLoop();
	  getSurface().setVisible(false);
  }
  
  public void end()
  {
	  new XMLWriter<Processing>("saves/autosave").save(this);
	  super.exit();
  }

  public void mouseClicked() {
  
	  int x = mouseX/50;
	  int y = mouseY/50;
	  

	  if( selection == null )							//Nic nie jest wybrane -> mo¿liwoœæ wyboru figury
	  {
		  selection = c.getTile(x, y).getFigure();		
		  if(selection != null && selection.getPlayer().getId() == turn)
			  selection.switchSelection();
		  else
			  selection = null;
	  }
	  else if( selection == c.getTile(x, y).getFigure() )		//Wybrana jakaœ figura i klikniêcie na t¹ sam¹ figurê -> odklikniêcie
	  {
		  selection.switchSelection();
		  selection = null;
	  }
	  else if( selection != null && selection.getPossibleMoves().contains(new Vector3(x,y))) //Wybrana jakaœ figura i klikniêcie na pole ruchu -> ruch figury i zmiana tury
	  {
		  c.moveFigure(selection.getPosition(), new Vector3(x,y));
		  changeTurn();
		  selection.switchSelection();
		  selection = null;
	  }
	  else		//Wybrana jakaœ figura i klikniêcie na inn¹ -> mo¿liwoœæ wyboru innej figury
	  {
		  selection.switchSelection();
		  selection = c.getTile(x, y).getFigure();
		  if(selection != null && selection.getPlayer().getId() == turn)
			  selection.switchSelection();
		  else
			  selection = null;
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
  	
  	public void changeTurn() 
	{
  		if(turn == PlayerID.one)
  			turn = PlayerID.two;
  		else
  			turn = PlayerID.one;

		  println("Turn of player "+turn.toString());
  	}
	
	public Element saveXML(Document document)
	{
		
        // root element
        Element root = document.createElement("Game");
        document.appendChild(root);
      
        root.appendChild(c.saveXML(document));
        
        Element tura = document.createElement("Turn");
        tura.appendChild(document.createTextNode(turn.toString()));
        
        return root;
		/*try {
			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
			Document document = documentBuilder.newDocument();
			
            // root element
            Element root = document.createElement("Game");
            document.appendChild(root);
          
            root.appendChild(c.saveXML(document));
            
            Element tura = document.createElement("Turn");
            tura.appendChild(document.createTextNode(turn.toString()));
           
            DOMSource source = new DOMSource(document);
            StreamResult stream = new StreamResult(new File(path));
            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, stream);
          
            
		} catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }*/
	}
}
