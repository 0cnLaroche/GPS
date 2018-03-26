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
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import modele.Evenements;
import modele.Graph;
import modele.Lien;
import modele.Noeud;
import modele.PointGraphe;
import outils.CSV;
import vue.FrmPanelSimulation;
import vue.FrmPanelUtilisateur;
import vue.PanelUtilisateur;

public class actionEvenement implements ActionListener, MouseListener, WindowListener {

	private FrmPanelUtilisateur frmPanelUtilisateur;

	private PanelUtilisateur panelUtilisateur;

	private PointGraphe pointGraphe ;

	private FrmPanelSimulation panelSimulation;

	private Evenements Event;
	
	private Graph graphe;

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

	private boolean nbrClick = false;//sert a eviter l'effets d'un second appuis sur les bouttons

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
		if (nomButtonClique.equals("Generer un accident")) {
			System.out.println("Click sur Generer un accident");
			/**
			 * generer de maniere aleatoire la route de l'evenement
			 */
			//String nomRouteEvenement = panelSimulation.getRouteAccident().getText();// on recupere le nom de la route ou
																					// l'evennement sera generer
			//Event = new Evenements(nomRouteEvenement, nomButtonClique);

		} else if (nomButtonClique.equals("Generer un traffic")) {
			System.out.println("Click sur Generer un traffic");
			/**
			 * generer de maniere aleatoire la route de l'evenement
			 */
			//String nomRouteEvenement = panelSimulation.getRouteTraffic().getText();// on recupere le nom de la route ou
																					// l'evennement sera generer
			//Event = new Evenements(nomRouteEvenement, nomButtonClique);

		}else if (nomButtonClique.equals("Demarrer") && !nbrClick) {
			frmPanelUtilisateur = new FrmPanelUtilisateur();
			frmPanelUtilisateur.setVisible(true);
			System.out.println("Click sur Demarrer");
			nbrClick = true;//on empeche le reaffichage du panel en cas d'un second appuis
			
		}else{
			String saisi = e.getActionCommand();
			System.out.println(saisi);
		}
	}

	/**
	 * Fonction qui permet de recuperer les noeuds et les liens a partir d'u fichier
	 * CSV et dessiner un graphe representant le reseau routier a partir de ces
	 * donnees
	 */
/*	public void dessinerGraphe() {
		int x, y;
		Graph graphe = new Graph();
		graphe.setNoeuds(new CSV("SystemGuidageRoutier/res/Coordonnees.csv"));
		graphe.setLiens(new CSV("SystemGuidageRoutier/res/liens.csv"));

		Noeud un = graphe.getNoeud("f");

		for (Entry<String, Noeud> entry : graphe.getNoeuds().entrySet()) {
			String cle = entry.getKey();
			Noeud valeur = entry.getValue();
			System.out.println(cle);
			System.out.println(valeur);

			x = valeur.getCoordonnees().x;
			y = valeur.getCoordonnees().y;
		}
		
		Map<String, Noeud> trier = new TreeMap<>(graphe.getNoeuds());
		
		for (Entry<String, Noeud> entry : trier.entrySet()) {
			String cle = entry.getKey();
			Noeud valeur = entry.getValue();
			System.out.println(cle);
			System.out.println(valeur);

			x = valeur.getCoordonnees().x;
			y = valeur.getCoordonnees().y;
		}

		
		 * for (Lien l:un.getVoisinage()){ System.out.println(l.toString());
		 * System.out.println(l.getNoeudUn().toString());
		 * System.out.println(l.getNoeudDeux().toString()); }
		 
	}*/
}
