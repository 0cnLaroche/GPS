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
import java.util.List;

import modele.Evenement;
import modele.Graph;
import modele.Noeud;
import modele.Trajet;
import modele.Voiture;
import vue.FrmPanelSimulation;
import vue.FrmPanelUtilisateur;
import vue.PanelUtilisateur;

public class ActionEvenement implements ActionListener, MouseListener, WindowListener {

	private FrmPanelUtilisateur frmPanelUtilisateur;

	private PanelUtilisateur panelUtilisateur;

	private FrmPanelSimulation panelSimulation;
	
	private Evenement evenement;

	private Voiture voiture;

	private static Trajet trajet;
	
	private static ArrayList<Point>pointCheminVoiture;
	
	private static ArrayList<String> iconesDirection;

	// private Evenements Event;

	private Graph graphe;

	private static String pointDepart; // contient le nom du noeud de depart

	private static String pointArriver;// contient le nom du noeud d'arriver
	
	private static String indicationCongestion = "";

	/**
	 * liste qui contient les coordonnes des points (intersections des routes) du
	 * graphe (reseau routier)
	 */
	private List<Point> coordonnnePoint = new ArrayList<>();

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

	private boolean nbrClick = false;// sert a eviter l'effets d'un second appuis sur les bouttons

	/**
	 * constructeur sans parametres
	 */
	public ActionEvenement(Graph graphe) {
		super();
		this.voiture = new Voiture();
		this.graphe = graphe;
		// ActionEvenement.pointCheminVoiture = new ArrayList<Point>();
	}

	/**
	 * constructeur avec parametres
	 * 
	 * @param panelUtilisateur
	 */
	public ActionEvenement(PanelUtilisateur panelUtilisateur) {
		this.panelUtilisateur = panelUtilisateur;
	}

	/**
	 * Methodes accesseurs et modificateurs
	 */
	
	

	/**
	 * @return the pointCheminVoiture
	 */
	public Trajet getTrajet() {
		return graphe.getTrajet();
	}
	public static ArrayList<Point> getPointCheminVoiture() {
		return pointCheminVoiture;
	}

	public static void setPointCheminVoiture(ArrayList<Point> pointCheminVoiture) {
		ActionEvenement.pointCheminVoiture = pointCheminVoiture;
	}

	public Graph getGraphe(){
		return this.graphe;
	}
	
	/**
	 * @return the indicationCongestion
	 */
	public static String getIndicationCongestion() {
		return indicationCongestion;
	}

	/**
	 * @param indicationCongestion the indicationCongestion to set
	 */
	public static void setIndicationCongestion(String indicationCongestion) {
		ActionEvenement.indicationCongestion = indicationCongestion;
	}

	/**
	 * @return the iconesDirection
	 */
	public static ArrayList<String> getIconesDirection() {
		return iconesDirection;
	}

	/**
	 * @param iconesDirection the iconesDirection to set
	 */
	public static void setIconesDirection(ArrayList<String> iconesDirection) {
		ActionEvenement.iconesDirection = iconesDirection;
	}

	/**
	 * @param pointCheminVoiture
	 *            the pointCheminVoiture to set
	 */
	public static void setTrajet(Trajet trajet) {
		ActionEvenement.trajet = trajet;
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
		// voiture.ecrireFichier(debut); // ecriture des coordonnes du point dans le
		// fichier
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

	// @SuppressWarnings("unlikely-arg-type")
	@Override
	public void actionPerformed(ActionEvent e) {
		
		/**
		 * Recupere les routes de depart et d'arriver saisi et les afficher dans les Label correspondant
		 */
		Object src = e.getSource();
		
		if (src == FrmPanelSimulation.getRouteDepSaisi()) {
			pointDepart = FrmPanelSimulation.getRouteDepSaisi().getText();
			System.out.println("Depart : " + pointDepart);
			FrmPanelSimulation.getInfoRouteDepart().setText("Depart : " + pointDepart);
			
		} else if (src == FrmPanelSimulation.getRouteArrSaisi()) {
			pointArriver = FrmPanelSimulation.getRouteArrSaisi().getText();
			System.out.println("Destination : " + pointArriver);
			FrmPanelSimulation.getInfoRouteArriver().setText("Destination : " + pointArriver);
		}
		
			/**
			 * Recuperer la saisi du Jtextfield PremierNoeudRouteAccident pour generer l'evenement de 
			 * type accident et relancer le calcul du chemin le plus court
			 */
		else if(src == FrmPanelSimulation.getPremierNoeudRouteTraffic()) {
			System.out.println("La router accidenter est : " + FrmPanelSimulation.getPremierNoeudRouteTraffic().getText());
			indicationCongestion = "imgpointRouge";
			FrmPanelUtilisateur.getAvertissementEvenement().setText("Attention!!! ACCIDENT SUR : " + FrmPanelSimulation.getPremierNoeudRouteTraffic().getText());
			
		}
		
		/*
		 * Resuperer la saisi du Jtextfield PremierNoeudRouteTraffic pour generer l'evenement 
		 * de type traffic et relancer le calcul du chemin le plus court
		 */
		else if(src == FrmPanelSimulation.getPremierNoeudRouteTraffic()) {
			System.out.println("La router qui a le traffic est : " + FrmPanelSimulation.getPremierNoeudRouteTraffic().getText());
			indicationCongestion = "imgpointRouge";
			FrmPanelUtilisateur.getAvertissementEvenement().setText("Attention!!! TRAFFIC SUR : " + FrmPanelSimulation.getPremierNoeudRouteTraffic().getText());
		}

		String nomButtonClique = e.getActionCommand(); // on recupere le nom du button cliquer
		/*
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
			String nomRouteEvenement = FrmPanelSimulation.getNoeudAccident();// on recupere le nom de la route ou l'evennement sera généré
			System.out.println("Click sur Generer un accident sur Noeud : " + nomRouteEvenement);
			//
			Evenement evenement = new Evenement("Accident",Evenement.HIGH);
			graphe.addEvenement(voiture.getRouteCourante(), evenement);
			
			//On recalcule un nouveau chemin
			graphe.calculCheminCourt(voiture.getNoeudProchain(), graphe.getNoeud(pointArriver));//On continura le chemin courant 
																						//et recalcule à partir du prochain noeud
			System.out.println(graphe.getTrajet().getListeNoeuds().toString());
			Trajet trajet = graphe.getTrajet();
			
			ActionEvenement.pointCheminVoiture = voiture.deplacerPoint(trajet.getListeNoeuds());

		} else if (nomButtonClique.equals("Generer un traffic")) {
			System.out.println("Click sur Generer un traffic");
			/**
			 * generer de maniere aleatoire la route de l'evenement
			 */
			String nomRouteEvenement = panelSimulation.getPremierNoeudRouteTraffic().getText();// on
			// recupere le nom de la route ou
			// l'evennement sera generer
			Evenement accident = new Evenement(nomRouteEvenement, Evenement.MEDIUM);
			//graphe.addEvenement(nomRouteEvenement, accident);

		} else if (nomButtonClique.equals("Démarrer") && !nbrClick) {

			System.out.println("Click sur Demarrer");
			
			nbrClick = true;// on empeche le reaffichage du panel en cas d'un second appuis
			
			/**
			 * Instance de la class Voiture, rpresente la voiture qui va se deplacer
			 */
			voiture = new Voiture();
			
			/**
			 * Creer un Panel utilisateur a l'appuis sur demarrer
			 */
			//frmPanelUtilisateur = new FrmPanelUtilisateur(this);
			//frmPanelUtilisateur.setVisible(true);
			

			
			if (pointDepart != null && pointArriver != null) {
				
				/*
				 * Calculer le chemin le plus court entre le noeud de depart et le noeud
				 * d'arrive grace a la methode calculCheminCourt de la classe graphe qui utilise
				 * l'algorithme de Dijkstra. Cette methode stocke les noeuds correspondant au
				 * chemin le plus court dans l'attribut NoeudCheminPlusCourt (ArrayList) de sa
				 * classe
				 */
				graphe.calculCheminCourt(graphe.getNoeud(pointDepart), graphe.getNoeud(pointArriver));// calcul du chemin le
																									// plus court
				System.out.println(graphe.getTrajet().getListeNoeuds().toString());
				Trajet trajet = graphe.getTrajet();
				/*
				 * Stocker les points correspondant au chemin le plus court calculer dans la
				 * classe voiture dans l'ArrayList pointCheminVoiture
				 */
				
				ActionEvenement.pointCheminVoiture = voiture.deplacerPoint(trajet.getListeNoeuds());// deplacement
																										// sur le
																												// chemin le
																												// plus
																												// court
				
				ActionEvenement.iconesDirection = voiture.directionAPrendreDansCheminPlusCourt(trajet.getListeNoeuds());
				
			}

		} 
	}

}
