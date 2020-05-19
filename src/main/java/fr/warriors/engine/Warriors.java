package fr.warriors.engine;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import fr.warriors.contracts.GameState;
import fr.warriors.contracts.Hero;
import fr.warriors.contracts.Map;
import fr.warriors.contracts.WarriorsAPI;

public class Warriors implements WarriorsAPI {
	

	@Override
	public List<? extends Hero> getHeroes() {
		List<Hero> availableHeroes = List.of(new Warrior(), new Wizard());
		return availableHeroes;
	}

	@Override
	public List<? extends Map> getMaps() {
		List<Map> availableMaps = List.of(new MapEmpty(), new MapStandard(), new MapGenerator());
		return availableMaps;
	}

	static HashMap<String, GameState> gamesDict = new HashMap<>();
	
	@Override
	public GameState createGame(String playerName, Hero hero, Map map) {
		fr.warriors.engine.GameState new_status = new fr.warriors.engine.GameState();
		new_status.playerName = playerName;
		new_status.hero = hero;
		new_status.map = map;
		new_status.gameId = new_status.setGameId();
		new_status.gameStatus = fr.warriors.contracts.GameStatus.IN_PROGRESS;
		new_status.lastLog = "";
		new_status.currentCase = 0;
		gamesDict.put(new_status.gameId, new_status);
		return new_status;
	}

	@Override
	public GameState nextTurn(String gameID) {
		fr.warriors.engine.GameState tmp_status = (fr.warriors.engine.GameState) gamesDict.get(gameID);
		tmp_status.currentCase = tmp_status.moveForward();
		tmp_status = tmp_status.playCase(tmp_status.currentCase);
		tmp_status.gameStatus = tmp_status.checkStatus();
		gamesDict.replace(tmp_status.gameId, tmp_status);
		return tmp_status;
	}

}
