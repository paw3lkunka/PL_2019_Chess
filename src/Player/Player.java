package Player;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import Figures.FColor;
import XML.XMLSerializable;

public class Player implements XMLSerializable {

	String name;
	PlayerID id;
	FColor color;
	Sex sex;
	Skill skill;
	int wins;
	int games;
	
	public Player(String name, PlayerID id, FColor color, Sex sex, Skill skill, int wins, int games) {
		this.name = name;
		this.color = color;
		this.sex = sex;
		this.skill = skill;
		this.wins = wins;
		this.games = games;
		this.id = id;
	}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PlayerID getId() {
		return id;
	}

	public void setId(PlayerID id) {
		this.id = id;
	}

	public FColor getColor() {
		return color;
	}

	public void setColor(FColor color) {
		this.color = color;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public int getGames() {
		return games;
	}

	public void setGames(int games) {
		this.games = games;
	}
	
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

	@Override
	public String toString() {
		return "Player [name=" + name + ", id=" + id + ", color=" + color + ", sex=" + sex + ", skill=" + skill
				+ ", wins=" + wins + ", games=" + games + "]";
	}


}
