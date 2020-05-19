package fr.warriors.client.console;

import java.util.List;

import fr.warriors.contracts.GameState;
import fr.warriors.contracts.Hero;
import fr.warriors.contracts.Map;
import fr.warriors.contracts.WarriorsAPI;

public class Warriors implements WarriorsAPI {

	@Override
	public List<? extends Hero> getHeroes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<? extends Map> getMaps() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameState createGame(String playerName, Hero hero, Map map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameState nextTurn(String gameID) {
		// TODO Auto-generated method stub
		return null;
	}

}
