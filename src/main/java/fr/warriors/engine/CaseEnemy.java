package fr.warriors.engine;

import fr.warriors.engine.MapSelection.eventType;

public class CaseEnemy extends CaseGeneric {

	public String enemyType;
	public int enemyStrength;
	public int enemyLife;

	public CaseEnemy(int index, String enemy) {
		super(index);
		this.type = eventType.ENEMY;
		this.enemyType = enemy;

		if (enemy.equals("dragon")) {
			this.enemyLife = 15;
			this.enemyStrength = 4;
		} else if (enemy.equals("sorcerer")) {
			this.enemyLife = 9;
			this.enemyStrength = 2;
		} else if (enemy.equals("goblin")) {
			this.enemyLife = 6;
			this.enemyStrength = 1;
		} else {
			this.enemyLife = 0;
			this.enemyStrength = 0;
		}
	}

	@Override
	public GameState runCase(String gameId) {
		GameState fightStatus = (GameState) Warriors.gamesDict.get(gameId);
		CaseEnemy enCours = (CaseEnemy) ((MapSelection) fightStatus.map).getBoard().get(fightStatus.currentCase);
		Debuff débuffs = fightStatus.enemiesDebuffs;
		String logDetail1 = "";

		int enemysLife;
		boolean fucked = false;
		enemysLife = enCours.enemyLife;

		if (enemyType.equals("dragon"))
			enemysLife = enemyLife - débuffs.dragon - fightStatus.hero.getAttackLevel();
		else if (enemyType.equals("sorcerer"))
			enemysLife = enemyLife - débuffs.sorcerer - fightStatus.hero.getAttackLevel();
		else if (enemyType.equals("goblin"))
			enemysLife = enemyLife - débuffs.goblin - fightStatus.hero.getAttackLevel();

		if (enemyType.equals("dragon") && enemysLife > 0)
			fightStatus.enemiesDebuffs.dragon = enemysLife;
		else if (enemyType.equals("dragon") && enemysLife <= 0) {
			fightStatus.enemiesDebuffs.dragon = enCours.enemyStrength = 0;
			fucked = true;
		}
		if (enemyType.equals("sorcerer") && enemysLife > 0)
			fightStatus.enemiesDebuffs.sorcerer = enemysLife;
		else if (enemyType.equals("sorcerer") && enemysLife <= 0) {
			fightStatus.enemiesDebuffs.sorcerer = enCours.enemyStrength = 0;
			fucked = true;
		}
		if (enemyType.equals("goblin") && enemysLife > 0)
			fightStatus.enemiesDebuffs.goblin = enemysLife;
		else if (enemyType.equals("goblin") && enemysLife <= 0) {
			fightStatus.enemiesDebuffs.goblin = enCours.enemyStrength = 0;
			fucked = true;
		}

		if (fightStatus.hero.getClass() == (Warrior.class)) {
			((Warrior) fightStatus.hero).setLife(-enCours.enemyStrength);
		} else {
			((Wizard) fightStatus.hero).setLife(-enCours.enemyStrength);
		}

		if ((fightStatus.hero.getAttackLevel() >= enCours.enemyLife) || (fucked)) {
			logDetail1 = ".\nI blasted him, he died until he was dead, nothing remains but dust, plus I lost no life like a real PGM !";
		} else if (fightStatus.hero.getLife() <= 0) {
			logDetail1 = "........ and Died\n...like ... miserably...\n\n------------------   GAME OVER   ------------------";
		} else {
			logDetail1 = ".\nThe fucker didn't die, he's got " + enemysLife + " HP left and hit me before he fled,\n"
					+ "dealing me " + enCours.enemyStrength + " damage... Aouch !";
		}
		this.extraLog = "\nI came across a bloody " + enemyType + " I fought bravely." + logDetail1;

		return fightStatus;
	}
}
