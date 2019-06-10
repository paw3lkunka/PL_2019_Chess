package Player;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import Figures.FColor;
import XML.XMLSerializable;

/**
 * The Class Player.
 * @author Piotr Ruciñski
 */
public class Player implements XMLSerializable {

	/** The name. */
	String name;
	
	/** The id. */
	PlayerID id;
	
	/** The color. */
	FColor color;
	
	/** The sex. */
	Sex sex;
	
	/** The skill. */
	Skill skill;
	
	/** The wins. */
	int wins;
	
	/** The games. */
	int games;
	
	/**
	 * Instantiates a new player.
	 *
	 * @param name the nick of the player
	 * @param id the id of the player in-game
	 * @param color the color the player's figures
	 * @param sex the sex of the player
	 * @param skill the skill level declared by player
	 * @param wins the wins count of the player
	 * @param games the games count of the player
	 */
	public Player(String name, PlayerID id, FColor color, Sex sex, Skill skill, int wins, int games) {
		this.name = name;
		this.color = color;
		this.sex = sex;
		this.skill = skill;
		this.wins = wins;
		this.games = games;
		this.id = id;
	}

	/**
	 * Instantiates a new player based on XML element.
	 *
	 * @param element the element
	 * @param id the id of the player in-game
	 * @param color the color of the player's figures
	 */
	public Player(Element element, PlayerID id, FColor color) {
		NodeList nList = element.getChildNodes();
		this.name = ((Element)nList).getElementsByTagName("Name").item(0).getTextContent();
		this.id = id;
		this.color = color;
		this.sex = Sex.valueOf( ((Element)nList).getElementsByTagName("Sex").item(0).getTextContent() );
		this.skill = Skill.valueOf( ((Element)nList).getElementsByTagName("Skill").item(0).getTextContent() );
		this.wins = Integer.parseInt( ((Element)nList).getElementsByTagName("Wins").item(0).getTextContent() );
		this.games = Integer.parseInt( ((Element)nList).getElementsByTagName("Games").item(0).getTextContent() );

	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public PlayerID getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(PlayerID id) {
		this.id = id;
	}

	/**
	 * Gets the color.
	 *
	 * @return the color
	 */
	public FColor getColor() {
		return color;
	}

	/**
	 * Sets the color.
	 *
	 * @param color the new color
	 */
	public void setColor(FColor color) {
		this.color = color;
	}

	/**
	 * Gets the sex.
	 *
	 * @return the sex
	 */
	public Sex getSex() {
		return sex;
	}

	/**
	 * Sets the sex.
	 *
	 * @param sex the new sex
	 */
	public void setSex(Sex sex) {
		this.sex = sex;
	}

	/**
	 * Gets the skill.
	 *
	 * @return the skill
	 */
	public Skill getSkill() {
		return skill;
	}

	/**
	 * Sets the skill.
	 *
	 * @param skill the new skill
	 */
	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	/**
	 * Gets the wins.
	 *
	 * @return the wins
	 */
	public int getWins() {
		return wins;
	}

	/**
	 * Sets the wins.
	 *
	 * @param wins the new wins
	 */
	public void setWins(int wins) {
		this.wins = wins;
	}

	/**
	 * Gets the games.
	 *
	 * @return the games
	 */
	public int getGames() {
		return games;
	}

	/**
	 * Sets the games.
	 *
	 * @param games the new games
	 */
	public void setGames(int games) {
		this.games = games;
	}
	
	/* (non-Javadoc)
	 * @see XML.XMLSerializable#saveXML(org.w3c.dom.Document)
	 */
	public Element saveXML(Document document)
	{
	            // root element
	            Element root = document.createElement("Player");
	            
	            Element imie = document.createElement("Name");
	            imie.appendChild(document.createTextNode(name));
	            root.appendChild(imie);
	            
	           /* Element idgracza = document.createElement("ID");
	            idgracza.appendChild(document.createTextNode(id.toString()));
	            root.appendChild(idgracza);*/

	           /* Element kolor = document.createElement("Color");
	            kolor.appendChild(document.createTextNode(color.toString()));
	            root.appendChild(kolor);*/
	            
	            Element plec = document.createElement("Sex");
	            plec.appendChild(document.createTextNode(sex.toString()));
	            root.appendChild(plec);
	            
	            Element umiej = document.createElement("Skill");
	            umiej.appendChild(document.createTextNode(skill.toString()));
	            root.appendChild(umiej);
	            
	            Element wygrane = document.createElement("Wins");
	            wygrane.appendChild(document.createTextNode(wins+""));
	            root.appendChild(wygrane);
	            
	            Element gry = document.createElement("Games");
	            gry.appendChild(document.createTextNode(games+""));
	            root.appendChild(gry);
	            return root;

	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Player [name=" + name + ", id=" + id + ", color=" + color + ", sex=" + sex + ", skill=" + skill
				+ ", wins=" + wins + ", games=" + games + "]";
	}


}
