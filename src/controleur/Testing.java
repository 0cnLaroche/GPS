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
		CSV c = new CSV("res/liens.csv");
		c.print();
		
		//test pour comparer la signature d'un lien. 
		//Est-ce que (a,b) devrait etre equivalent a (b,a)??
		Lien un,deux;
		Noeud a,b,k,l;
		a = new Noeud("a", new Point(10,23));
		b = new Noeud("b",new Point(0,33));
		un = new Lien(a,b);
		
		k = new Noeud("b",new Point(100,43));
		l = new Noeud("a",new Point(8,10));
		deux = new Lien(k,l);
		
		System.out.println(un.getHash());
		System.out.println(deux.getHash());
	}

}
