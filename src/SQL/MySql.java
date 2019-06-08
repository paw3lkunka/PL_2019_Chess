package SQL;

import java.sql.*;

import javax.swing.table.DefaultTableModel;

import Player.Sex;
import Player.Skill;

/**
 * The Class MySql.
 * @author Pawe³ Kunka
 */
public class MySql {
	
	/** The connection. */
	private Connection connection;
	
	/**
	 * Instantiates a new my sql. Sets the connection with localhost:3306 MySQL database.
	 *
	 * @throws SQLException the SQL exception
	 */
	public MySql() throws SQLException{
		DriverManager.registerDriver( new com.mysql.cj.jdbc.Driver() );
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Kompo2019?useUnicode=true&characterEncoding=utf-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
	}
	
	/**
	 * Gets the players stats as ResultSet.
	 *
	 * @return the players stats
	 * @throws SQLException the SQL exception
	 */
	public ResultSet getPlayersStats() throws SQLException {
		Statement statement = connection.createStatement();
		return statement.executeQuery(
				"SELECT * "
				+ "FROM players "
				+ "ORDER BY Wins DESC"
			);
	}
	
	/**
	 * Adds the new player to database's players table.
	 *
	 * @param nick the player nick
	 * @param sex the player sex
	 * @param age the player age
	 * @param skill the player skill
	 * @throws SQLException the SQL exception
	 */
	public void addNewPlayer(String nick, Sex sex, int age, Skill skill) throws SQLException {
		Statement statement = connection.createStatement();
		statement.executeUpdate(
			"INSERT INTO players (Nick, Games, Wins, Sex, Age, Skill) "
			+ "VALUES (\'" + nick + "\', 0, 0, \'" + sex + "\', " + age + ", \'" + skill + "\') "
		);
	}
	
	/**
	 * Checks if is player registered.
	 *
	 * @param nick the player nick
	 * @return true, if player is registered
	 * @throws SQLException the SQL exception
	 */
	public boolean isPlayerRegistered(String nick) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(
			"SELECT count(*) "
			+ "FROM players "
			+ "WHERE Nick = \'" + nick + "\'"
		);
		rs.next();
		
		if(rs.getInt(1) == 1) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Update win.
	 *
	 * @param nick the nick
	 * @throws SQLException the SQL exception
	 */
	public void updateWin(String nick) throws SQLException {
		Statement statement = connection.createStatement();
		statement.executeUpdate(
			"UPDATE players "
			+ "SET Games = Games + 1, Wins = Wins + 1 "
			+ "WHERE Nick = \'" + nick + "\'"
		);
	}
	
	/**
	 * Update loss.
	 *
	 * @param nick the nick
	 * @throws SQLException the SQL exception
	 */
	public void updateLoss(String nick) throws SQLException {
		Statement statement = connection.createStatement();
		statement.executeUpdate(
			"UPDATE players "
			+ "SET Games = Games + 1 "
			+ "WHERE Nick = \'" + nick + "\'"
		);
	}
	
	/**
	 * Builds the table model from ResultSet.
	 *
	 * @param rs the result set to base table on
	 * @return the default table model
	 * @throws SQLException the SQL exception
	 */
	public DefaultTableModel buildTableModel(ResultSet rs)
	        throws SQLException {

	    ResultSetMetaData metaData = rs.getMetaData();

	    // names of columns
	    int columnCount = metaData.getColumnCount();
	    String [] columnNames = new String[columnCount];
	    for (int column = 1; column <= columnCount; column++) {
	        columnNames[column - 1] = metaData.getColumnName(column);
	    }

	    // data of the table
	    rs.last();
	    Object [][] data = new Object[rs.getRow() + 1][];
	    //data[0] = columnNames;
	    rs.beforeFirst();
	    while (rs.next()) {
	        Object [] row = new Object[columnCount];
	        for (int column = 1; column <= columnCount; column++) {
	            row[column - 1] = rs.getObject(column);
	        }
	        data[rs.getRow() - 1] = row;
	    }

	    return new DefaultTableModel(data, columnNames);

	}
}
