/**
 * code test
 */

package vue;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Map.Entry;
import java.awt.geom.*;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import controleur.ActionEvenement;
import modele.Graph;
import modele.Lien;
import modele.Noeud;

public class PanelUtilisateur extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private FrmPanelUtilisateur panelUtilisateur;
	private Timer t = new Timer(25, this);
	private Noeud n1 = null, n2 = null;
	private Graph graphe;
	private Lien valeur;
	double y, x = 0;
	boolean check = false;
	double a, b; // pente et ordonne a l'origine de la droite
	double dx, dy, temps, frames, stepx, stepy, noeud1X, noeud1Y, noeud2X, noeud2Y;

	private static int delaisTimer;

	int i = 0; // indice du tableau pointCheminCourt

	private Image image;

	private Image imgToutDroit;

	private Image imgTourneDroite;

	private Image imgTourneGauche;

	/**
	 * declaration d'un attribut qui a le type de la classe qui contient les actions
	 * correspondant aux evenements
	 */
	// private actionEvenement actionEvenement = new actionEvenement(this);

	int cpt = 0; // temporaire pour le test

	public PanelUtilisateur(Graph graphe) {
		super.setBackground(new Color(220,220,220));
		// graphe.setNoeuds(new CSV("SystemGuidageRoutier/res/Coordonnees.csv"));
		// graphe.setLiens(new CSV("SystemGuidageRoutier/res/liens.csv"));
		this.graphe = graphe;
		t.start();
		delaisTimer = t.getDelay();

		try {
			//J'ai mis une autre image sans background mais on peut la changer
			this.image = ImageIO.read(getClass().getResourceAsStream("/pointeur.png"));
			image = image.getScaledInstance(40, 40, image.SCALE_DEFAULT);
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.imgToutDroit = new ImageIcon(this.getClass().getResource("/toutDroit.png")).getImage();
		this.imgTourneDroite = new ImageIcon(this.getClass().getResource("/tournerDroite.png")).getImage();
		this.imgTourneGauche = new ImageIcon(this.getClass().getResource("/tournerGauche.png")).getImage();
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
		Graphics2D g2 = (Graphics2D) g;
		double x1, y1, x2, y2 = 0;
		// int x1, y1, x2, y2 = 0;
		/**
		 * Dessiner les noeud et les liens entre les noeuds sur le Panel
		 */
		for (Entry<Integer, Lien> entry : graphe.getLiens().entrySet()) {
			// Integer cle = entry.getKey();
			valeur = entry.getValue();

			/*
			 * x1 = valeur.getNoeudUn().getCoordonnees().x; y1 =
			 * valeur.getNoeudUn().getCoordonnees().y;
			 * 
			 * x2 = valeur.getNoeudDeux().getCoordonnees().x; y2 =
			 * valeur.getNoeudDeux().getCoordonnees().y; g.setColor(Color.RED);
			 * 
			 * g.fillOval(x1, y1, 15, 15); g.drawString(valeur.getNoeudUn().getNom(), x1,
			 * y1); g.fillOval(x2, y2, 15, 15); g.drawString(valeur.getNoeudDeux().getNom(),
			 * x2, y2);
			 */

			/**
			 * Dessin utilisant la classe Graphics2D
			 */
			x1 = valeur.getNoeudUn().getCoordonnees().getX();
			y1 = valeur.getNoeudUn().getCoordonnees().getY();

			x2 = valeur.getNoeudDeux().getCoordonnees().getX();
			y2 = valeur.getNoeudDeux().getCoordonnees().getY();

			g2.setColor(Color.RED);

			/**
			 * Placer le premier noeud du lien
			 */
			g2.fill(new Ellipse2D.Double(valeur.getNoeudUn().getX(), valeur.getNoeudUn().getY(), 15, 15));

			/**
			 * Placer le deuxieme noeud du lien
			 */
			g2.fill(new Ellipse2D.Double(valeur.getNoeudDeux().getX(), valeur.getNoeudDeux().getY(), 15, 15));
			
			/**
			 * Dessiner le nom de route
			 */
			g2.setStroke(new BasicStroke(8));
			g2.setColor(Color.BLACK);
			g2.setFont(new Font("default", Font.BOLD, 20));
			
			g2.drawString(valeur.getNoeudUn().getNom(), valeur.getNoeudUn().getCoordonnees().x,
					valeur.getNoeudUn().getCoordonnees().y - 2);
			
			g2.drawString(valeur.getNoeudDeux().getNom(), valeur.getNoeudDeux().getCoordonnees().x,
					valeur.getNoeudDeux().getCoordonnees().y - 2);

			g2.setColor(Color.BLUE);

			g2.setStroke(new BasicStroke(2));
			
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			/**
			 * Dessiner le lien entre les deux noeuds
			 */
			g2.draw(new Line2D.Double(valeur.getNoeudUn().getX() + 8, valeur.getNoeudUn().getY() + 8,
					valeur.getNoeudDeux().getX() + 8, valeur.getNoeudDeux().getY() + 8));

			/**
			 * fin code dessin utilisant la classe Graphics2D
			 */

			try {

				g.setColor(Color.BLACK);

				/**
				 * iterer sur la liste contenant les noeuds voisins au noeud courant et dessiner
				 * a chaque fois les liens vers ses voisins
				 */
				// g.drawLine(x1 + 10, y1 + 10, x2 + 10, y2 + 10);

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

			// g2.draw(new Line2D.Double(ActionEvenement.getPointCheminVoiture().get(j),
			// ActionEvenement.getPointCheminVoiture().get(j + 1)));

			g2.drawLine(ActionEvenement.getPointCheminVoiture().get(j).x + 10,
					ActionEvenement.getPointCheminVoiture().get(j).y + 10,
					ActionEvenement.getPointCheminVoiture().get(j + 1).x + 10,
					ActionEvenement.getPointCheminVoiture().get(j + 1).y + 10);

		}

		/*		*//**
					 * Affichage des direction a prendre avec les icones
					 *//*
						 * if(i < ActionEvenement.getPointCheminVoiture().size() - 1) {
						 * 
						 * if(ActionEvenement.getPointCheminVoiture().get(i).y <
						 * ActionEvenement.getPointCheminVoiture().get(i + 1).y) {
						 * 
						 * FrmPanelUtilisateur.getDirectionAPrendre().setIcon(new
						 * ImageIcon(imgTourneDroite));
						 * FrmPanelUtilisateur.getDirectionAPrendre().setText("Tournez a droite");
						 * 
						 * }else if(ActionEvenement.getPointCheminVoiture().get(i).y >
						 * ActionEvenement.getPointCheminVoiture().get(i + 1).y) {
						 * FrmPanelUtilisateur.getDirectionAPrendre().setIcon(new
						 * ImageIcon(imgTourneGauche));
						 * FrmPanelUtilisateur.getDirectionAPrendre().setText("Tournez a gauche"); }else
						 * { FrmPanelUtilisateur.getDirectionAPrendre().setIcon(new
						 * ImageIcon(imgToutDroit));
						 * FrmPanelUtilisateur.getDirectionAPrendre().setText("Continuez tout droit"); }
						 * 
						 * }
						 */

		g.drawImage(image, ActionEvenement.getPointCheminVoiture().get(i).x,
				//ActionEvenement.getPointCheminVoiture().get(i).y - (image.getHeight(null) - 10), null);
				ActionEvenement.getPointCheminVoiture().get(i).y - (image.getHeight(null)-40), null);

		// g.fillOval(ActionEvenement.getPointCheminVoiture().get(i).x,
		// ActionEvenement.getPointCheminVoiture().get(i).y, 15, 15);

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
