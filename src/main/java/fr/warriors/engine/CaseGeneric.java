package fr.warriors.engine;

import fr.warriors.engine.MapSelection.eventType;

public class CaseGeneric {


	static int index;
	public eventType type;
	public String extraLog;


	public CaseGeneric(int index) {
		this.index = index;
		extraLog = "";
	}

	public int getIndex() {
		return this.index;
	}
	
	public GameState runCase(String gameId) {
		// overridden in empty & surprise subcase types
		return null;
	}


//	private CaseGeneric applyType(String lequel, eventType type) {
//		CaseGeneric choice = new CaseEnemy(index, lequel, 0);
//		return choice;
//	}
//	
//	public GameState

}
