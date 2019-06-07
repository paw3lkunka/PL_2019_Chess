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

@SuppressWarnings("serial")
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
		
		JLabel lblCreators = new JLabel("Tw\u00F3rcy");
		lblCreators.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreators.setForeground(Color.GREEN);
		lblCreators.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblCreators.setBounds(100, 20, 200, 30);
		add(lblCreators);
		
		JLabel lblPiotr = new JLabel("Piotr Ruci\u0144ski 216878");
		lblPiotr.setForeground(new Color(178, 34, 34));
		lblPiotr.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lblPiotr.setBounds(60, 60, 280, 22);
		add(lblPiotr);
		
		JLabel lblPawel = new JLabel("Pawe\u0142 Kunka 216819");
		lblPawel.setForeground(new Color(65, 105, 225));
		lblPawel.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lblPawel.setBounds(60, 90, 280, 22);
		add(lblPawel);
		
		JLabel lblTip = new JLabel("Porada");
		lblTip.setHorizontalAlignment(SwingConstants.CENTER);
		lblTip.setForeground(new Color(0, 255, 0));
		lblTip.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblTip.setBounds(100, 120, 200, 30);
		add(lblTip);
		
		JLabel lblTipWhite = new JLabel("Pierwszy gracz gra bia\u0142ymi - s\u0105 na g\u00F3rze.");
		lblTipWhite.setForeground(new Color(245, 245, 245));
		lblTipWhite.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		lblTipWhite.setBounds(60, 150, 280, 22);
		add(lblTipWhite);
		
		JLabel lblTipBlack = new JLabel("Drugi gracz gra czarnymi - s\u0105 na dole.");
		lblTipBlack.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		lblTipBlack.setBounds(60, 180, 280, 22);
		add(lblTipBlack);

	}
	
	public void actionPerformed(ActionEvent evt) {
		if(evt.getSource() == btnBack) {
			program.setContentPane(program.getMmPanel());
			program.revalidate();
			program.repaint();
		}
	}
}
