package fr.warriors.engine;

import java.util.Scanner;

public class Warrior implements fr.warriors.contracts.Hero{
	public static Scanner sc = new Scanner(System.in);
	public static final int MAX_LIFE_WARRIOR = 10;
	public static final int MAX_ATTACK_LEVEL_WARRIOR = 10;
	
	private String id;
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
	public Warrior(String name, String image, int life, int attackLevel) {
		this.name = name;
		this.image = image;
		this.life = life;
		this.attackLevel = attackLevel;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setImage(String image) {
		this.image = image;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
