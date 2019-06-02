import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Canvas;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import java.awt.Font;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewGamePanel extends JPanel {
	private Program program;
	private JTextField txtTitle;
	private JTextField txtLabelWhite;
	private JTextField txtLabelBlack;

	/**
	 * Create the panel.
	 */
	public NewGamePanel(Program program) {
		this.program = program;
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setBackground(new Color(160, 82, 45));
		setLayout(null);
		
		txtTitle = new JTextField();
		txtTitle.setFont(new Font("Arial Black", Font.BOLD, 18));
		txtTitle.setText("Wprowad\u017C nicki graczy");
		txtTitle.setHorizontalAlignment(SwingConstants.CENTER);
		txtTitle.setBackground(new Color(160, 82, 45));
		txtTitle.setEditable(false);
		txtTitle.setBounds(40, 13, 320, 30);
		add(txtTitle);
		txtTitle.setColumns(10);
		
		txtLabelWhite = new JTextField();
		txtLabelWhite.setHorizontalAlignment(SwingConstants.RIGHT);
		txtLabelWhite.setFont(new Font("Arial Black", Font.PLAIN, 16));
		txtLabelWhite.setEditable(false);
		txtLabelWhite.setText("Bia\u0142y:");
		txtLabelWhite.setBounds(60, 100, 70, 25);
		add(txtLabelWhite);
		txtLabelWhite.setColumns(10);
		
		JFormattedTextField txtWhitePlayer = new JFormattedTextField();
		txtWhitePlayer.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		txtWhitePlayer.setBackground(new Color(0, 0, 0));
		txtWhitePlayer.setForeground(new Color(245, 245, 245));
		txtWhitePlayer.setBounds(130, 100, 210, 25);
		add(txtWhitePlayer);
		
		txtLabelBlack = new JTextField();
		txtLabelBlack.setForeground(new Color(245, 245, 245));
		txtLabelBlack.setBackground(new Color(0, 0, 0));
		txtLabelBlack.setText("Czarny:");
		txtLabelBlack.setHorizontalAlignment(SwingConstants.RIGHT);
		txtLabelBlack.setFont(new Font("Arial Black", Font.PLAIN, 16));
		txtLabelBlack.setEditable(false);
		txtLabelBlack.setColumns(10);
		txtLabelBlack.setBounds(60, 200, 70, 25);
		add(txtLabelBlack);
		
		JFormattedTextField txtBlackPlayer = new JFormattedTextField();
		txtBlackPlayer.setForeground(new Color(0, 0, 0));
		txtBlackPlayer.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		txtBlackPlayer.setBackground(new Color(245, 245, 245));
		txtBlackPlayer.setBounds(130, 200, 210, 25);
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
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnStart) {
					// odpalanie PAppletu i w ogóle ca³ej gry
				}
			}
		});
		add(btnStart);

	}

}
