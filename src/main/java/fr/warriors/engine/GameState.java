package fr.warriors.engine;

import java.time.LocalDateTime;
import java.util.Random;

import fr.warriors.contracts.GameStatus;
import fr.warriors.contracts.Hero;
import fr.warriors.contracts.Map;
import fr.warriors.engine.MapSelection.eventType;

public class GameState implements fr.warriors.contracts.GameState {

	private static int rollDice() {
		return 1 + (int) (Math.random() * DICE_SIZE);
	}

	String playerName;
	String gameId;
	GameStatus gameStatus;
	Hero hero;
	Map map;
	String lastLog;
	int currentCase;
	Debuff enemiesDebuffs; //TODO : manage through DB as well later

	public static final int DICE_SIZE = 6;
	public String getPlayerName() {
		return this.playerName;
	}
	
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public void setGameStatus(GameStatus gameStatus) {
		this.gameStatus = gameStatus;
	}

	public void setHero(Hero hero) {
		this.hero = hero;
	}

	public void setCurrentCase(int currentCase) {
		this.currentCase = currentCase;
	}

	public GameState() {
		this.enemiesDebuffs = new Debuff();
	}

	@Override
	public String getGameId() {
		return this.gameId;
	}

	@Override
	public fr.warriors.contracts.GameStatus getGameStatus() {
		return this.gameStatus;
	}
	
	public Debuff getDebuff() {
		return this.enemiesDebuffs;
	}

	@Override
	public Hero getHero() {
		return this.hero;
	}

	@Override
	public Map getMap() {
		return this.map;
	}

	@Override
	public String getLastLog() {
		Map actualmap = this.map;
		String extraLog = ((MapSelection) actualmap).getBoard().get(currentCase).extraLog;
		System.out.println("\nPreviously on AMC The Walking " + hero.getName() + "...\nThe "
				+ hero.getClass().getSimpleName() + " I am was case : " + this.getCurrentCase() + " with "
				+ hero.getLife() + "HP.\nBehold my power ! I strike for " + hero.getAttackLevel()
				+ " damages at full force." + extraLog);
		return this.lastLog;
	}

	@Override
	public int getCurrentCase() {
		return this.currentCase;
	}

	public String setGameId() {
		return getPlayerName() + " - " + LocalDateTime.now().toString();
	}

	public int moveForward() {
		int roll = rollDice();
		if (this.currentCase + roll > this.map.getNumberOfCase() - 1) {
			this.currentCase = this.map.getNumberOfCase() - 1;
		} else {
			this.currentCase = this.currentCase + roll;
		}
		return this.currentCase;
	}

	public fr.warriors.contracts.GameStatus checkStatus() {
		Map actualmap = this.map;
		if (this.hero.getLife() <= 0)
			return fr.warriors.contracts.GameStatus.GAME_OVER;
		switch (this.currentCase) {
		case 63:
			((MapSelection) actualmap).getBoard().get(currentCase).extraLog = "\n\nI made it to the End of the game alive !\nGuess who's boss here?!\nThat's bloody ME bitches !";
			return fr.warriors.contracts.GameStatus.FINISHED;
		default:
			return fr.warriors.contracts.GameStatus.IN_PROGRESS;
		}
	}

	public GameState playCase(int currentCase) {

		Map actualmap = this.map;
		CaseGeneric event = ((MapSelection) actualmap).getBoard().get(currentCase);
		fr.warriors.engine.GameState playedCase;

		return playedCase = event.runCase(this.gameId);
	}

}
