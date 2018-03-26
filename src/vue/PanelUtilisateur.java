/**
 * code test
 */

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

	/**
	 * 
	 */
	private Timer t = new Timer(50, this);
	private Noeud test = null, test2 = null;
	private Graph graphe;
	private Lien valeur;
	double y, x = 0;
	boolean check = false;
	double a, b; // pente et ordonne a l'origine de la droite

	/**
	 * declaration d'un attribut qui a le type de la classe qui contient les actions
	 * correspondant aux evenements
	 */
	private actionEvenement actionEvenement = new actionEvenement(this);

	int cpt = 0; // temporaire pour le test

	public PanelUtilisateur(Graph graphe) {
		super.setBackground(Color.WHITE);
		graphe.setNoeuds(new CSV("SystemGuidageRoutier/res/Coordonnees.csv"));
		graphe.setLiens(new CSV("SystemGuidageRoutier/res/liens.csv"));
		this.graphe = graphe;
		t.start();
	}

	/**
	 * Methode qui permet de dessiner sur le Jpanel, elle se lance automatiquement a
	 * la creation de la fenetre. Elle se lance une seule fois. Pour la relancer, il
	 * faut utiliser la methode repaint(), cette derniere permet d'appeller la
	 * methode paintComponent()
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		int x1, y1, x2, y2, i = 0;
		// Graph graphe = new Graph();
		// graphe.setNoeuds(new CSV("SystemGuidageRoutier/res/Coordonnees.csv"));
		// graphe.setLiens(new CSV("SystemGuidageRoutier/res/liens.csv"));

		// Noeud un = graphe.getNoeud("f");

		/**
		 * Dessiner les noeud et les liens entre les noeuds sur le Panel
		 */
		for (Entry<Integer, Lien> entry : graphe.getLiens().entrySet()) {
			//Integer cle = entry.getKey();
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

				/**
				 * iterer sur la liste contenant les noeuds voisins au noeud courant et dessiner
				 * a chaque fois les liens vers ses voisins
				 */
				g.drawLine(x1 + 10, y1 + 10, x2 + 10, y2 + 10);

				/*
				 * if (!check) { x = valeur.getNoeudUn().getX(); // variables de l'equation
				 * check = true; }
				 */

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// test = valeur;
			// break;
		}
		
		test = valeur.getNoeudUn();
		test2 = valeur.getNoeudDeux();

		if (!check) {
			x = test.getCoordonnees().x;
			y = test.getCoordonnees().y;
			check = true;
		}
		//System.out.println(test);
		//System.out.println(test2);
		
		System.out.println("X vaut : " + x + "  Y vaut : " + y + "\n" + " Xdest vaut : " + test2.getCoordonnees().x);
		a = (test2.getY() - test.getY()) / (test2.getX() - test.getX());// calcule de la pente
		b = test.getY() - (a * test.getX()); // calcule de l'ordonnee a l'origine

		// System.out.println("Xdep : " + x + " Xdest : " + test2.getX());
		y = (a * x) + b; // calcul de la nouvelle coordonne y sur la droite
		g.fillOval((int) x, (int) y, 15, 15);
		// check = false;
	}

	/**
	 * gerer le timer pour le deplacement du point sur le graphe
	 * 
	 * @param e
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		/**
		 * code pour test
		 */
		if (test.getX() < test2.getX() && x != test2.getCoordonnees().x)
			x += 1; //calcule de la nouvelle valeur de la coordonnee x sur la droite
		else if (test.getX() > test2.getX() && x != test2.getCoordonnees().x)
			x -= 1; //calcule de la nouvelle valeur de la coordonnee x sur la droite
		repaint();
	}
}














/*package vue;

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

public class PanelUtilisateur extends JPanel implements ActionListener{
	
	*//**
		* 
		*/
/*
private Timer t = new Timer(1000, this);
private Lien test = null;
private Graph graphe;
private Lien valeur;
double y, x =0;
boolean check = false;

*//**
	* declaration d'un attribut qui a le type de la classe qui contient les actions
	* correspondant aux evenements
	*/
/*
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
	*/
/*
public void paintComponent(Graphics g) {
super.paintComponent(g);
Graphics2D g2 = (Graphics2D) g;
int x1, y1, x2, y2, i = 0;
//Graph graphe = new Graph();
//graphe.setNoeuds(new CSV("SystemGuidageRoutier/res/Coordonnees.csv"));
//graphe.setLiens(new CSV("SystemGuidageRoutier/res/liens.csv"));		

//Noeud un = graphe.getNoeud("f");

*//**
	* Dessiner les noeud et les liens entre les noeuds sur le Panel
	*/
/*
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
	//Graphics2D g2 = (Graphics2D) g;
	//g2.setStroke(new BasicStroke(1));
	g.setColor(Color.BLACK);
	
	*//**
		* iterer sur la liste contenant les noeuds voisins au noeud courant et dessiner a chaque 
		* fois les liens vers ses voisins
		*/
/*
g.drawLine(x1 + 10, y1 + 10, x2 + 10, y2 + 10);
} catch (Exception e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
test = valeur;
break;
}
if(!check) {
x = test.getNoeudUn().getX(); // variables de l'equation
check = true;
}

double a, b; // pente et ordonne a l'origine de la droite

a = (test.getNoeudDeux().getY() - test.getNoeudUn().getY()) / (test.getNoeudDeux().getX() - test.getNoeudUn().getX());// calcule de la pente
b = test.getNoeudUn().getY() - (a * test.getNoeudUn().getX()); // calcule de l'ordonnee a l'origine

System.out.println("Xdep : " + x + "  Xdest : " + test.getNoeudDeux().getX());
y = (a * x) + b; // calcul de la nouvelle coordonne y sur la droite
g.fillOval((int)x, (int)y, 15, 15);
}

*//**
	* gerer le timer pour le deplacement du point sur le graphe
	* @param e
	*/
/*
@Override
public void actionPerformed(ActionEvent e) {
*//**
	* code pour test 
	*//*
		if(test.getNoeudUn().getX() < test.getNoeudDeux().getX())
		x += 5;// calcule de la nouvelle valeur de la coordonnee x sur la droite
		else if(test.getNoeudUn().getX() > test.getNoeudDeux().getX())
		x -= 5;
		repaint();
		}
		}*/

