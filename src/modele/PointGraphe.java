package modele;

import java.awt.*;
import java.util.*;
import java.io.*;

import vue.PanelUtilisateur;

public class PointGraphe {
	
	/**
	 * attribut qui sert a ecrire dans un fichier
	 */
	private Formatter ecrire; 
	
	private PanelUtilisateur panelUtilisateur;
	
	/**
	 * Attribut qui permet de dessiner sur le panelUtilisateur, tout ce qu'on fait
	 * avec cette variable apparait sur le panelUtilisateur
	 */
	private Graphics g;
	
	/**
	 * contient les coordonnes des points et donc permettent de tracer les routes
	 * entre deux points quand on appuis sur la souris
	 */
	//private Point debut, fin = null;
	
	/**
	 * liste qui contient les coordonnes des points (intersections des routes) du
	 * graphe (reseau routier)
	 */
	private ArrayList<Point> coordonnnePoint = new ArrayList<>();
	
	int cpt = 0; // temporaire pour le test
	
	public PointGraphe() {super();}
	
	public PointGraphe(PanelUtilisateur panelUtilisateur, ArrayList<Point> coodroneePoint) {
		this.panelUtilisateur = panelUtilisateur;
		this.coordonnnePoint = coodroneePoint;
	}
	
	/**
	 * Methode qui permet d'ecrire les coordonnes d'un point dans un fichier
	 * @param coordonne
	 */
	public void ecrireFichier(Point coordonne) {
		File x = new File("Coordonees.txt");
		FileWriter y;
		BufferedWriter z;
		PrintWriter fichier = null;
		try {
			y = new FileWriter(x, true);
			z = new BufferedWriter(y);
			fichier = new PrintWriter(z);
			fichier.print(" X : " + String.valueOf(coordonne.getX()) + "  Y : " + String.valueOf(coordonne.getY()) + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
		fichier.close();
	}
	public void ecrireCSV(Point coordonne) {
		File x = new File("Coordonees.csv");
		FileWriter y;
		BufferedWriter z;
		PrintWriter fichier = null;
		try {
			y = new FileWriter(x, true);
			z = new BufferedWriter(y);
			fichier = new PrintWriter(z);
			fichier.println(String.valueOf(coordonne.getX()) + "," + String.valueOf(coordonne.getY()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		fichier.close();
	}
	
	/**
	 * Fonction qui permet de deplacer un point sur une droite (route) en utilisant
	 * l'equation de la droite correspodant a cette route
	 * 
	 * @param pointA
	 *            : coordonees du point de depart
	 * @param pointB
	 *            : coordonnees du point d'arriver
	 * 
	 */
	public void deplacerPoint(Point pointA, Point pointB) {
		double a, b; // pente et ordonne a l'origine de la droite
		double y, x; // variables de l'equation
		a = (pointB.getY() - pointA.getY()) / (pointB.getX() - pointA.getX());// calcule de la pente
		b = pointA.getY() - (a * pointA.getX()); // calcule de l'ordonnee a l'origine

		if (pointA.getX() < pointB.getX()) {
			x = coordonnnePoint.get(0).getX() + 1;// calcule de la nouvelle valeur de la coordonnee x sur la droite
		} else {
			x = coordonnnePoint.get(0).getX() - 1;// calcule de la nouvelle valeur de la coordonnee x sur la droite
		}
		y = (a * x) + b; // calcul de la nouvelle coordonne y sur la droite
		g.fillOval((int) x, (int) y, 15, 15);
	}
	
}
