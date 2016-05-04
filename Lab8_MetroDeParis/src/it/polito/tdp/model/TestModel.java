package it.polito.tdp.model;

public class TestModel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MetroModel m = new MetroModel();
		
		m.generaGrafo();
		
		System.out.println(m.getCammino("Argentine", "Athis Mons"));

	}

}
