package Menu;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Canvas;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Figures.FColor;
import Player.*;
import Processing.Chessboard;
import Processing.Vector3;
import processing.core.PApplet;

import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class NewGamePanel extends JPanel {
	private Program program;

	/**
	 * Create the panel.
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
						if(program.getMySql().isPlayerRegistered(txtWhitePlayer.getText()) && program.getMySql().isPlayerRegistered(txtBlackPlayer.getText())) {
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
												new Player("Bia³a", PlayerID.one, FColor.white, Sex.female, Skill.profesional, 10, 12),
												new Player("Czarny", PlayerID.two, FColor.black, Sex.male, Skill.beginner, 1, 5)
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
										new Player("Bia³a", PlayerID.one, FColor.white, Sex.female, Skill.profesional, 10, 12),
										new Player("Czarny", PlayerID.two, FColor.black, Sex.male, Skill.beginner, 1, 5)
										);
								program.getGame().run();
								program.setContentPane(program.getMmPanel());
							}
						} else {
							System.out.println("At least one of players is not registered!");
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

	}

}
