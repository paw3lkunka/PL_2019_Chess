import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Program extends JFrame {
	private MainMenuPanel mmPanel;

	public MainMenuPanel getMmPanel() {
		return mmPanel;
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
	
		
}
