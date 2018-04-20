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
import java.util.ArrayList;
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

	private int i = 0, j = 0; // indice du tableau pointCheminCourt

	private Image image;

	private Image imgToutDroit;

	private Image imgTourneDroite;

	private Image imgTourneGauche;
	
	private Image imgpointRouge;
	
	private Image imgpointVert;
	
	private double tempDeParcour = 0;
	
	private ActionEvenement actionEvenement;

	/**
	 * declaration d'un attribut qui a le type de la classe qui contient les actions
	 * correspondant aux evenements
	 */
	

	int cpt = 0; // temporaire pour le test

	public PanelUtilisateur(ActionEvenement controller) {
		
		this.actionEvenement = controller;
		super.setBackground(new Color(220,220,220));
		this.graphe = controller.getGraphe();
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
		this.imgpointRouge = new ImageIcon(this.getClass().getResource("/pointRouge.png")).getImage();
		this.imgpointVert = new ImageIcon(this.getClass().getResource("/pointVert.png")).getImage();
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
		/**
		 * Dessiner les noeud et les liens entre les noeuds sur le Panel
		 */
		for (Lien l : graphe.getListeLiens()) {
			// Integer cle = entry.getKey();
			valeur = l;

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

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		g.setColor(Color.GREEN);
		g2.setStroke(new BasicStroke(5));
	
	
		if(actionEvenement.getTrajet() != null){
			/**
			 * Desinner le trajet
			 */
			for (int j = 0; j < actionEvenement.getTrajet().size() - 1; j++) { 
				
				ArrayList<Noeud> list = actionEvenement.getTrajet().getListeNoeuds();

				g2.drawLine(list.get(j).getCoordonnees().x + 10, 
						(list.get(j).getCoordonnees().y + 10), 
						list.get(j + 1).getCoordonnees().x + 10,
						list.get(j + 1).getCoordonnees().y + 10);
			}
			/**
			 * Affichage des direction a prendre
			 */
			if (i < ActionEvenement.getIconesDirection().size()) {
				if (ActionEvenement.getIconesDirection().get(i).equals("toutDroit")) {
					FrmPanelUtilisateur.getDirectionAPrendre().setIcon(new ImageIcon(imgToutDroit));
					FrmPanelUtilisateur.getDirectionAPrendre().setText("Continuez tout droit");
					FrmPanelUtilisateur.getEtatTraffic().setIcon(new ImageIcon(imgpointVert));
					FrmPanelUtilisateur.getEtatTraffic().setText("Traffic fluide");
					System.out.println(i + "tout droit");

				} else if (ActionEvenement.getIconesDirection().get(i).equals("tournerDroite")) {
					FrmPanelUtilisateur.getDirectionAPrendre().setIcon(new ImageIcon(imgTourneDroite));
					FrmPanelUtilisateur.getDirectionAPrendre().setText("Tournez a droite");
					FrmPanelUtilisateur.getEtatTraffic().setIcon(new ImageIcon(imgpointVert));
					FrmPanelUtilisateur.getEtatTraffic().setText("Traffic fluide");
					System.out.println(i + "droite");
					
				} else if (ActionEvenement.getIconesDirection().get(i).equals("tournerGauche")) {
					FrmPanelUtilisateur.getDirectionAPrendre().setIcon(new ImageIcon(imgTourneGauche));
					FrmPanelUtilisateur.getDirectionAPrendre().setText("Tournez a gauche");
					FrmPanelUtilisateur.getEtatTraffic().setIcon(new ImageIcon(imgpointVert));
					FrmPanelUtilisateur.getEtatTraffic().setText("Traffic fluide");
					System.out.println(i + "gauche");
				}
			}
			
			/**
			 * Indication de la congestion
			 */
			if(ActionEvenement.getIndicationCongestion().equals("imgpointRouge")) {
				FrmPanelUtilisateur.getEtatTraffic().setIcon(new ImageIcon(imgpointRouge));
				FrmPanelUtilisateur.getEtatTraffic().setText("Congestion!!!");
			}

			g.drawImage(image,ActionEvenement.getPointCheminVoiture().get(i).x -10,
					ActionEvenement.getPointCheminVoiture().get(i).y - 10, null);
			
			FrmPanelUtilisateur.getTempParcours().setText(String.valueOf("Durée du parcour : " + (int)actionEvenement.getTempParcours()));
			
		}


		
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

		if (ActionEvenement.getPointCheminVoiture() != null){
			if(i < ActionEvenement.getPointCheminVoiture().size() - 1 ) {
				i++;
		}
/*			if(ActionEvenement.getPointCheminVoiture() != null ) {
				if(j < ActionEvenement.getPointCheminVoiture().size() - 1) {
					j++;
				}
			}*/
	}

		repaint();

	}
}
