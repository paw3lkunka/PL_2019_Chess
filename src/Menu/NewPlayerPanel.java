package Menu;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Player.Sex;
import Player.Skill;

import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JLabel;

public class NewPlayerPanel extends JPanel {
	private Program program;
	private ButtonGroup levelRadio;

	/**
	 * Create the panel.
	 */
	public NewPlayerPanel(Program program) {
		this.program = program;
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setBackground(new Color(160, 82, 45));
		setLayout(null);
		
		JFormattedTextField txtNick = new JFormattedTextField();
		txtNick.setHorizontalAlignment(SwingConstants.CENTER);
		txtNick.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		txtNick.setForeground(new Color(245, 245, 245));
		txtNick.setBackground(new Color(0, 0, 0));
		txtNick.setBounds(90, 100, 260, 25);
		add(txtNick);
		
		JComboBox cbSex = new JComboBox();
		cbSex.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		cbSex.setBackground(new Color(0, 0, 0));
		cbSex.setForeground(new Color(245, 245, 245));
		cbSex.setModel(new DefaultComboBoxModel(new String[] {"m\u0119\u017Cczyzna", "kobieta", "nie podaj\u0119"}));
		cbSex.setMaximumRowCount(3);
		cbSex.setBounds(90, 160, 120, 25);
		add(cbSex);
		
		JSpinner spinnerAge = new JSpinner();
		spinnerAge.setModel(new SpinnerNumberModel(21, 1, 150, 1));
		spinnerAge.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		spinnerAge.setForeground(new Color(245, 245, 245));
		spinnerAge.setBackground(new Color(0, 0, 0));
		spinnerAge.setBounds(290, 160, 60, 25);
		add(spinnerAge);
		
		JRadioButton rdbtnBeginner = new JRadioButton("Pocz\u0105tkuj\u0105cy");
		rdbtnBeginner.setSelected(true);
		rdbtnBeginner.setForeground(new Color(245, 245, 245));
		rdbtnBeginner.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		rdbtnBeginner.setBackground(new Color(160, 82, 45));
		rdbtnBeginner.setBounds(30, 250, 110, 25);
		add(rdbtnBeginner);
		
		JRadioButton rdbtnIntermediate = new JRadioButton("Standardowy");
		rdbtnIntermediate.setForeground(new Color(169, 169, 169));
		rdbtnIntermediate.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		rdbtnIntermediate.setBackground(new Color(160, 82, 45));
		rdbtnIntermediate.setBounds(140, 250, 110, 25);
		add(rdbtnIntermediate);
		
		JRadioButton rdbtnProfesional = new JRadioButton("Profesjonalista");
		rdbtnProfesional.setForeground(new Color(0, 0, 0));
		rdbtnProfesional.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		rdbtnProfesional.setBackground(new Color(160, 82, 45));
		rdbtnProfesional.setBounds(250, 250, 127, 25);
		add(rdbtnProfesional);
		
		levelRadio = new ButtonGroup();
		rdbtnBeginner.setActionCommand("beginner");
		levelRadio.add(rdbtnBeginner);
		rdbtnIntermediate.setActionCommand("intermediate");
		levelRadio.add(rdbtnIntermediate);
		rdbtnProfesional.setActionCommand("profesional");
		levelRadio.add(rdbtnProfesional);
		
		JButton btnBack = new JButton("Wr\u00F3\u0107");
		btnBack.setBackground(new Color(245, 245, 245));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnBack) {
					program.setContentPane(new NewGamePanel(program));
					program.revalidate();
					program.repaint();
				}
			}
		});
		btnBack.setBounds(30, 300, 120, 40);
		add(btnBack);
		
		JButton btnCreate = new JButton("Utw\u00F3rz");
		btnCreate.setEnabled(true);
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.out.println();
				// zebranie danych z pól tekstowych, radia, p³ci, countera
				// utworzenie obiektu gracza, zapisanie go w bazie danych
				try {
					if(program.getMySql().isPlayerRegistered(txtNick.getText())) {
						System.out.println("Player " + txtNick.getText() + " already registered!");
					} else {
						Sex playerSex = null;
						Skill playerSkill = null;
						//"m\u0119\u017Cczyzna", "kobieta", "nie podaj\u0119"
						switch(cbSex.getSelectedItem().toString()) {
						case "m\u0119\u017Cczyzna":
							playerSex = Sex.male;
							break;
						case "kobieta":
							playerSex = Sex.female;
							break;
						case "nie podaj\u0119":
							playerSex = Sex.none;
							break;
						}
						
						switch(levelRadio.getSelection().getActionCommand()) {
						case "beginner":
							playerSkill = Skill.beginner;
							break;
						case "intermediate":
							playerSkill = Skill.intermediate;
							break;
						case "profesional":
							playerSkill = Skill.profesional;
							break;
						}
						
						program.getMySql().addNewPlayer(txtNick.getText(), playerSex, (int) spinnerAge.getValue(), playerSkill);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnCreate.setForeground(new Color(245, 245, 245));
		btnCreate.setBackground(new Color(0, 0, 0));
		btnCreate.setBounds(240, 300, 120, 40);
		add(btnCreate);
		
		JLabel lblNewLabel = new JLabel("REJESTRACJA  GRACZA");
		lblNewLabel.setForeground(new Color(245, 245, 245));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Papyrus", Font.BOLD, 18));
		lblNewLabel.setBounds(30, 30, 340, 40);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("NICK:");
		lblNewLabel_1.setForeground(new Color(245, 245, 245));
		lblNewLabel_1.setFont(new Font("Papyrus", Font.BOLD, 15));
		lblNewLabel_1.setBounds(30, 100, 60, 25);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("PLEC:");
		lblNewLabel_2.setForeground(new Color(245, 245, 245));
		lblNewLabel_2.setFont(new Font("Papyrus", Font.BOLD, 15));
		lblNewLabel_2.setBounds(30, 160, 60, 25);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("WIEK:");
		lblNewLabel_3.setForeground(new Color(245, 245, 245));
		lblNewLabel_3.setFont(new Font("Papyrus", Font.BOLD, 15));
		lblNewLabel_3.setBounds(230, 160, 60, 25);
		add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("POZIOM  GRACZA:");
		lblNewLabel_4.setForeground(new Color(245, 245, 245));
		lblNewLabel_4.setFont(new Font("Papyrus", Font.BOLD, 15));
		lblNewLabel_4.setBounds(30, 220, 200, 25);
		add(lblNewLabel_4);

	}
}
