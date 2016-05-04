package it.polito.tdp.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.model.Connessione;
import it.polito.tdp.model.Fermata;
import it.polito.tdp.model.Linea;

public class MetroDAO {

	public List<Fermata> elencoFermate() {

		List<Fermata> fermate = new LinkedList<Fermata>();

		Connection conn = ConnectDB.getConnection();

		String sql = "select * from fermata";

		PreparedStatement st;

		try {
			st = conn.prepareStatement(sql);

			ResultSet res = st.executeQuery();

			while (res.next()) {
				Fermata f1 = new Fermata(res.getInt("id_fermata"), res.getString("nome"));
				f1.setX(res.getDouble("coordX"));
				f1.setY(res.getDouble("coordY"));
				fermate.add(f1);
			}

			res.close();
			conn.close();

			return fermate;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<Connessione> elencoConnesioni() {

		List<Connessione> connessioni = new LinkedList<Connessione>();

		Connection conn = ConnectDB.getConnection();

		String sql = "select * from connessione";

		PreparedStatement st;

		try {
			st = conn.prepareStatement(sql);

			ResultSet res = st.executeQuery();

			while (res.next()) {
				Connessione c1 = new Connessione(res.getInt("id_connessione"));
				c1.setCodiceLinea(res.getInt("id_linea"));
				c1.setCodiceFermataPartenza(res.getInt("id_stazP"));
				c1.setCodiceFermataArrivo(res.getInt("id_stazA"));
				connessioni.add(c1);
			}

			res.close();
			conn.close();

			return connessioni;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<Linea> elencoLinee() {
		
		List<Linea> linee = new LinkedList<Linea>();

		Connection conn = ConnectDB.getConnection();

		String sql = "select * from linea";

		PreparedStatement st;

		try {
			st = conn.prepareStatement(sql);

			ResultSet res = st.executeQuery();

			while (res.next()) {
				Linea l1 = new Linea(res.getInt("id_linea"));
				l1.setNome(res.getString("nome"));
				l1.setVelocita(res.getDouble("velocita"));
				l1.setIntervallo(res.getDouble("intervallo"));
				l1.setColore("colore");
				linee.add(l1);
			}

			res.close();
			conn.close();

			return linee;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}


}
