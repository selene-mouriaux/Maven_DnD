package fr.warriors.bdd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.warriors.contracts.GameStatus;
import fr.warriors.contracts.Hero;
import fr.warriors.engine.GameState;
import fr.warriors.engine.Warriors;

public class DaoGameState extends DAO {

	public DaoGameState(Connection conn) {
		super(conn);
	}

	/**
	 * Method creates a GameState from DB data, used to start game from a save.
	 */
	@Override
	public GameState create(Object obj) {
		GameState dbGameState = new GameState();
		try {
			Statement state = this.connect.createStatement();
			ResultSet result = state.executeQuery("SELECT * FROM hero WHERE Id = " + (String) obj);
			while (result.next()) {
				dbGameState = generatingDaoGameState(dbGameState, result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dbGameState;
	}

	/**
	 * Method aimed at deleting an entry in the DB. In other words, deleting a save
	 * file.
	 */
	@Override
	public Object delete(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Method destined to update the DB data whose Id is active with actual game
	 * contents upon each NextTurn() call.
	 */
	@Override
	public GameState update(Object obj) {
		String id = (String) obj;
		GameState actualStatus = (GameState) Warriors.gamesDict.get(id);
		try {
			Statement state = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet result = state.executeQuery("SELECT Life, Power, CurrentCase, MapType FROM hero");
			while (result.next()) {
				if (result.getString("Id").equals(id)) {
					result.updateInt("Life", actualStatus.getHero().getLife());
					result.updateInt("Power", actualStatus.getHero().getAttackLevel());
					result.updateInt("CurrentCase", actualStatus.getCurrentCase());
					result.updateString("MapType", actualStatus.getMap().getName());
					result.updateRow();
				} else {

				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Method selects and returns a whole GameState linked to the specified Id.
	 */
	@Override
	public GameState findById(String id) {
		GameState dbGameState = new GameState();
		try {
			Statement state = this.connect.createStatement();
			ResultSet result = state.executeQuery("SELECT * FROM hero");
			while (result.next()) {
				if (result.getString("Name").contains(id) || result.getString("Id").contains(id)
						|| result.getString("PlayerName").contains(id)) {
					dbGameState = generatingDaoGameState(dbGameState, result);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dbGameState;
	}

	/**
	 * Method returns all existing GameStates in the DB.
	 */
	@Override
	public List<GameState> findAll() {
		GameState dbGameState = new GameState();
		List<GameState> dbGameStateList = new ArrayList<GameState>();
		try {
			Statement state = this.connect.createStatement();
			ResultSet result = state.executeQuery("SELECT * FROM hero");
			while (result.next()) {
				dbGameStateList.add(generatingDaoGameState(dbGameState, result));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dbGameStateList;
	}

	private GameState generatingDaoGameState(GameState dbGameState, ResultSet result) throws SQLException {
		DaoHero daoHero = new DaoHero(SdzConnection.getInstance());
		dbGameState.setGameId(result.getString("Id"));
		dbGameState.setPlayerName(result.getString("PlayerName"));
		dbGameState.setHero(daoHero.findById(result.getString("Id")));
		dbGameState.setCurrentCase(result.getInt("CurrentCase"));
		dbGameState.setGameStatus(GameStatus.IN_PROGRESS);
		return dbGameState;
	}
}
