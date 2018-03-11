package vue;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JPanel;

import controleur.actionEvenement;


public class PanelUtilisateur extends JPanel {
	
	/**
	 * declaration d'un attribut qui a le type de la classe qui contient les actions 
	 * correspondant aux evenements
	 */
	private actionEvenement actionEvenement = new actionEvenement(this);

	/**
	 * Attribut qui permet de dessiner sur le panelUtilisateur, tout ce qu'on fait
	 * avec cette variable apparait sur le panelUtilisateur
	 */
	private Graphics g;

	/**
	 * contient les coordonnes des points et donc permettent de tracer les routes
	 * entre deux points quand on appuis sur la souris
	 */
	private Point debut, fin = null;

	/**
	 * permet de garde l'information de la premiere appuis sur la souri en tracant
	 * les routes
	 */
	private boolean appui = false;

	/**
	 * liste qui contient les coordonnes des points (intersections des routes) du
	 * graphe (reseau routier)
	 */
	private ArrayList<Point> coordonnnePoint = new ArrayList<>();
	
	int cpt = 0; // temporaire pour le test

	/**
	 * Methode accesseur
	 * @return the coordonnnePoint
	 */
	public ArrayList<Point> getCoordonnnePoint() {
		return coordonnnePoint;
	}

	/**
	 * Methode modificateur
	 * @param coordonnnePoint the coordonnnePoint to set
	 */
	public void setCoordonnnePoint(ArrayList<Point> coordonnnePoint) {
		this.coordonnnePoint = coordonnnePoint;
	}	

	public PanelUtilisateur() {
		super.setBackground(Color.WHITE);
		this.addMouseListener(actionEvenement);
	}

	/**
	 * Methode qui permet de dessiner sur le Jpanel, elle se lance automatiquement a
	 * la creation de la fenetre. Elle se lance une seule fois. Pour la relancer, il
	 * faut utiliser la methode repaint(), cette derniere permet d'appeller la
	 * methode paintComponent()
	 */
	/*public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}*/	
}
