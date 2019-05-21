package it.polito.tdp.metroparis.db;

import java.sql.Connection;

import it.polito.tdp.metroparis.model.Model;

public class TestDAO {

	public static void main(String[] args) {
		
		Model model = new Model();
		
		try {
			Connection connection = DBConnect.getConnection();
			connection.close();
			System.out.println("Connection Test PASSED");
			
			MetroDAO dao = new MetroDAO() ;
			
			System.out.println(dao.getAllFermate(model.getIdentityMap())) ;
			System.out.println(dao.getAllLinee()) ;

		} catch (Exception e) {
			throw new RuntimeException("Test FAILED", e);
		}
	}

}
