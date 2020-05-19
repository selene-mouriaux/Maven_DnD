package fr.warriors.engine;

import fr.warriors.engine.MapSelection.*;

public class CaseSurprise extends CaseGeneric {

	public surpriseType surpriseType;
	public String surpriseItem;
	public int surprisePotency;

	public CaseSurprise(int index, fr.warriors.engine.MapSelection.surpriseType surpriseType, String surpriseItem) {
		super(index);
		this.type = eventType.SURPRISE;

		this.surpriseType = surpriseType;
		this.surpriseItem = surpriseItem;
		
		if (surpriseItem.equals("minor healing potion")) {
			this.surprisePotency = 1;
		} else if (surpriseItem.equals("standard healing potion")) {
			this.surprisePotency = 2;
		} else if (surpriseItem.equals("major healing potion")) {
			this.surprisePotency = 5;
		} else if (surpriseItem.equals("spell : fireball")) {
			this.surprisePotency = 7;
		} else if (surpriseItem.equals("spell : lightning")) {
			this.surprisePotency = 2;
		} else if (surpriseItem.equals("weapon : sword")) {
			this.surprisePotency = 5;
		} else if (surpriseItem.equals("weapon : mace")) {
			this.surprisePotency = 3;
		} else if (surpriseItem.equals("weapon : bow")) {
			this.surprisePotency = 1;
		} else {
			this.surprisePotency = 0;
		}
		
	}

	private boolean equipmentIsUsable(String gameId) {
		boolean usable = true;
		fr.warriors.engine.GameState tmp = (fr.warriors.engine.GameState) Warriors.gamesDict.get(gameId);
		if ((tmp.hero.getClass() == Warrior.class && !this.surpriseItem.contains("weapon"))
				|| (tmp.hero.getClass() == Wizard.class && !this.surpriseItem.contains("spell"))) {
			usable = false;
		} else {

		}
		return usable;
	}

	@Override
	public GameState runCase(String gameId) {
		fr.warriors.engine.GameState looting = (fr.warriors.engine.GameState) Warriors.gamesDict.get(gameId);
		CaseSurprise enCours = (CaseSurprise) ((MapSelection) looting.map).getBoard().get(looting.currentCase);


		switch (surpriseType) {
		case EQUIPMENT:
			if (equipmentIsUsable(gameId)) {
				if (looting.hero.getClass() == Warrior.class) {
					((Warrior) looting.hero).setAttackLevel(enCours.surprisePotency);
				} else {
					((Wizard) looting.hero).setAttackLevel(enCours.surprisePotency);
				}
				enCours.extraLog = "\n" + looting.hero.getName() + " found and equipped a " + enCours.surpriseItem + ", adding "
						+ enCours.surprisePotency + " points to his power !!";
			} else {
				enCours.extraLog = "\nFound a " + enCours.surpriseItem + ", but a " + looting.hero.getClass().getSimpleName() +
						" couldn't equip such item :(";
			}
			break;
		case POTION:
			if (looting.hero.getClass() == Warrior.class) {
				((Warrior) looting.hero).setLife(enCours.surprisePotency);
			} else {
				((Wizard) looting.hero).setLife(enCours.surprisePotency);
			}
			enCours.extraLog = "\n" + looting.hero.getName() + " found and drank a " + enCours.surpriseItem +
					", which helped him get his HPs back to " + looting.hero.getLife() + " HPs.";
			break;
		default:
		}

		return looting;
	}
}
