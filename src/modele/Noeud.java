package modele;

import java.awt.Point;
import java.util.ArrayList;

public class Noeud {

	private String nom;

	private Point coordonnees;

	private boolean statu; // defini si le plus court chemin vers le noeud est permantent ou temporel

	private double longueurChemin; // definis le temps ou la longueur vers ce noeud a partir du noeud source

	private String predecesseur; // definis le dernier noeud dans la sequence du chemin le plus court a partir
									// duquel on est arriver a ce noeud (important pour connaitre la sequence
									// qui defini le chemin le plus court)
	private ArrayList<Lien> voisins;

	public Noeud(String nom, Point coordonnees) {
		this.nom = nom;
		this.coordonnees = coordonnees;
		this.voisins = new ArrayList<Lien>();
		this.statu = false; // au debut le noeud est temporaire
		this.longueurChemin = Double.MAX_VALUE; // au debut la longueur du chemin vers un noeud est infini 
		this.predecesseur = "";
	}

	/**
	 * Permet d'obtenir le statu du noeud (temporaire ou permanent)
	 * @return the statu
	 */
	public boolean isStatu() {
		return statu;
	}

	/**
	 * @param statu
	 *            the statu to set
	 */
	public void setStatu(boolean statu) {
		this.statu = statu;
	}

	/**
	 * Permet d'obtenir la longueur du chemin pour arriver a ce noeud
	 * @return the longueurChemin
	 */
	public double getLongueurChemin() {
		return longueurChemin;
	}

	/**
	 * @param longueurChemin
	 *            the longueurChemin to set
	 */
	public void setLongueurChemin(double longueurChemin) {
		this.longueurChemin = longueurChemin;
	}

	/**
	 * Permet d'obtenir le noeud predescesseur du noeud courant
	 * @return the predecesseur
	 */
	public String getPredecesseur() {
		return predecesseur;
	}

	/**
	 * @param predecesseur
	 *            the predecesseur to set
	 */
	public void setPredecesseur(String predecesseur) {
		this.predecesseur = predecesseur;
	}

	/**
	 * @param nom
	 *            the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @param voisins
	 *            the voisins to set
	 */
	public void setVoisins(ArrayList<Lien> voisins) {
		this.voisins = voisins;
	}

	public String getNom() {
		return this.nom;
	}

	public Point getCoordonnees() {
		return this.coordonnees;
	}

	public void setCoordonnees(Point point) {
		this.coordonnees = point;
	}

	public Double getX() {
		return coordonnees.getX();
	}

	public Double getY() {
		return coordonnees.getY();
	}

	public ArrayList<Lien> getVoisinage() {
		return this.voisins;
	}

	public Lien getVoisin(int index) {
		return this.voisins.get(index);
	}
	public Lien getLien(Noeud voisin){
		for(Lien l:voisin.getVoisins()){
			if (l.contiens(voisin)){
				return l;
			}
		}
		return null;
	}
	public void addVoisin(Lien e) {
		this.voisins.add(e);
	}

	public void removeVoisin(Lien e) {
		this.voisins.remove(e.getIndex());
	}

	public ArrayList<Lien> getVoisins() {
		return this.voisins;
	}

	public String toString() {
		String str = "[nom: " + this.nom + ", coordonnees:{x: " + this.getX() + ", y: " + this.getY() + "}]"
				+ " Labels:{Longueur du chemin : " + this.getLongueurChemin() + ", Predecesseur : "
				+ this.getPredecesseur() + ", statu : "+ this.isStatu() + "]";
		return str;
	}
}
