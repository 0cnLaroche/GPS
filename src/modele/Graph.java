package modele;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

import outils.CSV;

public class Graph  {
	
	private HashMap<String,Noeud> noeuds; // indexé par lettres de l'alphabet
	private HashMap<Integer,Lien> liens;
	
	public Graph(){
		noeuds = new HashMap<String,Noeud>();
		liens = new HashMap<Integer,Lien>();
	}
	
	public Noeud getNoeud(String nom){
		return this.noeuds.get(nom);
	}
	
	public Lien getLien(int index){
		return this.liens.get(index);
	}

	public void addLien(Noeud un, Noeud deux){
		Lien l = new Lien(un,deux);
		liens.put(l.hashCode(), l);
		un.addVoisin(l);
		deux.addVoisin(l);	
	}
	
	public void addNoeud(Noeud noeud){
		this.noeuds.put(noeud.getNom(), noeud);
	}
	
	public void setLiens(CSV source){
		for (ArrayList<String> champs:source){
			
			if (source.get(0).equals(champs)) // La premiere rangee contiens les titres. On ignore
				continue;
			
			String[] split = champs.get(1).split(";"); //Sépare la colonne de gauche
			for (String s:split){
				this.addLien(noeuds.get(champs.get(0)), noeuds.get(s));	//Appelle la methode de classe
																		//Les noeuds seront charges de leurs voisins
			}	
		}
	}
	
	public void setNoeuds(CSV source){
		for (ArrayList<String> champs:source){
			
			if (source.get(0).equals(champs)) // La premiere rangee contiens les titres. On ignore
				continue;
			
			String nom = champs.get(0);
			Integer x = Integer.parseInt(champs.get(1));
			Integer y = Integer.parseInt(champs.get(2));
			Noeud n = new Noeud(nom, new Point(x,y));
			this.addNoeud(n);
		}
	}



}
