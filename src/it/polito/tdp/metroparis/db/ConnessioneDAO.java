package it.polito.tdp.metroparis.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.metroparis.model.StazioniConnesse;

public class ConnessioneDAO {

	public List<StazioniConnesse> listConnesse(){
		List<StazioniConnesse> connesse = new LinkedList<>();
		final String sql = "SELECT id_stazP, id_stazA FROM connessione;";
		Connection conn = DBConnect.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				connesse.add(new StazioniConnesse(rs.getInt("id_stazP"), rs.getInt("id_stazA")));
			}
			
			conn.close();
			return connesse;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("Errore di connessione al Database.");
		}
		
	}
}
