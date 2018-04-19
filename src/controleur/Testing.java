package controleur;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Map.Entry;

import modele.*;
import outils.CSV;

/**
 * Cette classe n'a aucune utilités dans l'application, seulement créé pour tester 
 * @author Samuel
 *
 */
public class Testing {

	public static void main(String[] args) {
		

		Graph g = new Graph();
		g.setNoeuds(new CSV("./res/Coordonnees.csv"));
		g.setLiens(new CSV("./res/liens.csv"));
		System.out.println("Graph généré");
		

		
		//System.out.println(g.getNoeuds().toString());
		
		g.calculCheminCourt(g.getNoeud("a"), g.getNoeud("m"));
		//System.out.println(g.getNoeudCheminPlusCourt());
		Graph trajet = g.getTrajet();
		System.out.println(trajet.getListeNoeuds().toString());
		System.out.println(g.getNoeudCheminPlusCourt().toString());
		
		
		
	}

}
