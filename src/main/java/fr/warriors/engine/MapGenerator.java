package fr.warriors.engine;

import java.util.ArrayList;
import java.util.Arrays;

import fr.warriors.engine.MapSelection.surpriseType;

public class MapGenerator extends MapSelection {

	private String name;
	private int numberOfCase;
	private ArrayList<CaseGeneric> board;

	public MapGenerator() {
		this.name = "Map random";
		this.numberOfCase = 64;

		CaseGeneric[] tmp_board = new CaseGeneric[64];
		// {1:dragon, 2:sorcerer, 3:goblin, 4:bow, 5:mace, 6:sword, 7:lightning, 8:fireball, 9:minorHP,
		// 10:standardHP, 11:majorHP, 12:nothing}
		int boardElements[] = { 4, 10, 10, 5, 3, 2, 5, 2, 5, 3, 1, 14 }, randomElement;
		for (int index = 0; index < this.numberOfCase; index++) {
			randomElement = (int) (Math.random() * boardElements.length);
			if (boardElements[randomElement] > 0) {
				boardElements[randomElement] -= 1;
				switch (randomElement) {
				case 0:
					tmp_board[index] = new CaseEnemy(index - 1, "dragon");
					break;
				case 1:
					tmp_board[index] = new CaseEnemy(index - 1, "sorcerer");
					break;
				case 2:
					tmp_board[index] = new CaseEnemy(index - 1, "goblin");
					break;
				case 3:
					tmp_board[index] = new CaseSurprise(index - 1, surpriseType.EQUIPMENT, "weapon : bow");
					break;
				case 4:
					tmp_board[index] = new CaseSurprise(index - 1, surpriseType.EQUIPMENT, "weapon : mace");
					break;
				case 5:
					tmp_board[index] = new CaseSurprise(index - 1, surpriseType.EQUIPMENT, "weapon : sword");
					break;
				case 6:
					tmp_board[index] = new CaseSurprise(index - 1, surpriseType.EQUIPMENT, "spell : lightning");
					break;
				case 7:
					tmp_board[index] = new CaseSurprise(index - 1, surpriseType.EQUIPMENT, "spell : fireball");
					break;
				case 8:
					tmp_board[index] = new CaseSurprise(index - 1, surpriseType.POTION, "minor healing potion");
					break;
				case 9:
					tmp_board[index] = new CaseSurprise(index - 1, surpriseType.POTION, "standard healing potion");
					break;
				case 10:
					tmp_board[index] = new CaseSurprise(index - 1, surpriseType.POTION, "major healing potion");
					break;
				case 11:
					tmp_board[index] = new CaseEmpty(index - 1);
					break;
				default:
				}
			} else {
				index -= 1 ;
			}
		}
		// this.board = (ArrayList<CaseGeneric>) Arrays.asList(tmp_board);
		// doesn't work, hence replaced with hard casting as follows :

		ArrayList<CaseGeneric> board = new ArrayList<CaseGeneric>();
		for (int i = 0; i < numberOfCase; i++) {
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

	public ArrayList<CaseGeneric> getBoard() {
		return board;
	}
}