package fr.warriors.engine;

import fr.warriors.engine.MapSelection.eventType;

public class CaseEmpty extends CaseGeneric{
	
	public CaseEmpty(int index) {
		super(index);
		this.type = eventType.EMPTY;
	}
	
	public GameState runCase(String gameId) {
		fr.warriors.engine.GameState wandering = (fr.warriors.engine.GameState) Warriors.gamesDict.get(gameId);

		this.extraLog = "\nLast case was boring as hell, "
				+ "nothing happened, at least i didn't get my ass kicked --\"";
		return wandering;
	}
}
