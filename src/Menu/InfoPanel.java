package Menu;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

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
		
		btnBack = new JButton("Wr\u00F3\u0107");
		btnBack.setBounds(100, 220, 200, 30);
		btnBack.setForeground(new Color(245, 245, 245));
		btnBack.setBackground(new Color(0, 0, 0));
		btnBack.addActionListener(this);
		add(btnBack);
		
		JLabel lblNewLabel = new JLabel("Tw\u00F3rcy");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.GREEN);
		lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblNewLabel.setBounds(100, 20, 200, 30);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Piotr Ruci\u0144ski 216878");
		lblNewLabel_1.setForeground(new Color(178, 34, 34));
		lblNewLabel_1.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lblNewLabel_1.setBounds(60, 60, 280, 22);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Pawe\u0142 Kunka 216819");
		lblNewLabel_2.setForeground(new Color(65, 105, 225));
		lblNewLabel_2.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lblNewLabel_2.setBounds(60, 90, 280, 22);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Porada");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setForeground(new Color(0, 255, 0));
		lblNewLabel_3.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(100, 120, 200, 30);
		add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Pierwszy gracz gra bia\u0142ymi - s\u0105 na g\u00F3rze.");
		lblNewLabel_4.setForeground(new Color(245, 245, 245));
		lblNewLabel_4.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(60, 150, 280, 22);
		add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Drugi gracz gra czarnymi - s\u0105 na dole.");
		lblNewLabel_5.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		lblNewLabel_5.setBounds(60, 180, 280, 22);
		add(lblNewLabel_5);

	}
	
	public void actionPerformed(ActionEvent evt) {
		if(evt.getSource() == btnBack) {
			program.setContentPane(program.getMmPanel());
			program.revalidate();
			program.repaint();
		}
	}
}
