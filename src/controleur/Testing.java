package controleur;

import modele.*;

/**
 * Cette classe n'a aucune utilités dans l'application, seulement créé pour tester 
 * @author Samuel
 *
 */
public class Testing {

	public static void main(String[] args) {
		Graph g = new Graph();
		CSV c = new CSV("res/liens.csv");
		//c.print();
		
		//test pour comparer la signature d'un lien. 
		//Est-ce que (a,b) devrait etre equivalent a (b,a)??
		Lien un,deux;
		Noeud a,b,k,l;
		a = new Noeud("a");
		b = new Noeud("b");
		un = new Lien(a,b,1.0);
		
		k = new Noeud("b");
		l = new Noeud("a");
		deux = new Lien(k,l,1.0);
		
		System.out.println(un.getHash());
		System.out.println(deux.getHash());
	}

}
