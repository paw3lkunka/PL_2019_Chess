import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InfoPanel extends JPanel implements ActionListener {
	private Program program;
	JButton btnBack;
	/**
	 * Create the panel.
	 */
	public InfoPanel(Program program) {
		this.program = program;
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setBackground(new Color(128, 128, 128));
		setLayout(null);
		
		JTextField txtCreators = new JTextField();
		txtCreators.setBounds(100, 20, 200, 30);
		txtCreators.setEditable(false);
		txtCreators.setForeground(new Color(0, 255, 0));
		txtCreators.setBackground(new Color(128, 128, 128));
		txtCreators.setFont(new Font("Arial Black", Font.PLAIN, 20));
		txtCreators.setHorizontalAlignment(SwingConstants.CENTER);
		txtCreators.setText("Tw\u00F3rcy");
		add(txtCreators);
		
		JTextField txtPiotrek = new JTextField();
		txtPiotrek.setBounds(60, 60, 280, 22);
		txtPiotrek.setEditable(false);
		txtPiotrek.setText("Piotr Ruci\u0144ski 216878");
		txtPiotrek.setHorizontalAlignment(SwingConstants.LEFT);
		txtPiotrek.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		txtPiotrek.setForeground(new Color(178, 34, 34));
		txtPiotrek.setBackground(new Color(128, 128, 128));
		add(txtPiotrek);
		
		JTextField txtPawelek = new JTextField();
		txtPawelek.setBounds(60, 90, 280, 22);
		txtPawelek.setEditable(false);
		txtPawelek.setText("Pawe\u0142 Kunka 216819");
		txtPawelek.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		txtPawelek.setForeground(new Color(0, 0, 255));
		txtPawelek.setBackground(new Color(128, 128, 128));
		add(txtPawelek);
		
		JTextField txtTip = new JTextField();
		txtTip.setBounds(100, 120, 200, 30);
		txtTip.setEditable(false);
		txtTip.setText("Porada");
		txtTip.setHorizontalAlignment(SwingConstants.CENTER);
		txtTip.setForeground(new Color(124, 252, 0));
		txtTip.setFont(new Font("Arial Black", Font.PLAIN, 20));
		txtTip.setColumns(10);
		txtTip.setBackground(new Color(128, 128, 128));
		add(txtTip);
		
		JTextField txtWhites = new JTextField();
		txtWhites.setBounds(60, 150, 280, 22);
		txtWhites.setEditable(false);
		txtWhites.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtWhites.setText("Pierwszy gracz gra bia\u0142ymi - s\u0105 na g\u00F3rze.");
		txtWhites.setForeground(new Color(245, 245, 245));
		txtWhites.setBackground(new Color(128, 128, 128));
		add(txtWhites);
		
		JTextField txtBlacks = new JTextField();
		txtBlacks.setBounds(60, 180, 280, 22);
		txtBlacks.setEditable(false);
		txtBlacks.setText("Drugi gracz gra czarnymi - s\u0105 na dole.");
		txtBlacks.setForeground(new Color(0, 0, 0));
		txtBlacks.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtBlacks.setColumns(10);
		txtBlacks.setBackground(Color.GRAY);
		add(txtBlacks);
		
		btnBack = new JButton("Wr\u00F3\u0107");
		btnBack.setBounds(100, 220, 200, 30);
		btnBack.setForeground(new Color(245, 245, 245));
		btnBack.setBackground(new Color(0, 0, 0));
		btnBack.addActionListener(this);
		add(btnBack);

	}
	
	public void actionPerformed(ActionEvent evt) {
		if(evt.getSource() == btnBack) {
			program.setContentPane(program.getMmPanel());
			program.revalidate();
			program.repaint();
		}
	}
}
