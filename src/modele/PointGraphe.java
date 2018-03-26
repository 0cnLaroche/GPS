package modele;

import java.awt.*;
import java.util.*;
import java.util.Map.Entry;
import java.io.*;

import vue.*;

import vue.FrmPanelUtilisateur;
import vue.PanelUtilisateur;

public class PointGraphe { 
	
	double y = 0, x = 0; // variables de l'equation
	
	private PanelUtilisateur panelUtilisateur;
	
	private FrmPanelUtilisateur frmPanelUtilisateur;
	
	private Graph graphe;
	
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
	private Point coordonnnePointAjour;
	
	int cpt = 0; // temporaire pour le test
	
	

	/**
	 * @return the coordonnnePointAjour
	 */
	public Point getCoordonnnePointAjour() {
		return coordonnnePointAjour;
	}

	/**
	 * @param coordonnnePointAjour the coordonnnePointAjour to set
	 */
	public void setCoordonnnePointAjour(Point coordonnnePointAjour) {
		this.coordonnnePointAjour = coordonnnePointAjour;
	}

	/**
	 * constructeur sans parametres
	 */
	public PointGraphe() {super();}
	
	/**
	 * constructeur avec parametre
	 * @param panelUtilisateur
	 * @param coodroneePoint
	 */
	public PointGraphe(PanelUtilisateur panelUtilisateur) {
		this.panelUtilisateur = panelUtilisateur;
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
	 * Fonction qui lit dans un fichier les coordonnees d'un point du graphe
	 */
	/*public void lireFichier(String nomFichier) {
		String a, b, c, d;
		int x = 0, y = 0, i = 0;
		Scanner lecture = null;
		try {
			lecture = new Scanner(new File(nomFichier));
		       while(lecture.hasNext()){
	                a = lecture.next();
	                c = lecture.next();
	                x = (int) Double.parseDouble(lecture.next());
	                b = lecture.next();
	                d = lecture.next();
	                y = (int) Double.parseDouble(lecture.next());
	                pointGraphe = new Point(x,y); 
	                coordonnnePoint.add(pointGraphe);
	                System.out.println("X : " + coordonnnePoint.get(cpt).getX() + "  Y : " + coordonnnePoint.get(cpt).getY() );
	                cpt++;
		       }
		       lecture.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}*/
	
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
		
		a = (pointB.getY() - pointA.getY()) / (pointB.getX() - pointA.getX());// calcule de la pente
		b = pointA.getY() - (a * pointA.getX()); // calcule de l'ordonnee a l'origine
		
		while((int)x != pointB.x) {
			//System.out.println("coordonnee X A jour : " + (int)x + "coordonnee Y A jour : " + (int)y);
			//System.out.println("coordonnee X du point B : " + pointB.x + "coordonnee Y point B : " + pointB.y);
			
			if (pointA.getX() < pointB.getX()) {
				x = pointA.getX() + 30;// calcule de la nouvelle valeur de la coordonnee x sur la droite
			} else {
				x = pointA.getX() - 30;// calcule de la nouvelle valeur de la coordonnee x sur la droite
			}
			y = (a * x) + b; // calcul de la nouvelle coordonne y sur la droite
			coordonnnePointAjour = new Point((int)x,(int) y);
		}
	}
	
}



/**
 * code test
 *//*

package vue;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import javax.swing.JPanel;
import javax.swing.Timer;

import controleur.actionEvenement;
import modele.*;
import outils.CSV;

public class PanelUtilisateur extends JPanel implements ActionListener {

	*//**
	 * 
	 *//*
	private Timer t = new Timer(5000, this);
	private Lien test = null;
	private Graph graphe;
	private Lien valeur;
	double y, x = 0;
	boolean check = false;

	*//**
	 * declaration d'un attribut qui a le type de la classe qui contient les actions
	 * correspondant aux evenements
	 *//*
	private actionEvenement actionEvenement = new actionEvenement(this);

	int cpt = 0; // temporaire pour le test

	public PanelUtilisateur(Graph graphe) {
		super.setBackground(Color.WHITE);
		graphe.setNoeuds(new CSV("SystemGuidageRoutier/res/Coordonnees.csv"));
		graphe.setLiens(new CSV("SystemGuidageRoutier/res/liens.csv"));
		this.graphe = graphe;
		t.start();
	}

	*//**
	 * Methode qui permet de dessiner sur le Jpanel, elle se lance automatiquement a
	 * la creation de la fenetre. Elle se lance une seule fois. Pour la relancer, il
	 * faut utiliser la methode repaint(), cette derniere permet d'appeller la
	 * methode paintComponent()
	 *//*
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		int x1, y1, x2, y2, i = 0;
		// Graph graphe = new Graph();
		// graphe.setNoeuds(new CSV("SystemGuidageRoutier/res/Coordonnees.csv"));
		// graphe.setLiens(new CSV("SystemGuidageRoutier/res/liens.csv"));

		// Noeud un = graphe.getNoeud("f");

		*//**
		 * Dessiner les noeud et les liens entre les noeuds sur le Panel
		 *//*
		for (Entry<Integer, Lien> entry : graphe.getLiens().entrySet()) {
			Integer cle = entry.getKey();
			valeur = entry.getValue();

			x1 = valeur.getNoeudUn().getCoordonnees().x;
			y1 = valeur.getNoeudUn().getCoordonnees().y;

			x2 = valeur.getNoeudDeux().getCoordonnees().x;
			y2 = valeur.getNoeudDeux().getCoordonnees().y;
			g.setColor(Color.RED);

			g.fillOval(x1, y1, 15, 15);
			g.drawString(valeur.getNoeudUn().getNom(), x1, y1);
			g.fillOval(x2, y2, 15, 15);
			g.drawString(valeur.getNoeudDeux().getNom(), x2, y2);

			try {
				// Graphics2D g2 = (Graphics2D) g;
				// g2.setStroke(new BasicStroke(1));
				g.setColor(Color.BLACK);

				*//**
				 * iterer sur la liste contenant les noeuds voisins au noeud courant et dessiner
				 * a chaque fois les liens vers ses voisins
				 *//*
				g.drawLine(x1 + 10, y1 + 10, x2 + 10, y2 + 10);

				if (!check) {
					x = valeur.getNoeudUn().getX(); // variables de l'equation
					check = true;
				}

				double a, b; // pente et ordonne a l'origine de la droite

				a = (valeur.getNoeudDeux().getY() - valeur.getNoeudUn().getY())
						/ (valeur.getNoeudDeux().getX() - valeur.getNoeudUn().getX());// calcule de la pente
				b = valeur.getNoeudUn().getY() - (a * valeur.getNoeudUn().getX()); // calcule de l'ordonnee a l'origine

				System.out.println("Xdep : " + x + "  Xdest : " + valeur.getNoeudDeux().getX());
				y = (a * x) + b; // calcul de la nouvelle coordonne y sur la droite
				g.fillOval((int) x, (int) y, 15, 15);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// test = valeur;
			// break;
		}

		check = false;
	}

	*//**
	 * gerer le timer pour le deplacement du point sur le graphe
	 * 
	 * @param e
	 *//*
	@Override
	public void actionPerformed(ActionEvent e) {
		*//**
		 * code pour test
		 *//*
		if (valeur.getNoeudUn().getX() < valeur.getNoeudDeux().getX())
			x += 5;// calcule de la nouvelle valeur de la coordonnee x sur la droite
		else if (valeur.getNoeudUn().getX() > valeur.getNoeudDeux().getX())
			x -= 5;
		repaint();
	}
}*/
