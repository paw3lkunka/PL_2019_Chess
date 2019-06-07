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

public class StatsPanel extends JPanel {
	private Program program;
	private JTable table;

	/**
	 * Create the panel.
	 * @throws SQLException 
	 */
	public StatsPanel(Program program) throws SQLException {
		this.program = program;
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setBackground(new Color(160, 82, 45));
		setLayout(null);
		
		table = new JTable();
		table.setEnabled(false);
		table.setForeground(new Color(245, 245, 245));
		table.setBackground(new Color(0, 0, 0));
		table.setModel(program.getMySql().buildTableModel(program.getMySql().getPlayersStats()));
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(55);
		table.getColumnModel().getColumn(0).setMinWidth(14);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(30);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(65);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(4).setPreferredWidth(40);
		table.getColumnModel().getColumn(5).setResizable(false);
		table.getColumnModel().getColumn(5).setPreferredWidth(100);
		table.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		table.setBounds(20, 50, 360, 255);
		add(table);
		
		JButton btnBack = new JButton("Wr\u00F3\u0107");
		btnBack.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		btnBack.setBackground(new Color(245, 245, 245));
		btnBack.setBounds(20, 320, 97, 25);
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
		
		JButton btnLastPage = new JButton("<<<");
		btnLastPage.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		btnLastPage.setEnabled(false);
		btnLastPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//cofanie tabelki
			}
		});
		btnLastPage.setForeground(new Color(245, 245, 245));
		btnLastPage.setBackground(new Color(0, 0, 0));
		btnLastPage.setBounds(240, 320, 60, 25);
		add(btnLastPage);
		
		JButton btnNextPage = new JButton(">>>");
		btnNextPage.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		btnNextPage.setEnabled(false);
		btnNextPage.setForeground(new Color(0, 0, 0));
		btnNextPage.setBackground(new Color(245, 245, 245));
		btnNextPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// tabelka w przód
			}
		});
		btnNextPage.setBounds(320, 320, 60, 25);
		add(btnNextPage);
		
		JLabel lblNewLabel = new JLabel("RANKING");
		lblNewLabel.setForeground(new Color(245, 245, 245));
		lblNewLabel.setFont(new Font("Papyrus", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(100, 10, 200, 30);
		add(lblNewLabel);

	}

}
