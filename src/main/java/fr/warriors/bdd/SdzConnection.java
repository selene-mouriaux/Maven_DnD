package fr.warriors.bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SdzConnection {
	// CTRL + SHIFT + O pour générer les imports

	private static String URL = "jdbc:mysql://localhost:3306/dnd_db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private static String LOGIN = "magicneuron";
	private static String PASSWORD = "santoryu";
	private static Connection connect = null;

	// Constructeur privé
	private SdzConnection() {
		try {
			connect = DriverManager.getConnection(URL, LOGIN, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Méthode qui va nous retourner notre instance et la créer si elle n'existe pas
	public static Connection getInstance() {
		if (connect == null) {
			new SdzConnection();
		}
		return connect;
	}
}
