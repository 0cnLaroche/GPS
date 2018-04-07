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
		
		String a = "blah";
		String b = "toto";
		
		System.out.println(a.hashCode() + b.hashCode());
		System.out.println(b.hashCode() + a.hashCode());
		
		Graph g = new Graph();
		g.setNoeuds(new CSV("SystemGuidageRoutier/res/Coordonnees.csv"));
		g.setLiens(new CSV("SystemGuidageRoutier/res/liens.csv"));
		System.out.println("Graph généré");
		

		
		System.out.println(g.getNoeuds().toString());
		
		g.calculCheminCourt(g.getNoeud("a"), g.getNoeud("g"));
		ArrayList<Noeud> trajet = g.getPlusCourtChemin();
		System.out.println(trajet.get(0).getNom());
		
		
		
	}

}
