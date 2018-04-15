package modele;

import java.awt.Point;
import java.util.ArrayList;

import vue.PanelUtilisateur;

public class Voiture {

	private Point positionVoiture; //Represente la position de la voiture a un instant t
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
	 */
	public ArrayList<Point> deplacerPoint(ArrayList<Noeud> cheminCourt) {
		ArrayList<Point> cheminVoiture = new ArrayList<Point>();

		x = cheminCourt.get(cheminCourt.size() - 1).getCoordonnees().x;
		y = cheminCourt.get(cheminCourt.size() - 1).getCoordonnees().y;

		positionVoiture.setLocation( x, y);
		
		/**
		 * on parcours le tableau a l'envers car les noeuds du chemin le plus court sont
		 * stockee du dernier au premier
		 */
		for (int i = cheminCourt.size() - 1; i > 0; i--) {
			
			Noeud nDepart = cheminCourt.get(i);
			Noeud nArriver = cheminCourt.get(i-1);
			
			depart = cheminCourt.get(i).getCoordonnees();
			arriver = cheminCourt.get(i - 1).getCoordonnees();
			
			//retrouver le lien ici pour avoir le poid
			Lien lien = nDepart.getLien(nArriver);

			System.out.println(nDepart.getNom());

			System.out.println(nArriver.getNom());
			System.out.println(lien.toString());
			
			temps = lien.getPoid() * lien.getCongestion();
			//temps = Math.hypot(nArriver.getX()-nDepart.getX(), nArriver.getY()-nDepart.getY());
			System.out.println(lien.getCongestion());
			dx = arriver.getX() - depart.getX();
			dy = arriver.getY() - depart.getY();

			frames = (temps * 50) / PanelUtilisateur.getDelaisTimer(); //utilisation d'une variable static pour avoir le delay du timer de la 
																		// classe PanelUtilisateur
			stepx = dx / frames;
			stepy = dy / frames;

			while (positionVoiture.x < arriver.x || positionVoiture.x > arriver.x) {
				
				cheminVoiture.add(positionVoiture.getLocation());
				
			/*	System.out.println("La position de la voiture est : " + positionVoiture.x);
				System.out.println("Le prochain point d'arriver est : " + arriver.x);
				System.out.println("le poid vaut : " + temps);
				System.out.println("Le delais du timer vaut : " + PanelUtilisateur.getDelaisTimer());*/
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
				}//-------------------fin code Sam-----------------------------
				
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
			//tempParcourt += noeud.get
		}
		return tempParcourt;
		
	}
	
	/**
	 * Methode qui permet de definir les directions a prendre a partir des noeud du
	 * chemin le plus court
	 */
	public ArrayList<String> directionAPrendreDansCheminPlusCourt(ArrayList<Noeud> cheminCourt) {
		ArrayList<String> nomIconDirectionAPrendre = new ArrayList<>();

		for (int i = cheminCourt.size() - 1; i > 0; i--) {
			if (cheminCourt.get(i).getX() < cheminCourt.get(0).getX()) {
				/**
				 * On compare les positions des noeuds appartenant au chemin le plus court
				 * et dependament du resultat on stocke le nom de l'icone de direction correspondante 
				 * 
				 * Ici le sens de depalcement est de la gauche de l'ecran vers la droite
				 */
				if (cheminCourt.get(i).getY() < cheminCourt.get(i - 1).getY()
						|| cheminCourt.get(i).getX() > cheminCourt.get(i - 1).getX()) {
					/**
					 * La voiture se deplace dans le sens inverse a celui des tests de comparaison
					 * des noeuds
					 */
					nomIconDirectionAPrendre.add("tournerDroite");

				} else if (cheminCourt.get(i).getY() > cheminCourt.get(i - 1).getY()
						|| cheminCourt.get(i).getX() < cheminCourt.get(i - 1).getX()) {

					nomIconDirectionAPrendre.add("tournerGauche");
				} else {

					nomIconDirectionAPrendre.add("toutDroit");
				}
			}else {
				/**
				 * On fait la meme chose mais dans l'autre sens (de la droite de l'ecran vers la gauche)
				 */
				if (cheminCourt.get(i).getY() > cheminCourt.get(i - 1).getY()
						|| cheminCourt.get(i).getX() < cheminCourt.get(i - 1).getX()) {
					/**
					 * La voiture se deplace dans le sens inverse a celui des tests de comparaison
					 * des noeuds
					 */
					nomIconDirectionAPrendre.add("tournerDroite");

				} else if (cheminCourt.get(i).getY() < cheminCourt.get(i - 1).getY()
						|| cheminCourt.get(i).getX() > cheminCourt.get(i - 1).getX()) {

					nomIconDirectionAPrendre.add("tournerGauche");
				} else {

					nomIconDirectionAPrendre.add("toutDroit");
				}
			}
		}
		return nomIconDirectionAPrendre;
	}
}