package it.polito.tdp.db;

import it.polito.tdp.model.Fermata;

public class TestDAO {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MetroDAO dao = new MetroDAO();
		
		for(Fermata t: dao.elencoFermate())
			System.out.println(t.getNome());

	}

}
