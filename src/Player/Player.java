package Player;
import Figures.FColor;

public class Player {

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
	
	

}
