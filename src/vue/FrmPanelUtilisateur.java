package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controleur.ActionEvenement;
import modele.Graph;

public class FrmPanelUtilisateur extends JFrame {

	private PanelUtilisateur panelUtilisateur;

	private static JPanel informationUtilisateur;

	private static JLabel avertissementEvenement;

	private static JLabel directionAPrendre;

	private static JLabel tempParcours;
	
	private static JLabel etatTraffic;

	private Graph graphe;
	
	private ActionEvenement actionEvenement;
	
	//private ActionEvenement controleur;

	/**
	 * constructeur de la classe
	 */
	public FrmPanelUtilisateur(ActionEvenement controller) {
		super("Systemes de transports intelligents");
		this.actionEvenement = controller; //ajouter pour le test

		fenetreUtilisateur();
	}
	
	/**
	 * Methode accesseur
	 */
	
	

	/**
	 * @return the panelUtilisateur
	 */
	public PanelUtilisateur getPanelUtilisateur() {
		return panelUtilisateur;
	}

	/**
	 * @return the etatTraffic
	 */
	public static JLabel getEtatTraffic() {
		return etatTraffic;
	}

	/**
	 * @param etatTraffic the etatTraffic to set
	 */
	public static void setEtatTraffic(JLabel etatTraffic) {
		FrmPanelUtilisateur.etatTraffic = etatTraffic;
	}

	/**
	 * @param panelUtilisateur the panelUtilisateur to set
	 */
	public void setPanelUtilisateur(PanelUtilisateur panelUtilisateur) {
		this.panelUtilisateur = panelUtilisateur;
	}



	/**
	 * @return the informationUtilisateur
	 */
	public static JPanel getInformationUtilisateur() {
		return FrmPanelUtilisateur.informationUtilisateur;
	}



	/**
	 * @param informationUtilisateur the informationUtilisateur to set
	 */
	public static void setInformationUtilisateur(JPanel informationUtilisateur) {
		FrmPanelUtilisateur.informationUtilisateur = informationUtilisateur;
	}



	/**
	 * @return the avertissementEvenement
	 */
	public static JLabel getAvertissementEvenement() {
		return FrmPanelUtilisateur.avertissementEvenement;
	}



	/**
	 * @param avertissementEvenement the avertissementEvenement to set
	 */
	public static void setAvertissementEvenement(JLabel avertissementEvenement) {
		FrmPanelUtilisateur.avertissementEvenement = avertissementEvenement;
	}



	/**
	 * @return the directionAPrendre
	 */
	public static JLabel getDirectionAPrendre() {
		return FrmPanelUtilisateur.directionAPrendre;
	}



	/**
	 * @param directionAPrendre the directionAPrendre to set
	 */
	public static void setDirectionAPrendre(JLabel directionAPrendre) {
		FrmPanelUtilisateur.directionAPrendre = directionAPrendre;
	}



	/**
	 * @return the tempParcours
	 */
	public static JLabel getTempParcours() {
		return FrmPanelUtilisateur.tempParcours;
	}



	/**
	 * @param tempParcours the tempParcours to set
	 */
	public static void setTempParcours(JLabel tempParcours) {
		FrmPanelUtilisateur.tempParcours = tempParcours;
	}



	/**
	 * Methode qui contient les proprietes du JFrame (fenetre) FrmPanelUtilisateur
	 */
	public void fenetreUtilisateur() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1000, 1000);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setAlwaysOnTop(false);

		/**
		 * Creation et ajout du JPanel qui contient le dessin (graphe) du reseau routier sur le
		 * JFrame
		 */
		panelUtilisateur = new PanelUtilisateur(actionEvenement);
		this.add(panelUtilisateur);

		/**
		 * Ajout du panel qui contient les labels d'affichage des messages utilisateurs
		 * sur le JFrame
		 */
		informationUtilisateur = new JPanel(new GridBagLayout());
		informationUtilisateur.setBackground(Color.BLACK);
		this.add(informationUtilisateur, BorderLayout.NORTH);

		avertissementEvenement = new JLabel("Avertissements des evenements");
		avertissementEvenement.setFont(new Font("Serif", Font.BOLD, 19));
		avertissementEvenement.setForeground(Color.GREEN);

		directionAPrendre = new JLabel("Directions a prendre");
		directionAPrendre.setFont(new Font("Serif", Font.BOLD, 19));
		directionAPrendre.setForeground(Color.GREEN);

		tempParcours = new JLabel("Temps de parcours");
		tempParcours.setFont(new Font("Serif", Font.BOLD, 19));
		tempParcours.setForeground(Color.GREEN);
		
		etatTraffic = new JLabel("Etat du traffic");
		etatTraffic.setFont(new Font("Serif", Font.BOLD, 19));
		etatTraffic.setForeground(Color.GREEN);

		/**
		 * contrainte de mise en forme des composantes du JPanel informationUtilisateur
		 */
		GridBagConstraints panelInformationUtilisateur = new GridBagConstraints();

		panelInformationUtilisateur.insets = new Insets(40, 20, 40, 20);

		/**
		 * placer le label avertissementEvenement a la colonne 1, ligne 1 du le JPanel
		 * informationUtilisateur en utilisant le GridBagLayout
		 */
		panelInformationUtilisateur.gridx = 1;
		panelInformationUtilisateur.gridy = 0;
		informationUtilisateur.add(avertissementEvenement, panelInformationUtilisateur);

		/**
		 * placer le label directionAPrendre a la colonne 2, ligne 1 du le JPanel
		 * informationUtilisateur en utilisant le GridBagLayout
		 */
		panelInformationUtilisateur.gridx = 2;
		panelInformationUtilisateur.gridy = 0;
		informationUtilisateur.add(directionAPrendre, panelInformationUtilisateur);

		/**
		 * placer le label tempParcours a la colonne 3, ligne 1 du le JPanel
		 * informationUtilisateur en utilisant le GridBagLayout
		 */
		panelInformationUtilisateur.gridx = 3;
		panelInformationUtilisateur.gridy = 0;
		informationUtilisateur.add(tempParcours, panelInformationUtilisateur);
		
		/**
		 * Placer le label qui signal l'etat du traffic
		 */
		panelInformationUtilisateur.gridx = 4;
		panelInformationUtilisateur.gridy = 0;
		informationUtilisateur.add(etatTraffic, panelInformationUtilisateur);

	}

}
