package Menu;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

/**
 * The Class StatsPanel.
 * @author Pawe³ Kunka
 */
@SuppressWarnings("serial")
public class StatsPanel extends JPanel {
	
	/** The program. */
	private Program program;
	
	/** The table. */
	private JTable table;

	/**
	 * Create the panel.
	 *
	 * @param program the program
	 * @throws SQLException the SQL exception
	 */
	public StatsPanel(Program program) throws SQLException {
		this.program = program;
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setBackground(new Color(160, 82, 45));
		setLayout(null);
		
		JButton btnBack = new JButton("Wr\u00F3\u0107");
		btnBack.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		btnBack.setBackground(new Color(245, 245, 245));
		btnBack.setBounds(150, 320, 100, 25);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnBack) {
					program.setContentPane(program.getMmPanel());
					program.revalidate();
					program.repaint();
				}
			}
		});
		add(btnBack);
		
		JLabel lblTitle = new JLabel("RANKING");
		lblTitle.setForeground(new Color(245, 245, 245));
		lblTitle.setFont(new Font("Papyrus", Font.BOLD, 16));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(100, 10, 200, 30);
		add(lblTitle);
		
		table = new JTable();
		table.setEnabled(false);
		table.setForeground(new Color(245, 245, 245));
		table.setBackground(new Color(0, 0, 0));
		table.setModel(program.getMySql().buildTableModel(program.getMySql().getPlayersStats()));
		table.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(20, 50, 360, 250);
		add(scrollPane);

	}
}
