package controleur;

import java.awt.Point;
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
		g.setNoeuds(new CSV("res/Coordonnees.csv"));
		g.setLiens(new CSV("res/liens.csv"));
		
		Noeud un = g.getNoeud("a");
		
		for (Lien l:un.getVoisinage()){
			System.out.println(l.toString());
			System.out.println(l.getNoeudUn().toString());
			System.out.println(l.getNoeudDeux().toString());
		}
	}

}
