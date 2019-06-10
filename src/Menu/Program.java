package Menu;
import java.awt.EventQueue;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Processing.Processing;
import SQL.MySql;
import processing.core.PApplet;

/**
 * The Class Program.
 * @author Pawe³ Kunka
 */
@SuppressWarnings("serial")
public class Program extends JFrame {
	
	/** The main menu panel. Reference to the JPanel extended JPanel representing main menu in this application. Unsecured singleton */
	private MainMenuPanel mmPanel;
	
	/** The game as reference to the instance of the Processing class. Unsecured singleton. */
	private Processing game;
	
	/** The my sql. Reference to the instance of MySql class made to access localhost:3306 dababase containig players table. Unsecured singleton. */
	private MySql mySql;


	/**
	 * Gets the my sql class.
	 *
	 * @return the my sql
	 */
	public MySql getMySql() {
		return mySql;
	}

	/**
	 * Gets the main menu panel.
	 *
	 * @return the main menu panel
	 */
	public MainMenuPanel getMmPanel() {
		return mmPanel;
	}

	/**
	 * Gets the game - processing reference.
	 *
	 * @return the game
	 */
	public Processing getGame() {
		return game;
	}
	
	/**
	 * Launch the application.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Program frame = new Program();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame of the program.
	 *
	 * @throws SQLException the SQL exception
	 */
	public Program() throws SQLException {
		
		
  	  	game = new Processing(this);
		String[] args =new String[] {"--location=2000,2000", "Szachy"};
    	PApplet.runSketch(args, game);
    	game.exit();
    	
    	mySql = new MySql();
    	
		setTitle("GRA W SZACHY");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosed(WindowEvent evt) {
				super.windowClosed(evt);
			}

			@Override
			public void windowClosing(WindowEvent evt) {
				int confirm = JOptionPane.showConfirmDialog(
					null, 
					"Czy na pewno chcesz zakoñczyæ?", 
					"Koñczymy zabawê?", 
					JOptionPane.YES_NO_OPTION, 
					JOptionPane.INFORMATION_MESSAGE
				);
				
				switch(confirm) {
				case JOptionPane.YES_OPTION:
					// coœ innego do za³atwienia przy zamykaniu
					try {
						game.end();
					} catch(NullPointerException ex) {
						
					}
					evt.getWindow().dispose();
					System.out.println("Closed");
					break;
				default:
					System.out.println("Closing has been cancelled");
					break;
				}
			}
			
		});
		setBounds(100, 100, 400, 400);		
		mmPanel = new MainMenuPanel(this);
		setContentPane(mmPanel);
	}
	
	/**
	 * Prints the string on standard output.
	 *
	 * @param str the string to print
	 */
	public void print(String str) {
		System.out.println(str);
	}
}
