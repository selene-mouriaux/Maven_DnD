package fr.warriors.bdd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fr.warriors.contracts.Hero;
import fr.warriors.engine.Warrior;
import fr.warriors.engine.Wizard;

public class DaoHero extends DAO<Hero> {

//	public static Scanner sc = new Scanner(System.in);

	public DaoHero(Connection conn) {
		super(conn);
	}

	@Override
	public Hero create(Hero obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Hero delete(Hero obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Hero update(Hero obj) {

		return null;
	}

	@Override
	public Hero findById(String id) {
		Hero dbHero = new Warrior();
		try {
			Statement state = this.connect.createStatement();
			ResultSet result = state.executeQuery("SELECT * FROM hero");
			while (result.next()) {
				if (result.getString("Id").contains(id) || result.getString("Id").equals(id)
						|| result.getString("Name").equals(id) || result.getString("Name").contains(id)) {
					dbHero = generateHero(dbHero, result);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dbHero;
	}

	@Override
	public List<Hero> findAll() {
		List<Hero> dbHeroesList = new ArrayList<Hero>();
		Hero dbHero = new Warrior();
		try {
			Statement state = this.connect.createStatement();
			ResultSet result = state.executeQuery("SELECT * FROM hero");
			while (result.next()) {
				dbHeroesList.add(generateHero(dbHero, result));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dbHeroesList;
	}

	private Hero generateHero(Hero dbHero, ResultSet result) throws SQLException {
		if (result.getString("Type").equals("Warrior")) {
			Warrior newInput = new Warrior(result.getString("Name"), result.getString("Image"), result.getInt("Life"),
					result.getInt("Power"));
			newInput.setId(result.getString("Id"));
			return newInput;
		} else {
			Wizard newInput = new Wizard(result.getString("Name"), result.getString("Image"), result.getInt("Life"),
					result.getInt("Power"));
			newInput.setId(result.getString("Id"));
			return newInput;
		}
	}

}
