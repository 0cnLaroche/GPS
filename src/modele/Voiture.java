package modele;

import java.awt.Point;
import java.util.ArrayList;

import vue.PanelUtilisateur;

public class Voiture {

	/**
	 * Represente la position de la voiture a un instant t
	 */
	private Point positionVoiture;

	/**
	 * Tableau des point representant le chemin de la voiture
	 */
	private ArrayList<Point> cheminVoiture;

	/**
	 * attribut contenant les points de depart et d'arriver temporaire
	 */
	private Point depart;

	private Point arriver;

	private double dx, dy, temps, frames, stepx, stepy;

	/**
	 * varialbes de l'equation
	 */
	private double y = 0, x; // variables de l'equation

	/**
	 * constructeur de la classe
	 */
	public Voiture() {
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
			//System.out.println(lien.toString());
			System.out.println(cheminCourt.get(i).getNom());
			System.out.println(cheminCourt.get(i-1).getNom());
			
			//temps = lien.getPoid(); Fonctionne pas pour certains segments pour une raison inconnu
			temps = Math.hypot(cheminCourt.get(i-1).getX()-cheminCourt.get(i).getX(), cheminCourt.get(i-1).getY()-cheminCourt.get(i).getY());

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

	/*	*//**
			 * Methode qui permet d'ecrire les coordonnes d'un point dans un fichier, code a enlever a la fin
			 * 
			 * @param coordonne
			 *//*
				 * public void ecrireFichier(Point coordonne) { File x = new
				 * File("Coordonees.txt"); FileWriter y; BufferedWriter z; PrintWriter fichier =
				 * null; try { y = new FileWriter(x, true); z = new BufferedWriter(y); fichier =
				 * new PrintWriter(z); fichier.print(" X : " + String.valueOf(coordonne.getX())
				 * + "  Y : " + String.valueOf(coordonne.getY()) + "\n"); } catch (IOException
				 * e) { e.printStackTrace(); } fichier.close(); } public void ecrireCSV(Point
				 * coordonne) { File x = new File("Coordonees.csv"); FileWriter y;
				 * BufferedWriter z; PrintWriter fichier = null; try { y = new FileWriter(x,
				 * true); z = new BufferedWriter(y); fichier = new PrintWriter(z);
				 * fichier.println(String.valueOf(coordonne.getX()) + "," +
				 * String.valueOf(coordonne.getY())); } catch (IOException e) {
				 * e.printStackTrace(); } fichier.close(); }
				 */
}