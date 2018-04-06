/**
 * code test
 */

package vue;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map.Entry;

import javax.swing.JPanel;
import javax.swing.Timer;

import controleur.ActionEvenement;
import modele.Graph;
import modele.Lien;
import modele.Noeud;
import outils.CSV;

public class PanelUtilisateur extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private Timer t = new Timer(50, this);
	private Noeud n1 = null, n2 = null;
	private Graph graphe;
	private Lien valeur;
	double y, x = 0;
	boolean check = false;
	double a, b; // pente et ordonne a l'origine de la droite
	double dx, dy, temps, frames, stepx, stepy, noeud1X, noeud1Y, noeud2X, noeud2Y;
	
	private static int delaisTimer;
	
	int i = 0; // indice du tableau pointCheminCourt

	/**
	 * declaration d'un attribut qui a le type de la classe qui contient les actions
	 * correspondant aux evenements
	 */
	// private actionEvenement actionEvenement = new actionEvenement(this);
	
	int cpt = 0; // temporaire pour le test

	public PanelUtilisateur(Graph graphe) {
		super.setBackground(Color.WHITE);
		graphe.setNoeuds(new CSV("SystemGuidageRoutier/res/Coordonnees.csv"));
		graphe.setLiens(new CSV("SystemGuidageRoutier/res/liens.csv"));
		this.graphe = graphe;
		t.start();
		delaisTimer = t.getDelay();
	}

	/**
	 * Methode accesseur
	 */
	
	/**
	 * @return the delaisTimer
	 */
	public static int getDelaisTimer() {
		return delaisTimer;
	}

	/**
	 * @param delaisTimer the delaisTimer to set
	 */
	public static void setDelaisTimer(int delaisTimer) {
		PanelUtilisateur.delaisTimer = delaisTimer;
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
		int x1, y1, x2, y2 = 0;
		/**
		 * Dessiner les noeud et les liens entre les noeuds sur le Panel
		 */
		for (Entry<Integer, Lien> entry : graphe.getLiens().entrySet()) {
			// Integer cle = entry.getKey();
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

				g.setColor(Color.BLACK);

				/**
				 * iterer sur la liste contenant les noeuds voisins au noeud courant et dessiner
				 * a chaque fois les liens vers ses voisins
				 */
				g.drawLine(x1 + 10, y1 + 10, x2 + 10, y2 + 10);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		// deux noeuds
		// n1 = graphe.getNoeud("q");
		// n2 = graphe.getNoeud("p");
		
		
		
/*		if (!check) {
			x = ActionEvenement.getPointCheminVoiture().get(i).x;
			y = ActionEvenement.getPointCheminVoiture().get(i).y;

			noeud1X = ActionEvenement.getPointCheminVoiture().get(i).getX();
			noeud1Y = ActionEvenement.getPointCheminVoiture().get(i).getY();

			noeud2X = ActionEvenement.getPointCheminVoiture().get(i + 1).getX();
			noeud2Y = ActionEvenement.getPointCheminVoiture().get(i + 1).getY();

			temps = valeur.getPoid();
			dx = noeud2X - noeud1X;
			dy = noeud2Y - noeud1Y;
			
			delaisTimer = t.getDelay(); 

			frames = (valeur.getPoid() * 100) / delaisTimer;
			stepx = dx / frames;
			stepy = dy / frames;
		}*/
		//g.fillOval((int) x, (int) y, 15, 15);
		System.out.println("Le delais du timer vaut : " + t.getDelay());
		g.fillOval(ActionEvenement.getPointCheminVoiture().get(i).x, ActionEvenement.getPointCheminVoiture().get(i).y, 15, 15);
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

	/*	if (x < (noeud1X + dx) && y < (noeud1Y + dy)) {
			x += stepx;
			y += stepy;
		}

		if (dx > 0) {
			if (x + stepx < noeud2X) {
				x += stepx;
				y += stepy;
			}
		} else if (dx < 0) {
			if (x + stepx > noeud2X) {
				x = +stepx;
				y = +stepy;
			}
		}
		if (i < ActionEvenement.getPointCheminVoiture().size() - 2 ) {
			i++;
		} else {
			check = true;
		}*/
		
		if(i < ActionEvenement.getPointCheminVoiture().size() - 1) {
			i++;
		}
		
		repaint();

	}
}
