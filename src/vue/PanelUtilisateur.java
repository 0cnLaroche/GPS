package vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JPanel;

import controleur.actionEvenement;
import modele.PointGraphe;

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

	private PointGraphe pointGraphe = new PointGraphe();

	/**
	 * Methode accesseur
	 * 
	 * @return the coordonnnePoint
	 */
	public ArrayList<Point> getCoordonnnePoint() {
		return coordonnnePoint;
	}

	/**
	 * Methode modificateur
	 * 
	 * @param coordonnnePoint
	 *            the coordonnnePoint to set
	 */
	public void setCoordonnnePoint(ArrayList<Point> coordonnnePoint) {
		this.coordonnnePoint = coordonnnePoint;
	}

	public PanelUtilisateur() {
		super.setBackground(Color.WHITE);
		//this.addMouseListener(actionEvenement); //utiliser quand on veut dessiner sur le JPanel 
	}

	/**
	 * Methode qui permet de dessiner sur le Jpanel, elle se lance automatiquement a
	 * la creation de la fenetre. Elle se lance une seule fois. Pour la relancer, il
	 * faut utiliser la methode repaint(), cette derniere permet d'appeller la
	 * methode paintComponent()
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		/**
		 * lire les coordonnees des points du reseaux routier et generer le reseau
		 * routier
		 */
		pointGraphe.lireFichier(
				//TODO: Va devoir changer ça
				"C:\\Users\\NDUHURA\\Desktop\\Credo\\i\\UQO\\Cours\\hiver_2018\\Prog_2\\Devoir\\Devoir2\\CoordoneesEffectif.txt");
		
		for (int i = 0; i < pointGraphe.getCoordonnnePoint().size(); i++) {
			g.setColor(Color.RED);
			g.setFont(new Font("Serif", Font.BOLD, 19));
			g.fillOval((int) pointGraphe.getCoordonnnePoint().get(i).getX(),
					(int) pointGraphe.getCoordonnnePoint().get(i).getY(), 10, 10);
			g.drawString("A", (int)pointGraphe.getCoordonnnePoint().get(i).getX(),
					(int) pointGraphe.getCoordonnnePoint().get(i).getY());
		}
	}
}
