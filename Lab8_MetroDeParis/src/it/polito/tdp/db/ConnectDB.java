package it.polito.tdp.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {

	private static final String URL = "jdbc:mysql://localhost/metroparis?user=root";

	public static Connection getConnection() {

		try {
			Connection conn = DriverManager.getConnection(URL);
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("errore nella connessione", e);
		}
	}

}
