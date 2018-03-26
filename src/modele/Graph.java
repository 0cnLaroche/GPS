package modele;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

import outils.CSV;

public class Graph  {
	
	private HashMap<String,Noeud> noeuds; // indexé par lettres de l'alphabet
	private HashMap<Integer,Lien> liens;
	private Point coordonnnePointAjour;
	
	/**
	 * Methodes accesseurs
	 */
	
	
	/**
	 * @return the noeuds
	 */
	public HashMap<String, Noeud> getNoeuds() {
		return noeuds;
	}

	/**
	 * @return the coordonnnePointAjour
	 */
	public Point getCoordonnnePointAjour() {
		return coordonnnePointAjour;
	}

	/**
	 * @param coordonnnePointAjour the coordonnnePointAjour to set
	 */
	public void setCoordonnnePointAjour(Point coordonnnePointAjour) {
		this.coordonnnePointAjour = coordonnnePointAjour;
	}

	/**
	 * @param noeuds the noeuds to set
	 */
	public void setNoeuds(HashMap<String, Noeud> noeuds) {
		this.noeuds = noeuds;
	}

	/**
	 * @return the liens
	 */
	public HashMap<Integer, Lien> getLiens() {
		return liens;
	}

	/**
	 * @param liens the liens to set
	 */
	public void setLiens(HashMap<Integer, Lien> liens) {
		this.liens = liens;
	}

	
	/**
	 * constructeur de class
	 */
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
			
			Integer x = (int) Double.parseDouble(champs.get(1)); //La methode getX() de Point retourne un Double
			Integer y = (int) Double.parseDouble(champs.get(2)); //Mais les variables x et y sont des int
																 //Il faut donc faire la conversion de Double à int
			Noeud n = new Noeud(nom, new Point(x,y));
			this.addNoeud(n);
		}
	}
}
