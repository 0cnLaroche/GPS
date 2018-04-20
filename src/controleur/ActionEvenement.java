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
import java.util.InputMismatchException;
import java.util.List;

import com.sun.corba.se.impl.io.TypeMismatchException;

import modele.Evenement;
import modele.Graph;
import modele.Noeud;
import modele.Trajet;
import modele.Voiture;
import vue.FrmPanelSimulation;
import vue.FrmPanelUtilisateur;
import vue.PanelUtilisateur;

public class ActionEvenement implements ActionListener {

	private FrmPanelUtilisateur frmPanelUtilisateur;

	private PanelUtilisateur panelUtilisateur;

	private FrmPanelSimulation panelSimulation;

	private Evenement evenement;

	private Voiture voiture;

	private static Trajet trajet;

	private static ArrayList<Point> pointCheminVoiture;

	private static ArrayList<String> iconesDirection;
	
	private double tempParcours;

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
		//this.trajet = new Trajet();
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

	/**
	 * @return the tempParcours
	 */
	public double getTempParcours() {
		return tempParcours;
	}

	/**
	 * @param tempParcours the tempParcours to set
	 */
	public void setTempParcours(double tempParcours) {
		this.tempParcours = tempParcours;
	}

	public static ArrayList<Point> getPointCheminVoiture() {
		return pointCheminVoiture;
	}

	public static void setPointCheminVoiture(ArrayList<Point> pointCheminVoiture) {
		ActionEvenement.pointCheminVoiture = pointCheminVoiture;
	}

	public Graph getGraphe() {
		return this.graphe;
	}

	/**
	 * @return the indicationCongestion
	 */
	public static String getIndicationCongestion() {
		return indicationCongestion;
	}

	/**
	 * @param indicationCongestion
	 *            the indicationCongestion to set
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
	 * @param iconesDirection
	 *            the iconesDirection to set
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



	// @SuppressWarnings("unlikely-arg-type")
	@Override
	public void actionPerformed(ActionEvent e) {

		/**
		 * Recupere les routes de depart et d'arriver saisi et les afficher dans les
		 * Label correspondant
		 */
		Object src = e.getSource();

		if (src == FrmPanelSimulation.getRouteDepSaisi()) {

				pointDepart = FrmPanelSimulation.getRouteDepSaisi().getText();
				System.out.println("Depart : " + pointDepart);
				FrmPanelSimulation.getRouteDepart().setText("Depart : " + pointDepart);

		} else if (src == FrmPanelSimulation.getRouteArrSaisi()) {
				pointArriver = FrmPanelSimulation.getRouteArrSaisi().getText();
				System.out.println("Destination : " + pointArriver);
				FrmPanelSimulation.getInfoRouteArriver().setText("Destination : " + pointArriver);
		}

		/**
		 * Recuperer la saisi du Jtextfield PremierNoeudRouteAccident pour generer
		 * l'evenement de type accident et relancer le calcul du chemin le plus court
		 */
		else if (src == FrmPanelSimulation.getPremierNoeudRouteAccident()) {
			indicationCongestion = "imgpointRouge";
			FrmPanelUtilisateur.getAvertissementEvenement().setText(
					"Attention!!! ACCIDENT SUR : " + FrmPanelSimulation.getPremierNoeudRouteAccident().getText());

		}

		/*
		 * Resuperer la saisi du Jtextfield PremierNoeudRouteTraffic pour generer
		 * l'evenement de type traffic et relancer le calcul du chemin le plus court
		 */
		else if (src == FrmPanelSimulation.getPremierNoeudRouteTraffic()) {
			System.out.println(
					"La router qui a le traffic est : " + FrmPanelSimulation.getPremierNoeudRouteTraffic().getText());
			indicationCongestion = "imgpointRouge";
			FrmPanelUtilisateur.getAvertissementEvenement().setText(
					"Attention!!! TRAFFIC SUR : " + FrmPanelSimulation.getPremierNoeudRouteTraffic().getText());
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
			
			trajet = graphe.getTrajet();
			
			System.out.println("La taille de listeNoeud est : " + trajet.getListeNoeuds().size());
			
			String nomRouteEvenement = FrmPanelSimulation.getNoeudAccident();// on recupere le nom de la route ou
																				// l'evennement sera généré

			System.out.println("Click sur Generer un accident sur Noeud : " + nomRouteEvenement);
			
			Evenement evenement = new Evenement("Accident", Evenement.FULL);
			
			if (trajet.getListeNoeuds().contains(graphe.getNoeud(nomRouteEvenement))) {
				Noeud noeudSuivant;
				for (int i = 0; i < trajet.getListeNoeuds().size(); i++) {
					Noeud n = trajet.getListeNoeuds().get(i);
					if (n.equals(graphe.getNoeud(nomRouteEvenement))) {
						if (n.equals(trajet.getListeNoeuds().get(Math.abs(i - 1)))) {
							noeudSuivant = trajet.getListeNoeuds().get(Math.abs(i - 1));
						} else {
							noeudSuivant = trajet.getListeNoeuds().get(Math.abs(i + 1));
						}
						graphe.addEvenement(trajet.getLien(graphe.getNoeud(nomRouteEvenement).getNom().hashCode()
								+ noeudSuivant.getNom().hashCode()), evenement);
						break;
					}
				}

			}

			System.out.println(graphe.getEvenements().toString());

			// On recalcule un nouveau chemin
			System.out.println("Noeud prochain est : " + voiture.getNoeudProchain());
			
			graphe.calculCheminCourt(graphe.getNoeud(pointDepart), graphe.getNoeud(pointArriver));// On continura le
																									// chemin courant
			// et recalcule à partir du prochain noeud
			System.out.println(graphe.getTrajet().getListeNoeuds().toString());
			//trajet1 = graphe.getTrajet();

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
			// graphe.addEvenement(nomRouteEvenement, accident);

		} else if (nomButtonClique.equals("Démarrer")  && !nbrClick) {

			System.out.println("Click sur Demarrer");

			nbrClick = true;// Empeche le reaffichage du panel en cas d'un second appuis

			/**
			 * Instance de la class Voiture, rpresente la voiture qui va se deplacer
			 */
			voiture = new Voiture();

			/**
			 * Creer un Panel utilisateur a l'appuis sur demarrer
			 */
			// frmPanelUtilisateur = new FrmPanelUtilisateur(this);
			// frmPanelUtilisateur.setVisible(true);

			if (pointDepart != null && pointArriver != null) {

				/*
				 * Calculer le chemin le plus court entre le noeud de depart et le noeud
				 * d'arrive grace a la methode calculCheminCourt de la classe graphe qui utilise
				 * l'algorithme de Dijkstra. Cette methode stocke les noeuds correspondant au
				 * chemin le plus court dans l'attribut NoeudCheminPlusCourt (ArrayList) de sa
				 * classe
				 */
				graphe.calculCheminCourt(graphe.getNoeud(pointDepart), graphe.getNoeud(pointArriver));// calcul du
																										// chemin le
																										// plus court
				//System.out.println(graphe.getTrajet().getListeNoeuds().toString());
				
				trajet = graphe.getTrajet();
				System.out.println("La taille de listeNoeud est : " + trajet.getListeNoeuds().size());
				/*
				 * Stocker les points correspondant au chemin le plus court calculer dans la
				 * classe voiture dans l'ArrayList pointCheminVoiture
				 */

				ActionEvenement.pointCheminVoiture = voiture.deplacerPoint(trajet.getListeNoeuds());// deplacement
																									// sur le
																									// chemin le
																									// plus
																									// court
				
				tempParcours = voiture.dureeDuTrajet(trajet.getListeNoeuds());
				
				System.out.println("Le temps du parcours est : " + tempParcours);
				
				ActionEvenement.iconesDirection = voiture.directionAPrendreDansCheminPlusCourt(trajet.getListeNoeuds());
				
			}

		}
	}

}
