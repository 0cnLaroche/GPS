/**
 * code test
 */

package vue;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map.Entry;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import controleur.ActionEvenement;
import modele.Graph;
import modele.Lien;

public class PanelUtilisateur extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private FrmPanelUtilisateur frmPanelUtilisateur;
	
	
	private Timer t = new Timer(25, this);
	private Graph graphe;
	private Lien valeur;
	double y, x = 0;
	boolean check = false;
	double a, b; // pente et ordonne a l'origine de la droite
	double dx, dy, temps, frames, stepx, stepy, noeud1X, noeud1Y, noeud2X, noeud2Y;

	private static int delaisTimer;

	int i = 0; // indice du tableau pointCheminCourt

	private BufferedImage image;

	/**
	 * declaration d'un attribut qui a le type de la classe qui contient les actions
	 * correspondant aux evenements
	 */
	// private actionEvenement actionEvenement = new actionEvenement(this);

	int cpt = 0; // temporaire pour le test

	public PanelUtilisateur(Graph graphe) {
		super.setBackground(Color.WHITE);
		this.graphe = graphe;
		t.start();
		delaisTimer = t.getDelay();

		try {
			this.image = ImageIO.read(getClass().getResourceAsStream("/pointeur.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	 * @param delaisTimer
	 *            the delaisTimer to set
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
		
		//String imageName = "iamge/toutDroite.png";
/*		ImageIcon newIcon = new ImageIcon("image/toutDroite.png");s
		newIcon.getImage().flush();
		FrmPanelUtilisateur.getDirectionAPrendre().setIcon(newIcon);*/
		
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
		g.setColor(Color.GREEN);
		g2.setStroke(new BasicStroke(5));
		
		/**
		 * Desinner le trajet
		 */
		for (int j = 0; j < ActionEvenement.getPointCheminVoiture().size() - 1; j++) {
			
			g2.drawLine(ActionEvenement.getPointCheminVoiture().get(j).x + 10,
					ActionEvenement.getPointCheminVoiture().get(j).y + 10,
					ActionEvenement.getPointCheminVoiture().get(j + 1).x + 10,
					ActionEvenement.getPointCheminVoiture().get(j + 1).y + 10);
		}

		g.setColor(Color.BLACK);

			g.drawImage(image, ActionEvenement.getPointCheminVoiture().get(i).x,
					ActionEvenement.getPointCheminVoiture().get(i).y - (image.getHeight()-10),
					null);

		//g.fillOval(ActionEvenement.getPointCheminVoiture().get(i).x, ActionEvenement.getPointCheminVoiture().get(i).y,
			//	15, 15);

	}

	/**
	 * Gestion du timer pour le deplacement du point sur le graphe
	 * 
	 * @param e
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		/**
		 * code pour test
		 */

		if (i < ActionEvenement.getPointCheminVoiture().size() - 1) {
			i++;
		}

		repaint();

	}
}
