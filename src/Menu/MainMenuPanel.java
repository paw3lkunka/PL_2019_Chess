package Menu;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class MainMenuPanel extends JPanel {
	private Program program;
	private JTextField txtTitle;
	
	// by u¿yæ w optymalny sposób klasy wewnêtrznej do obs³ugi tych guziczków potrzebne by³y te pola
	private MainMenuListener mmListener;
	private JButton btnNewGame;
	private JButton btnLoadGame;
	private JButton btnSaveGame;
	private JButton btnStats;
	private JButton btnInfo;
	private JButton btnExit;
	private JButton btnContinue;
	
	
	public JButton getBtnContinue() {
		return btnContinue;
	}

	/**
	 * Create the panel.
	 */
	public MainMenuPanel(Program program) {
		this.program = program;
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setBackground(new Color(165, 42, 42));
		setLayout(null);
		
		mmListener = new MainMenuListener();
		
		txtTitle = new JTextField();
		txtTitle.setText("GRA W SZACHY");
		txtTitle.setForeground(new Color(245, 245, 245));
		txtTitle.setFont(new Font("Arial Black", Font.PLAIN, 26));
		txtTitle.setHorizontalAlignment(SwingConstants.CENTER);
		txtTitle.setBackground(new Color(165, 42, 42));
		txtTitle.setBounds(40, 20, 320, 40);
		add(txtTitle);
		txtTitle.setColumns(10);
		
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

	}
	
	class MainMenuListener implements ActionListener {

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
				} catch (NullPointerException ex) {
					System.out.println("Save file hasn't been chosen");
				}
			}

			if(e.getSource() == btnStats) {
				program.setContentPane(new StatsPanel(program));
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
