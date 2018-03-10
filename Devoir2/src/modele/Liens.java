package modele;

import java.awt.Point;
import java.util.ArrayList;

public class Liens {
	
	/**
	 * List qui contient les coordonnes des points du graphe
	 */
	private ArrayList<Point> coordonesPoint = new ArrayList<>();
	
	/**
	 * List qui contient les poids des arcs du graphe
	 */
	private ArrayList<Double> poidArc = new ArrayList<Double>();
	
	/**
	 * List qui contient les noms (identifiants) des routes 
	 */
	private ArrayList<String> nomRoute = new ArrayList<String>();
	
	/**
	 *List qui va contenir les suites des point sur le graphe
	 */
	private ArrayList<String> nomPoint = new ArrayList<String>();
	
	/**
	 * Constructeur
	 * @param coordonesPoint
	 */
	public Liens(ArrayList<Point> coordonesPoint) {
		this.coordonesPoint = coordonesPoint;
	}
	
	
	
	/**
	 * @return the coordonesPoint
	 */
	public ArrayList<Point> getCoordonesPoint() {
		return coordonesPoint;
	}



	/**
	 * @param coordonesPoint the coordonesPoint to set
	 */
	public void setCoordonesPoint(ArrayList<Point> coordonesPoint) {
		this.coordonesPoint = coordonesPoint;
	}



	/**
	 * @return the poidArc
	 */
	public ArrayList<Double> getPoidArc() {
		return poidArc;
	}



	/**
	 * @param poidArc the poidArc to set
	 */
	public void setPoidArc(ArrayList<Double> poidArc) {
		this.poidArc = poidArc;
	}



	/**
	 * @return the nomRoute
	 */
	public ArrayList<String> getNomRoute() {
		return nomRoute;
	}



	/**
	 * @param nomRoute the nomRoute to set
	 */
	public void setNomRoute(ArrayList<String> nomRoute) {
		this.nomRoute = nomRoute;
	}



	/**
	 * @return the nomPoint
	 */
	public ArrayList<String> getNomPoint() {
		return nomPoint;
	}



	/**
	 * @param nomPoint the nomPoint to set
	 */
	public void setNomPoint(ArrayList<String> nomPoint) {
		this.nomPoint = nomPoint;
	}



	/**
	 * Methode qui calcule la distance entre deux points sur le graphe, donc definie le poid des arcs
	 * entre les points du graphe, et stocke ces poids dans une liste "poidsArc"
	 * @param tabPoint
	 */
	public void distance(ArrayList<Point> tabPoint) {
		double poid, xa, xb, ya, yb;
		for (int i = 0; i < tabPoint.size() - 1; i++) {
			xa = tabPoint.get(i).getX();
			xb = tabPoint.get(i + 1).getX();
			
			ya = tabPoint.get(i).getY();
			yb = tabPoint.get(i + 1).getY();
			
			poid = Math.sqrt(Math.pow(xb - xa, 2) + Math.pow(yb - ya, 2));
			
			this.poidArc.add(poid);
		} 
		
	}
	public void distance(Point a, Point b){
		double poid, xa, xb, ya, yb;
		//Voir la méthode distance de java.awt.geometry.point2d
		
		//poid = Math.sqrt(Math.pow(xb - xa, 2) + Math.pow(yb - ya, 2));
		
		/*
		 * 
		 */
	}
}
