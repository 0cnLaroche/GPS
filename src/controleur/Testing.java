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
		
/*		Graph g = new Graph();
		g.setNoeuds(new CSV("SystemGuidageRoutier/res/Coordonnees.csv"));
		g.setLiens(new CSV("SystemGuidageRoutier/res/liens.csv"));
		
		Noeud un = g.getNoeud("p");
		
		for (Lien l:un.getVoisinage()){
			System.out.println(l.toString());
			System.out.println(l.getNoeudUn().toString());
			System.out.println(l.getNoeudDeux().toString());
			System.out.println("\n");
		}
		
		g.calculCheminCourt(g.getNoeud("h"), g.getNoeud("s"));
		
		for (Noeud noeud : g.getNoeudCheminPlusCourt()) {
			System.out.println(noeud.toString());
		}
		
		System.out.println();
		
		for (int i = g.getNoeudCheminPlusCourt().size() - 1; i >= 0; i--) {
			System.out.print(g.getNoeudCheminPlusCourt().get(i).getNom() + " ---> ");
		}
		
		System.out.println("done");
		
		
		
		int i = 1;
		
		Graph g = new Graph();
		g.setNoeuds(new CSV("SystemGuidageRoutier/res/Coordonnees.csv"));
		g.setLiens(new CSV("SystemGuidageRoutier/res/liens.csv"));
		
		Noeud un = g.getNoeud("b");
		
		for (Lien l:un.getVoisinage()){
			System.out.println(l.toString());
			System.out.println(l.getNoeudUn().toString());
			System.out.println(l.getNoeudDeux().toString());
			System.out.println("\n");
		}
		
		for (Entry<Integer, Lien> entry : g.getLiens().entrySet()) {
			//System.out.println(i + " " + entry.toString() + "\n");
			System.out.println(entry.getValue().getNoeudUn().getCoordonnees().x);
			System.out.println(entry.getValue().getNoeudUn().getCoordonnees().y);
			
			System.out.println(entry.getValue().getNoeudDeux().getCoordonnees().x);
			System.out.println(entry.getValue().getNoeudDeux().getCoordonnees().y);
		}*/
	}

}
