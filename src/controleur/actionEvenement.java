package controleur;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import modele.Evenements;
import modele.PointGraphe;
import vue.FrmPanelSimulation;
import vue.FrmPanelUtilisateur;
import vue.PanelUtilisateur;

public class actionEvenement implements ActionListener, MouseListener, WindowListener {

	private FrmPanelUtilisateur frmPanelUtilisateur;

	private PanelUtilisateur panelUtilisateur;

	private PointGraphe pointGraphe = new PointGraphe();

	private FrmPanelSimulation panelSimulation;

	private Evenements Event;

	/**
	 * liste qui contient les coordonnes des points (intersections des routes) du
	 * graphe (reseau routier)
	 */
	private ArrayList<Point> coordonnnePoint = new ArrayList<>();

	/**
	 * contient les coordonnes des points et donc permettent de tracer les routes
	 * entre deux points quand on appuis sur la souris
	 */
	private Point debut, fin = null;

	/**
	 * Attribut qui permet de dessiner sur le panelUtilisateur, tout ce qu'on fait
	 * avec cette variable apparait sur le panelUtilisateur
	 */
	private Graphics g;

	int cpt = 0; // temporaire pour le test

	/**
	 * constructeur sans parametres
	 */
	public actionEvenement() {
		super();
	}

	/**
	 * constructeur avec parametres
	 * 
	 * @param panelUtilisateur
	 */
	public actionEvenement(PanelUtilisateur panelUtilisateur) {
		this.panelUtilisateur = panelUtilisateur;
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		cpt++;
		g = panelUtilisateur.getGraphics();
		// Graphics2D g2 = (Graphics2D) g;
		debut = e.getPoint();
		coordonnnePoint.add(debut);
		pointGraphe.ecrireFichier(debut); // ecriture des coordonnes du point dans le fichier
		g.setColor(Color.RED);
		g.fillOval(debut.x, debut.y, 10, 10);
		System.out.println("Element a la " + (cpt - 1) + " position");
		System.out
				.println("X : " + coordonnnePoint.get(cpt - 1).getX() + "  Y : " + coordonnnePoint.get(cpt - 1).getY());

		if (cpt == 2) {
			// pointGraphe.deplacerPoint(fin, debut);
		}
		fin = debut;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		/**
		 * si click sur un boutton { creer une instance de la classe Evenement et
		 * recuprer le contenue du getTextfield correspondant et le nom du button
		 * cliquer pour le passer au constructeur de cette instance }
		 */
		panelSimulation = new FrmPanelSimulation();
		String nomButtonClique = e.getActionCommand(); // on recupere le nom du button cliquer
		/**
		 * la classe Evennements recoit ce qui est saisi dans le Jtextfield
		 * "Localisation de l'accident" donc le nom de la route ou l'evennement doit
		 * etre generer et et le nom du button cliquer pour savoir le type d'evennement
		 * a generer (accident ou traffic)
		 */
		if (nomButtonClique.equals("G�n�rer un accident")) {
			System.out.println("Click sur G�n�rer un accident");
			String nomRouteEvenement = panelSimulation.getRouteAccident().getText();// on recupere le nom de la route ou
																					// l'evennement sera generer
			Event = new Evenements(nomRouteEvenement, nomButtonClique);

		} else if (nomButtonClique.equals("G�n�rer un traffic")) {
			System.out.println("Click sur G�n�rer un traffic");
			String nomRouteEvenement = panelSimulation.getRouteTraffic().getText();// on recupere le nom de la route ou
																					// l'evennement sera generer
			Event = new Evenements(nomRouteEvenement, nomButtonClique);

		} else if (nomButtonClique.equals("Demarrer")) {
			frmPanelUtilisateur = new FrmPanelUtilisateur();
			frmPanelUtilisateur.setVisible(true);
			System.out.println("Click sur Demarrer");
		}
	}
}
