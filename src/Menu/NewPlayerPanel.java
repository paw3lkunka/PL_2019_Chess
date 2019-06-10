package Menu;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JFormattedTextField;
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
import javax.swing.JLabel;

/**
 * The Class NewPlayerPanel.
 * Extended JPanel containing components to set new player properties and sign him up in MySQL database (localhost:3306 - players table).
 * @author Pawe³ Kunka
 */
@SuppressWarnings("serial")
public class NewPlayerPanel extends JPanel {
	
	/** The program. Reference to the instance of the program. Primitive and unsecured singleton.*/
	private Program program;
	
	/** The level radio. Radio button group for choosing declared players skills. Only one at the time may be chosen.*/
	private ButtonGroup levelRadio;

	/**
	 * Create the new player panel. Sets all of its components to the valid state.
	 * Adds anonymous classes as their dedicated action listener if needed.
	 *
	 * @param program the the reference to the instance of the program class, main class of this application
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
		
		JLabel lblBadNick = new JLabel("NICK  ZAJETY LUB Z£Y !!!");
		lblBadNick.setVisible(false);
		lblBadNick.setForeground(new Color(255, 105, 180));
		lblBadNick.setFont(new Font("Papyrus", Font.PLAIN, 13));
		lblBadNick.setBounds(100, 130, 200, 20);
		add(lblBadNick);
		
		JButton btnCreate = new JButton("Utw\u00F3rz");
		btnCreate.setEnabled(true);
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.out.println();
				// zebranie danych z pól tekstowych, radia, p³ci, countera
				// utworzenie obiektu gracza, zapisanie go w bazie danych
				try {
					if(program.getMySql().isPlayerRegistered(txtNick.getText()) || txtNick.getText().compareTo("") == 0 || txtNick.getText().length() > 25) {
						lblBadNick.setVisible(true);
						System.out.println("Player " + txtNick.getText() + " already registered or invalid!");
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
						program.setContentPane(new NewGamePanel(program));
						program.revalidate();
						program.repaint();
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
		
		JLabel lblTitle = new JLabel("REJESTRACJA  GRACZA");
		lblTitle.setForeground(new Color(245, 245, 245));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Papyrus", Font.BOLD, 18));
		lblTitle.setBounds(30, 30, 340, 40);
		add(lblTitle);
		
		JLabel lblNick = new JLabel("NICK:");
		lblNick.setForeground(new Color(245, 245, 245));
		lblNick.setFont(new Font("Papyrus", Font.BOLD, 15));
		lblNick.setBounds(30, 100, 60, 25);
		add(lblNick);
		
		JLabel lblSex = new JLabel("PLEC:");
		lblSex.setForeground(new Color(245, 245, 245));
		lblSex.setFont(new Font("Papyrus", Font.BOLD, 15));
		lblSex.setBounds(30, 160, 60, 25);
		add(lblSex);
		
		JLabel lblAge = new JLabel("WIEK:");
		lblAge.setForeground(new Color(245, 245, 245));
		lblAge.setFont(new Font("Papyrus", Font.BOLD, 15));
		lblAge.setBounds(230, 160, 60, 25);
		add(lblAge);
		
		JLabel lblLevel = new JLabel("POZIOM  GRACZA:");
		lblLevel.setForeground(new Color(245, 245, 245));
		lblLevel.setFont(new Font("Papyrus", Font.BOLD, 15));
		lblLevel.setBounds(30, 220, 200, 25);
		add(lblLevel);

	}
}
