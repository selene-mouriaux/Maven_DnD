package fr.warriors.engine;

import java.util.HashMap;
import java.util.List;

import fr.warriors.bdd.DaoGameState;
import fr.warriors.bdd.DaoHero;
import fr.warriors.bdd.SdzConnection;
import fr.warriors.contracts.GameState;
import fr.warriors.contracts.Hero;
import fr.warriors.contracts.Map;
import fr.warriors.contracts.WarriorsAPI;

public class Warriors implements WarriorsAPI {

	private DaoHero daoHero = new DaoHero(SdzConnection.getInstance());
	private DaoGameState daoGameState = new DaoGameState(SdzConnection.getInstance());

	@Override
	public List<? extends Hero> getHeroes() {
		List<Hero> availableHeroes = null;
		availableHeroes = daoHero.findAll();
		availableHeroes.add(new Warrior());
		availableHeroes.add(new Wizard());
//		List<Hero> availableHeroes = List.of(new Warrior(), new Wizard());
		return availableHeroes;
	}

	@Override
	public List<? extends Map> getMaps() {
		List<Map> availableMaps = List.of(new MapEmpty(), new MapStandard(), new MapGenerator());
		return availableMaps;
	}

	public static HashMap<String, GameState> gamesDict = new HashMap<>();

	@Override
	public GameState createGame(String playerName, Hero hero, Map map) {
//		Warrior matchPlayer = (Warrior) daoGameState.findById(playerName);
		GameState matchHeroName = daoGameState.findById(hero.getName());
		fr.warriors.engine.GameState new_status = new fr.warriors.engine.GameState();
//		if (matchPlayer.getId() != null) {
//			new_status = daoGameState.create(matchPlayer.getId());
//			gamesDict.put(new_status.gameId, new_status);
//			return new_status; } else 
		if (matchHeroName.getGameId() != null) {
			new_status = daoGameState.create(matchHeroName.getGameId());
			new_status.lastLog = "";
			new_status.map = map;
			gamesDict.put(new_status.gameId, new_status);
			return new_status;
		} else {
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
	}

	@Override
	public GameState nextTurn(String gameID) {
		fr.warriors.engine.GameState tmp_status = (fr.warriors.engine.GameState) gamesDict.get(gameID);
		tmp_status.currentCase = tmp_status.moveForward();
		tmp_status = tmp_status.playCase(tmp_status.currentCase);
		tmp_status.gameStatus = tmp_status.checkStatus();
		gamesDict.replace(tmp_status.gameId, tmp_status);
		daoGameState.update(gameID);
		return tmp_status;
	}

}
