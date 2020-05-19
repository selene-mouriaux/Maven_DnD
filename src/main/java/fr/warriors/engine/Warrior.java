package fr.warriors.engine;

public class Warrior implements fr.warriors.contracts.Hero{

	public static final int MAX_LIFE_WARRIOR = 10;
	public static final int MAX_ATTACK_LEVEL_WARRIOR = 10;
	
	private String name;
	private String image;
	private int life;
	private int attackLevel;

	public Warrior() {
		this.name = "Aragorn";
		this.image = "https://i.pinimg.com/564x/df/8a/52/df8a52d6805fd196139f8e09037069cf.jpg";
		this.life = 5;
		this.attackLevel = 5;
	}
	
	public int setLife(int value) {
		this.life = this.life + value;
		if (this.life > MAX_LIFE_WARRIOR)
			this.life = MAX_LIFE_WARRIOR;
		return this.life;
	}
	
	public int setAttackLevel(int value) {
		this.attackLevel = this.attackLevel + value;
		if (this.attackLevel > MAX_ATTACK_LEVEL_WARRIOR)
			this.attackLevel = MAX_ATTACK_LEVEL_WARRIOR;
		return this.attackLevel;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	@Override
	public String getImage() {
		// TODO Auto-generated method stub
		return this.image;
	}

	@Override
	public int getLife() {
		// TODO Auto-generated method stub
		return this.life;
	}

	@Override
	public int getAttackLevel() {
		// TODO Auto-generated method stub
		return attackLevel;
	}

}
