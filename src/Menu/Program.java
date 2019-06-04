package Menu;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Processing.Processing;
import processing.core.PApplet;

public class Program extends JFrame {
	private MainMenuPanel mmPanel;
	private Processing game;


	public MainMenuPanel getMmPanel() {
		return mmPanel;
	}

	public Processing getGame() {
		return game;
	}
	
	/**
	 * Launch the application.
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
	 * Create the frame.
	 */
	public Program() {
		
		
  	  	game = new Processing(this);
		String[] args =new String[] {"--location=2000,2000", "Szachy"};
    	PApplet.runSketch(args, game);
    	game.stop();
    	
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
					evt.getWindow().dispose();
					game.end();
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
	
	public void print(String str) {
		System.out.println(str);
	}
}
