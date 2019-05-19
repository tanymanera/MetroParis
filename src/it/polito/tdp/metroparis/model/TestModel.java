package it.polito.tdp.metroparis.model;

public class TestModel {

	public static void main(String[] args) {
		TestModel test = new TestModel();
		test.runTest();

	}

	private void runTest() {
		Model model = new Model();
		model.creaGrafo();
		System.out.println(model.getMetro());
		
	}

}
