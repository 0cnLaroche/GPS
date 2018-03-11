package controleur;

import modele.CSV;
import modele.Graph;

public class Testing {

	public static void main(String[] args) {
		Graph g = new Graph();
		CSV c = new CSV("res/liens.csv");
		c.print();
	}

}
