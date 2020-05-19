package fr.warriors.engine;
import java.util.ArrayList;

public class MapStandard extends MapSelection {
	
	private String name;
	private int numberOfCase;
	private ArrayList<CaseGeneric> board;
	
	public MapStandard() {
		this.name = "Map Standard";
		this.numberOfCase = 64;
		
		CaseGeneric[] tmp_board = new CaseGeneric[64];
		for (int i = 0; i < this.numberOfCase; i++)
			tmp_board[i] = new CaseEmpty(i);
		int[] dragonsSpots = {45, 52, 56, 62};
		for (int index : dragonsSpots)
			tmp_board[index] = new CaseEnemy(index-1, "dragon");
		int[] sorcerersSpots = {10, 20, 25, 32, 35, 36, 37, 40, 44, 47};
		for (int index : sorcerersSpots)
			tmp_board[index] = new CaseEnemy(index-1, "sorcerer");
		int[] gobelinsSpots = {3, 6, 9 , 12, 15, 18, 21, 24, 27, 30};
		for (int index : gobelinsSpots)
			tmp_board[index] = new CaseEnemy(index-1, "goblin");
		int[] bowsSpots = {2, 11, 14, 19, 26};
		for (int index : bowsSpots)
			tmp_board[index] = new CaseSurprise(index-1, surpriseType.EQUIPMENT,"weapon : bow");
		int[] macesSpots = {5, 22, 38};
		for (int index : macesSpots)
			tmp_board[index] = new CaseSurprise(index-1, surpriseType.EQUIPMENT,"weapon : mace");
		int[] swordsSpots = {42, 53};
		for (int index : swordsSpots)
			tmp_board[index] = new CaseSurprise(index-1, surpriseType.EQUIPMENT,"weapon : sword");
		int[] spellLightningSpots = {1, 4 , 8, 17, 23};
		for (int index : spellLightningSpots)
			tmp_board[index] = new CaseSurprise(index-1, surpriseType.EQUIPMENT,"spell : lightning");
		int[] spellFireballSpots = {48, 49};
		for (int index : spellFireballSpots)
			tmp_board[index] = new CaseSurprise(index-1, surpriseType.EQUIPMENT,"spell : fireball");
		int[] flaskMinorSpots = {7, 13, 28, 29, 33};
		for (int index : flaskMinorSpots)
			tmp_board[index] = new CaseSurprise(index-1, surpriseType.POTION,"minor healing potion");
		int[] flaskStandardSpots = {31, 39, 43};
		for (int index : flaskStandardSpots)
			tmp_board[index] = new CaseSurprise(index-1, surpriseType.POTION,"standard healing potion");
		int[] flaskMajorSpots = {41};
		for (int index : flaskMajorSpots)
			tmp_board[index] = new CaseSurprise(index-1, surpriseType.POTION,"major healing potion");
		
		//	this.board = (ArrayList<CaseGeneric>) Arrays.asList(tmp_board);
		//	doesn't work, hence replaced with hard casting as follows :
		
		ArrayList<CaseGeneric> board = new ArrayList<CaseGeneric>();
		for (int i=0; i<numberOfCase; i++) {
			board.add(tmp_board[i]);
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

	@Override
	public ArrayList<CaseGeneric> getBoard() {
		return board;
	}
}