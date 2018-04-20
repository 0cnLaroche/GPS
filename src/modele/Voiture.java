package modele;

import java.awt.Point;
import java.util.ArrayList;

import vue.PanelUtilisateur;

public class Voiture {

	private Point positionVoiture; //Represente la position de la voiture a un instant t
	private Lien route; //Represente le lien emprunté à l'instant t
	private Noeud nProchain,nPrecedant;
	private ArrayList<Point> cheminVoiture; //Tableau des point representant le chemin de la voiture
	private Point depart, arriver;//attribut contenant les points de depart et d'arriver temporaire
	private double dx, dy, temps, frames, stepx, stepy; //varialbes de l'equation
	private double y = 0, x; // variables de l'equation

	public Voiture() { //constructeur de la classe
		super();
		this.positionVoiture = new Point(0, 0);
		this.depart = new Point(0, 0);
		this.arriver = new Point(0, 0);
		this.cheminVoiture = new ArrayList<Point>();
	}
	/**Pour obtenir l'objet lien représentant la route présentement emprunté
	 * @author Samuel
	 * @return		Lien présentement emprunter dans le trajet
	 */
	public Lien getRouteCourante(){
		return this.route;
	}
	/** @author Samuel
	 * @return		Le dernier noeud emprunté. Celui de départ sinon
	 */
	public Noeud getNoeudPrecedant(){
		return this.nPrecedant;
	}
	/**@author Samuel
	 * @return 		Le prochain noeud sur le trajet. Destination finale sinon.
	 */
	public Noeud getNoeudProchain(){
		return this.nProchain;
	}

	/**
	 * @return the cheminVoiture
	 */
	public ArrayList<Point> getCheminVoiture() {
		return cheminVoiture;
	}

	/**
	 * @param cheminVoiture
	 *            the cheminVoiture to set
	 */
	public void setCheminVoiture(ArrayList<Point> cheminVoiture) {
		this.cheminVoiture = cheminVoiture;
	}

	/**
	 * @return the positionVoiture
	 */
	public Point getPositionVoiture() {
		return positionVoiture;
	}

	/**
	 * @param positionVoiture
	 *            the positionVoiture to set
	 */
	public void setPositionVoiture(Point positionVoiture) {
		this.positionVoiture = positionVoiture;
	}

	/**
	 * @return the depart
	 */
	public Point getDepart() {
		return depart;
	}

	/**
	 * @param depart
	 *            the depart to set
	 */
	public void setDepart(Point depart) {
		this.depart = depart;
	}

	/**
	 * @return the arriver
	 */
	public Point getArriver() {
		return arriver;
	}

	/**
	 * @param arriver
	 *            the arriver to set
	 */
	public void setArriver(Point arriver) {
		this.arriver = arriver;
	}

	/**
	 * Fonction qui permet de deplacer la voiture sur le chemin le plus court
	 * 
	 * @param cheminCourt
	 *            Tableau contenant les noeuds du chemin le plus court
	 * @return Tableau contenant les points representant le parcour de la voiture sur le chemin le plus court
	 */
	public ArrayList<Point> deplacerPoint(ArrayList<Noeud> cheminCourt) {
		ArrayList<Point> cheminVoiture = new ArrayList<Point>();
		
		x = cheminCourt.get(0).getCoordonnees().x;
		y = cheminCourt.get(0).getCoordonnees().y;
		
		positionVoiture.setLocation( x, y);
		cheminVoiture.add(positionVoiture.getLocation());
		/**
		 * Parcourir le tableau contenant les noeud du chemin le plus court et 
		 * deplacer la voiture sur ce chemin 
		 */
		for (int i = 0; i < cheminCourt.size()-1; i++) {

			x = cheminCourt.get(i).getCoordonnees().x;
			y = cheminCourt.get(i).getCoordonnees().y;

			//positionVoiture.setLocation( x, y);
			//cheminVoiture.add(positionVoiture.getLocation());
			
			Noeud nDepart = cheminCourt.get(i);
			Noeud nArriver = cheminCourt.get(i+1);
			
			nProchain = nArriver;
			nPrecedant = nDepart;
			
			depart = cheminCourt.get(i).getCoordonnees();
			arriver = cheminCourt.get(i + 1).getCoordonnees();
			
			//retrouver le lien ici pour avoir le poid
			Lien lien = nDepart.getLien(nArriver);
			this.route = lien; //lien à l'instant t

/*			System.out.println(nDepart.getNom());
			System.out.println(nArriver.getNom());
			System.out.println(lien.toString());*/
			
			temps = lien.getPoid() * lien.getCongestion();
			
			dx = arriver.getX() - depart.getX();
			dy = arriver.getY() - depart.getY();

			frames = (temps * 50) / PanelUtilisateur.getDelaisTimer(); 
			stepx = dx / frames;
			stepy = dy / frames;
			
			System.out.println("facteur congestion : " + lien.getCongestion() + ", dx: " + stepx + ", dy: "+ stepy);
			

			while (positionVoiture.x < arriver.x || positionVoiture.x > arriver.x) {
				
				cheminVoiture.add(positionVoiture.getLocation());

				/**
				 * code Sam
				 */
				//TODO: Réinitialiser au prochain point du trajet et non le point actuel pour éliminer les déviations
				positionVoiture.setLocation(x, y);// point representant la position de la voiture a l'instant t
				
				if (x < (depart.getX() + dx) && y < (depart.getY() + dy)) {
					x += stepx;
					y += stepy;
				}

				if (dx > 0) {
					if (x + stepx < arriver.getX()) {
						x += stepx;
						y += stepy;
					}
				} else if (dx < 0) {
					if (x + stepx > arriver.getX()) {
						x += stepx;
						y += stepy;
					}
				}
				
				//System.out.println("La position de la voiture est : " + positionVoiture.toString());//a enlever a la fin

				positionVoiture.setLocation(x, y);//point representant la position
				}
			//positionVoiture.setLocation(cheminCourt.get(i+1).getCoordonnees());//àTester

		}
		
		cheminVoiture.add(positionVoiture.getLocation());
		return cheminVoiture;
	}
	
	/**
	 * Methode qui calcule la duree du trajet
	 */
	public double dureeDuTrajet(ArrayList<Noeud> cheminCourt) {
		double tempParcourt = 0;
		for(Noeud noeud : cheminCourt) {
			tempParcourt += noeud.getLongueurChemin();
		}
		return tempParcourt;
		
	}
	
	/**
	 * Methode qui permet de definir les directions a prendre a partir des noeud du
	 * chemin le plus court
	 * @param cheminCourt 
	 * 					Tableaux contenant les noeuds du chemin le plus court 
	 * @return Tableau contenant les noms des directions a prendre par rapport au
	 * 			chemin le plus court
	 */
	public ArrayList<String> directionAPrendreDansCheminPlusCourt(ArrayList<Noeud> cheminCourt) {
		ArrayList<String> nomIconDirectionAPrendre = new ArrayList<>();

		for (int i = 0; i < cheminCourt.size()-1; i++) {
			if (cheminCourt.get(i).getX() < cheminCourt.get(i+1).getX()) {
				/**
				 * On compare les positions des noeuds appartenant au chemin le plus court
				 * et dependament du resultat on stocke le nom de l'icone de direction correspondante 
				 * 
				 * Ici le sens de depalcement est de la gauche de l'ecran vers la droite
				 */
				if (cheminCourt.get(i).getY() < cheminCourt.get(i + 1).getY()
						|| cheminCourt.get(i).getX() > cheminCourt.get(i + 1).getX()) {
					/**
					 * La voiture se deplace dans le sens inverse a celui des tests de comparaison
					 * des noeuds
					 */
					nomIconDirectionAPrendre.add("tournerDroite");

				} else if (cheminCourt.get(i).getY() > cheminCourt.get(i + 1).getY()
						|| cheminCourt.get(i).getX() < cheminCourt.get(i + 1).getX()) {

					nomIconDirectionAPrendre.add("tournerGauche");
				} else {

					nomIconDirectionAPrendre.add("toutDroit");
				}
			}else {
				/**
				 * On fait la meme chose mais dans l'autre sens (de la droite de l'ecran vers la gauche)
				 */
				if (cheminCourt.get(i).getY() > cheminCourt.get(i + 1).getY()
						|| cheminCourt.get(i).getX() < cheminCourt.get(i + 1).getX()) {
					/**
					 * La voiture se deplace dans le sens inverse a celui des tests de comparaison
					 * des noeuds
					 */
					nomIconDirectionAPrendre.add("tournerDroite");

				} else if (cheminCourt.get(i).getY() < cheminCourt.get(i + 1).getY()
						|| cheminCourt.get(i).getX() > cheminCourt.get(i + 1).getX()) {

					nomIconDirectionAPrendre.add("tournerGauche");
				} else {

					nomIconDirectionAPrendre.add("toutDroit");
				}
			}
		}
		return nomIconDirectionAPrendre;
	}
}