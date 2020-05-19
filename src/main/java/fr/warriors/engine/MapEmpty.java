package fr.warriors.engine;
import java.util.ArrayList;

public class MapEmpty extends MapSelection {
	
	private String name;
	private int numberOfCase;
	private ArrayList<CaseGeneric> board;
	
	public MapEmpty() {
		this.name = "Map vide";
		this.numberOfCase = 64;
		ArrayList<CaseGeneric> board = new ArrayList<CaseGeneric>();
		for (int i=0; i<numberOfCase; i++) {
			CaseEmpty tmp_case =new CaseEmpty(i);
			board.add(tmp_case);
		}
		this.board = board;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	@Override
	public int getNumberOfCase() {
		// TODO Auto-generated method stub
		return this.numberOfCase;
	}

	public ArrayList<CaseGeneric> getBoard() {
		return board;
	}

	
	
	
}
