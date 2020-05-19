package fr.warriors.engine;

import java.util.ArrayList;

public abstract class MapSelection implements fr.warriors.contracts.Map{
	
	public enum eventType {
		EMPTY, SURPRISE, ENEMY
	}	
	public enum surpriseType {
		EQUIPMENT, POTION
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumberOfCase() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public ArrayList<CaseGeneric> getBoard() {
		return null;
	}

}
