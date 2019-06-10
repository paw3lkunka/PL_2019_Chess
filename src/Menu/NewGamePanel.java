package Menu;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Figures.FColor;
import Player.*;

import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

/**
 * The Class NewGamePanel.
 * @author Pawe³ Kunka
 */
@SuppressWarnings("serial")
public class NewGamePanel extends JPanel {
	
	/** The program. Reference to the instance of the program. Primitive and unsecured singleton. */
	private Program program;

	/**
	 * Create the new game panel. Sets all of its components to the valid state.
	 * Adds anonymous classes as their dedicated action listener if needed.
	 *
	 * @param program the reference to the instance of the program class, main class of this application.
	 */
	public NewGamePanel(Program program) {
		this.program = program;
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setBackground(new Color(160, 82, 45));
		setLayout(null);
		
		JFormattedTextField txtWhitePlayer = new JFormattedTextField();
		txtWhitePlayer.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		txtWhitePlayer.setBackground(new Color(0, 0, 0));
		txtWhitePlayer.setForeground(new Color(245, 245, 245));
		txtWhitePlayer.setBounds(140, 100, 200, 25);
		add(txtWhitePlayer);
		
		JFormattedTextField txtBlackPlayer = new JFormattedTextField();
		txtBlackPlayer.setForeground(new Color(0, 0, 0));
		txtBlackPlayer.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		txtBlackPlayer.setBackground(new Color(245, 245, 245));
		txtBlackPlayer.setBounds(140, 200, 200, 25);
		add(txtBlackPlayer);
		
		JButton btnBack = new JButton("Wr\u00F3\u0107");
		btnBack.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		btnBack.setForeground(new Color(245, 245, 245));
		btnBack.setBackground(new Color(0, 0, 0));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnBack) {
					program.setContentPane(program.getMmPanel());
					program.revalidate();
					program.repaint();
				}
			}
		});
		btnBack.setBounds(20, 280, 100, 50);
		add(btnBack);
		
		JButton btnNewPlayer = new JButton("Nowy gracz");
		btnNewPlayer.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		btnNewPlayer.setForeground(new Color(0, 0, 0));
		btnNewPlayer.setBackground(new Color(245, 245, 245));
		btnNewPlayer.setBounds(140, 280, 120, 50);
		btnNewPlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnNewPlayer) {
					program.setContentPane(new NewPlayerPanel(program));
					program.revalidate();
					program.repaint();
				}
			}
		});
		add(btnNewPlayer);
		
		JLabel lblTitle = new JLabel("WPROWADZ NICKI GRACZY");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Papyrus", Font.BOLD, 16));
		lblTitle.setBounds(40, 13, 320, 30);
		add(lblTitle);
		
		JLabel lblWhitePlayer = new JLabel("BIALY:");
		lblWhitePlayer.setBackground(new Color(240, 255, 255));
		lblWhitePlayer.setFont(new Font("Papyrus", Font.BOLD, 15));
		lblWhitePlayer.setHorizontalAlignment(SwingConstants.RIGHT);
		lblWhitePlayer.setBounds(60, 100, 80, 25);
		add(lblWhitePlayer);
		
		JLabel lblBlackPlayer = new JLabel("CZARNY:");
		lblBlackPlayer.setForeground(new Color(245, 245, 245));
		lblBlackPlayer.setFont(new Font("Papyrus", Font.BOLD, 12));
		lblBlackPlayer.setBounds(60, 200, 80, 25);
		add(lblBlackPlayer);
		
		JLabel lblWhiteUnregistered = new JLabel("NIEZAREJESTROWANY(A)   !!!");
		lblWhiteUnregistered.setVisible(false);
		lblWhiteUnregistered.setForeground(new Color(0, 0, 255));
		lblWhiteUnregistered.setFont(new Font("Papyrus", Font.PLAIN, 10));
		lblWhiteUnregistered.setBounds(140, 130, 200, 20);
		add(lblWhiteUnregistered);
		
		JLabel lblBlackUnregistered = new JLabel("NIEZAREJESTROWANY(A)  !!!");
		lblBlackUnregistered.setVisible(false);
		lblBlackUnregistered.setForeground(Color.BLUE);
		lblBlackUnregistered.setFont(new Font("Papyrus", Font.PLAIN, 10));
		lblBlackUnregistered.setBounds(140, 230, 200, 20);
		add(lblBlackUnregistered);
		
		JLabel lblEqualNick = new JLabel("DUBLUJ\u0104CE  SI\u0118  NICKI !!!");
		lblEqualNick.setVisible(false);
		lblEqualNick.setForeground(new Color(47, 79, 79));
		lblEqualNick.setFont(new Font("Papyrus", Font.PLAIN, 10));
		lblEqualNick.setBounds(140, 160, 200, 20);
		add(lblEqualNick);
		
		JButton btnStart = new JButton("Start");
		btnStart.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		btnStart.setForeground(new Color(245, 245, 245));
		btnStart.setBackground(Color.BLACK);
		btnStart.setBounds(280, 280, 100, 50);
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnStart) {
					System.out.println("Clicked Start button");
					
					try {
						lblWhiteUnregistered.setVisible(false);
						lblBlackUnregistered.setVisible(false);
						lblEqualNick.setVisible(false);
						
						if(program.getMySql().isPlayerRegistered(txtWhitePlayer.getText()) == false) {
							lblWhiteUnregistered.setVisible(true);
							System.out.println("User " + txtWhitePlayer.getText() + "not registered!");
						
						} else if(program.getMySql().isPlayerRegistered(txtBlackPlayer.getText()) == false) {
							lblBlackUnregistered.setVisible(true);
							System.out.println("User " + txtBlackPlayer.getText() + "not registered!");
						
						} else if(txtWhitePlayer.getText().compareTo(txtBlackPlayer.getText()) == 0) {
							lblEqualNick.setVisible(true);
							System.out.println("User cannot play with himself! Well at least in this app.");
						
						} else {
							if(program.getGame().getIsRunning()) {
								
								int confirm = JOptionPane.showConfirmDialog(
										null, 
										"Czy na pewno chcesz rozpocz¹æ now¹ grê? Niezapisany postêp zostanie utracony.", 
										"UWAGA!", 
										JOptionPane.YES_NO_OPTION, 
										JOptionPane.INFORMATION_MESSAGE
									);
									
									switch(confirm) {
									case JOptionPane.YES_OPTION:
										//setC(new Chessboard(program.getGame(), new Vector3(0,0,0)));
										program.getGame().newGame(  //za³atane na tasme
												new Player(txtWhitePlayer.getText(), PlayerID.one, FColor.white, Sex.female, Skill.profesional, 10, 12),
												new Player(txtBlackPlayer.getText(), PlayerID.two, FColor.black, Sex.male, Skill.beginner, 1, 5)
												);
										program.getGame().run();
										program.setContentPane(program.getMmPanel());
										break;
									default:
										System.out.println("New game cancelled");
										break;
									}
							} else {
								program.getGame().newGame(  //za³atane na tasme
										new Player(txtWhitePlayer.getText(), PlayerID.one, FColor.white, Sex.female, Skill.profesional, 10, 12),
										new Player(txtBlackPlayer.getText(), PlayerID.two, FColor.black, Sex.male, Skill.beginner, 1, 5)
										);
								program.getGame().run();
								program.setContentPane(program.getMmPanel());
							}
						} 
						
					} catch (HeadlessException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
					program.getMmPanel().getBtnContinue().setEnabled(true);
				}
			}
		});
		add(btnStart);
		
		

	}
}
