package it.polito.tdp.metroparis.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javadocmd.simplelatlng.LatLng;

import it.polito.tdp.metroparis.model.Connessione;
import it.polito.tdp.metroparis.model.Fermata;
import it.polito.tdp.metroparis.model.Linea;

public class MetroDAO {

	public List<Fermata> getAllFermate() {

		final String sql = "SELECT id_fermata, nome, coordx, coordy FROM fermata ORDER BY nome ASC";
		List<Fermata> fermate = new ArrayList<Fermata>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Fermata f = new Fermata(rs.getInt("id_Fermata"), rs.getString("nome"),
						new LatLng(rs.getDouble("coordx"), rs.getDouble("coordy")));
				fermate.add(f);
			}

			st.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore di connessione al Database.");
		}
		
		return fermate;
	}

	public List<Linea> getAllLinee() {
		final String sql = "SELECT id_linea, nome, velocita, intervallo FROM linea ORDER BY nome ASC";

		List<Linea> linee = new ArrayList<Linea>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Linea f = new Linea(rs.getInt("id_linea"), rs.getString("nome"), rs.getDouble("velocita"),
						rs.getDouble("intervallo"));
				linee.add(f);
			}

			st.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore di connessione al Database.");
		}

		return linee;
	}

	public List<Connessione> getAllConnessioni() {
		final String sql = "SELECT connessione.id_connessione, linea.id_linea, " +
	
				"fermataP.id_fermata AS id_partenza, fermataP.nome AS partenza, " +
				"fermataP.coordX AS X_partenza, fermataP.coordY AS Y_partenza, " + 
				
				"fermataA.id_fermata AS id_arrivo, fermataA.nome AS arrivo, " +
				"fermataA.coordX AS X_arrivo, fermataA.coordY AS Y_arrivo " + 
				
				"FROM connessione JOIN linea JOIN fermata AS fermataP JOIN fermata AS fermataA " + 
				
				"WHERE connessione.id_linea = linea.id_linea AND " +
				"connessione.id_stazP=fermataP.id_fermata AND " +
				"connessione.id_stazA=fermataA.id_fermata;";

		List<Connessione> connessioni = new ArrayList<>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Linea linea = new Linea(rs.getInt("id_linea"));
				Fermata partenza = new Fermata(rs.getInt("id_partenza"), rs.getString("partenza"),
						new LatLng(rs.getLong("X_partenza"),rs.getLong("Y_partenza")));
				Fermata arrivo = new Fermata(rs.getInt("id_arrivo"), rs.getString("arrivo"),
						new LatLng(rs.getLong("X_arrivo"),rs.getLong("Y_arrivo")));
				Connessione connessione = new Connessione(rs.getInt("id_connessione"), linea, partenza, arrivo);
				connessioni.add(connessione);
			}

			st.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore di connessione al Database.");
		}

		return connessioni;
	}

}
