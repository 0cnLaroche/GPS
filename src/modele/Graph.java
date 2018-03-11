package modele;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

import outils.CSV;

public class Graph  {
	
	private HashMap<String,Noeud> noeuds; // indexé par lettres de l'alphabet
	private HashMap<Integer,Lien> liens;
	
	
	
	public void setNoeuds(CSV source){
		for (ArrayList<String> l:source){
			String nom = l.get(0);
			Integer x = Integer.parseInt(l.get(1));
			Integer y = Integer.parseInt(l.get(2));
			Noeud n = new Noeud(nom, new Point(x,y));
			
		}
	}
	public void setLiens(CSV source){
		
	}



}
