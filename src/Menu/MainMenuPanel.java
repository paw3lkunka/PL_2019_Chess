package Menu;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

/**
 * The Class MainMenuPanel.
 * Extended JPanel containing main menu of the application.
 * @author Pawe³ Kunka
 */
@SuppressWarnings("serial")
public class MainMenuPanel extends JPanel {
	
	/** The program. Reference to the instance of the program. Primitive and unsecured singleton. */
	private Program program;
	
	/** The mm listener. Main menu listener handling actionEvents of all the main menu buttons*/
	// by u¿yæ w optymalny sposób klasy wewnêtrznej do obs³ugi tych guziczków potrzebne by³y te pola
	private MainMenuListener mmListener;
	
	/** The btn new game. */
	private JButton btnNewGame;
	
	/** The btn load game. */
	private JButton btnLoadGame;
	
	/** The btn save game. */
	private JButton btnSaveGame;
	
	/** The btn stats. */
	private JButton btnStats;
	
	/** The btn info. */
	private JButton btnInfo;
	
	/** The btn exit. */
	private JButton btnExit;
	
	/** The btn continue. */
	private JButton btnContinue;
	
	/** The lbl title. */
	private JLabel lblTitle;
	
	
	/**
	 * Gets the continue button. Enabled when the game is running. Continues the game.
	 *
	 * @return the continue button
	 */
	public JButton getBtnContinue() {
		return btnContinue;
	}

	/**
	 * Create the panel representing main menu. Sets all the components that it contains to their valid state.
	 * Adds MainMenuListener as their action listener.
	 *
	 * @param program the reference to the instance of the program class, main class of this application
	 */
	public MainMenuPanel(Program program) {
		this.program = program;
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setBackground(new Color(165, 42, 42));
		setLayout(null);
		
		mmListener = new MainMenuListener();
		
		btnNewGame = new JButton("Nowa gra");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewGame.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		btnNewGame.setForeground(new Color(245, 245, 245));
		btnNewGame.setBackground(new Color(0, 0, 0));
		btnNewGame.setBounds(40, 120, 320, 30);
		btnNewGame.addActionListener(mmListener);
		add(btnNewGame);
		
		btnLoadGame = new JButton("Wczytaj gr\u0119");
		btnLoadGame.setForeground(new Color(0, 0, 0));
		btnLoadGame.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		btnLoadGame.setBackground(new Color(245, 245, 245));
		btnLoadGame.setBounds(40, 160, 320, 30);
		btnLoadGame.addActionListener(mmListener);
		add(btnLoadGame);
		
		btnSaveGame = new JButton("Zapisz gr\u0119");
		btnSaveGame.setForeground(new Color(245, 245, 245));
		btnSaveGame.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		btnSaveGame.setBackground(Color.BLACK);
		btnSaveGame.setBounds(40, 200, 320, 30);
		btnSaveGame.addActionListener(mmListener);
		add(btnSaveGame);
		
		btnStats = new JButton("Ranking");
		btnStats.setForeground(Color.BLACK);
		btnStats.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		btnStats.setBackground(new Color(245, 245, 245));
		btnStats.setBounds(40, 240, 320, 30);
		btnStats.addActionListener(mmListener);
		add(btnStats);
		
		btnInfo = new JButton("Tworcy i porady");
		btnInfo.setForeground(new Color(245, 245, 245));
		btnInfo.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		btnInfo.setBackground(Color.BLACK);
		btnInfo.setBounds(40, 280, 320, 30);
		btnInfo.addActionListener(mmListener);
		add(btnInfo);
		
		btnExit = new JButton("Wyjscie");
		btnExit.setForeground(Color.BLACK);
		btnExit.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		btnExit.setBackground(new Color(245, 245, 245));
		btnExit.setBounds(40, 320, 320, 30);
		btnExit.addActionListener(mmListener);
		add(btnExit);
		
		btnContinue = new JButton("Kontynuuj");
		btnContinue.setEnabled(false);
		btnContinue.setForeground(new Color(0, 0, 0));
		btnContinue.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		btnContinue.setBackground(new Color(245, 245, 245));
		btnContinue.setBounds(40, 80, 320, 30);
		btnContinue.addActionListener(mmListener);
		add(btnContinue);
		
		lblTitle = new JLabel("GRA W SZACHY");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setForeground(new Color(245, 245, 245));
		lblTitle.setFont(new Font("Papyrus", Font.BOLD, 26));
		lblTitle.setBounds(40, 20, 320, 40);
		add(lblTitle);

	}
	
	/**
	 * The listener interface for receiving mainMenu events.
	 * The class that is interested in processing a mainMenu
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addMainMenuListener<code> method. When
	 * the mainMenu event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see MainMenuEvent
	 */
	class MainMenuListener implements ActionListener {

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == btnContinue) {
				program.getGame().run();
			}
			
			if(e.getSource() == btnNewGame) {
				program.setContentPane(new NewGamePanel(program));
				program.revalidate();
				program.repaint();
			}
			
			if(e.getSource() == btnLoadGame) {
				try {
					JFileChooser fileChooser = new JFileChooser();
					fileChooser.setCurrentDirectory(new java.io.File("."));
					fileChooser.setDialogTitle("Wybierz plik do wczytania");
					fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
					fileChooser.showOpenDialog(btnLoadGame);
					
					//trzeba bêdzie z³apaæ wyj¹tek NullPointerException na wypadek klikniêcia Cancel
					System.out.println("Chosen load file: " + fileChooser.getSelectedFile().getAbsolutePath() );
					program.getGame().loadGame(fileChooser.getSelectedFile().getAbsolutePath());
					program.getGame().run();
					btnContinue.setEnabled(true);
				} catch (NullPointerException ex) {
					System.out.println("Load file hasn't been chosen");
				}
			}
			
			if(e.getSource() == btnSaveGame) {
				try {
					JFileChooser fileChooser = new JFileChooser();
					fileChooser.setCurrentDirectory(new java.io.File("."));
					fileChooser.setDialogTitle("Wybierz plik do zapisu");
					fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
					fileChooser.showOpenDialog(btnLoadGame);
					
					//trzeba bêdzie z³apaæ wyj¹tek NullPointerException na wypadek klikniêcia Cancel
					System.out.println("Chosen save file: " + fileChooser.getSelectedFile().getAbsolutePath() );
					program.getGame().saveGame(fileChooser.getSelectedFile().getAbsolutePath());
					//new XMLWriter<Chessboard>(fileChooser.getSelectedFile().getAbsolutePath()).save(program.getGame().getC());
				} catch (NullPointerException ex) {
					System.out.println("Save file hasn't been chosen");
				}
			}

			if(e.getSource() == btnStats) {
				try {
					program.setContentPane(new StatsPanel(program));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				program.revalidate();
				program.repaint();
			}

			if(e.getSource() == btnInfo) {
				program.setContentPane(new InfoPanel(program));
				program.revalidate();
				program.repaint();
			}

			if(e.getSource() == btnExit) {
				program.dispatchEvent(new WindowEvent(program, WindowEvent.WINDOW_CLOSING));
			}

		}
		
	}

}
