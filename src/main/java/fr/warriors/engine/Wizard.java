package fr.warriors.engine;

public class Wizard implements fr.warriors.contracts.Hero {

	public static final int MAX_LIFE_WIZARD = 6;
	public static final int MAX_ATTACK_LEVEL_WIZARD = 15;

	private String id;
	private String name;
	private String image;
	private int life;
	private int attackLevel;

	public Wizard() {
		this.name = "Gandalf";
		this.image = "https://friendlystock.com/wp-content/uploads/2019/10/3-wizard-with-beard-cartoon-clipart.jpg";
		this.life = 3;
		this.attackLevel = 8;
	}
	public Wizard(String name, String image, int life, int attackLevel) {
		this.name = name;
		this.image = image;
		this.life = life;
		this.attackLevel = attackLevel;
	}

	public int setLife(int value) {
		this.life = this.life + value;
		if (this.life > MAX_LIFE_WIZARD)
			this.life = MAX_LIFE_WIZARD;
		return this.life;
	}

	public int setAttackLevel(int value) {
		this.attackLevel = this.attackLevel + value;
		if (this.attackLevel > MAX_ATTACK_LEVEL_WIZARD)
			this.attackLevel = MAX_ATTACK_LEVEL_WIZARD;
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

	public void setName(String name) {
		this.name = name;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
