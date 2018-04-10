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

	private JPanel informationUtilisateur;

	private JLabel avertissementEvenement;

	private static JLabel directionAPrendre;

	private JLabel tempParcours;

	private Graph graphe;

	
	

	public JLabel getTempParcours() {
		return tempParcours;
	}

	public void setTempParcours(JLabel tempParcours) {
		this.tempParcours = tempParcours;
	}

	public JPanel getInformationUtilisateur() {
		return informationUtilisateur;
	}

	public void setInformationUtilisateur(JPanel informationUtilisateur) {
		this.informationUtilisateur = informationUtilisateur;
	}

	public JLabel getAvertissementEvenement() {
		return avertissementEvenement;
	}

	public void setAvertissementEvenement(JLabel avertissementEvenement) {
		this.avertissementEvenement = avertissementEvenement;
	}

	public static JLabel getDirectionAPrendre() {
		return directionAPrendre;
	}

	public static void setDirectionAPrendre(JLabel directionAPrendre) {
		FrmPanelUtilisateur.directionAPrendre = directionAPrendre;
	}

	/**
	 * constructeur de la classe
	 */
	public FrmPanelUtilisateur() {
		super("Systemes de transports intelligents");
		//controleur = new ActionEvenement();//ajouter pour le test
		graphe = new Graph();
		fenetreUtilisateur();
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
		 * Ajout du panel qui contient les labels d'affichage des messages utilisateurs
		 * sur le JFrame
		 */
		informationUtilisateur = new JPanel(new GridBagLayout());
		informationUtilisateur.setBackground(Color.BLACK);
		this.add(informationUtilisateur, BorderLayout.NORTH);

		/**
		 * Creation et ajout du JPanel qui contient le dessin (graphe) du reseau routier sur le
		 * JFrame
		 */
		panelUtilisateur = new PanelUtilisateur(graphe);
		this.add(panelUtilisateur);

		avertissementEvenement = new JLabel("Messages d'avertissements des evenements");
		avertissementEvenement.setFont(new Font("Serif", Font.BOLD, 19));
		avertissementEvenement.setForeground(Color.GREEN);

		directionAPrendre = new JLabel("Affichage des directions a prendre");
		directionAPrendre.setFont(new Font("Serif", Font.BOLD, 19));
		directionAPrendre.setForeground(Color.GREEN);

		tempParcours = new JLabel("Affichage du temps de parcours");
		tempParcours.setFont(new Font("Serif", Font.BOLD, 19));
		tempParcours.setForeground(Color.GREEN);

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

	}

}
