package vue;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controleur.actionEvenement;

public class FrmPanelSimulation extends JFrame {

	private JPanel panelSimulation;

	private PanelUtilisateur panelUtilisateur;

	/**
	 * Bouttons qui servent a genere des evenements (accident et traffic)
	 */
	private JButton genereAccident;
	private JButton genereTraffic;
	private JButton demarrerSimulation;
	private JButton lancerNavigation;
	private JButton arreteSimulation;
	private JButton reprendreSimulation;

	/**
	 * Champs de saisi pour entre l'endroit ou on veut generer un evenement
	 */
	private JLabel routeAccident;
	private JTextField routeTraffic;

	private actionEvenement actionEvenement;

	/**
	 * @return the panelUtilisateur
	 */
	public PanelUtilisateur getPanelUtilisateur() {
		return panelUtilisateur;
	}

	/**
	 * @param panelUtilisateur
	 *            the panelUtilisateur to set
	 */
	public void setPanelUtilisateur(PanelUtilisateur panelUtilisateur) {
		this.panelUtilisateur = panelUtilisateur;
	}

	/**
	 * @return the demarrerSimulation
	 */
	public JButton getDemarrerSimulation() {
		return demarrerSimulation;
	}

	/**
	 * @param demarrerSimulation
	 *            the demarrerSimulation to set
	 */
	public void setDemarrerSimulation(JButton demarrerSimulation) {
		this.demarrerSimulation = demarrerSimulation;
	}

	/**
	 * @return the lancerNavigation
	 */
	public JButton getLancerNavigation() {
		return lancerNavigation;
	}

	/**
	 * @param lancerNavigation
	 *            the lancerNavigation to set
	 */
	public void setLancerNavigation(JButton lancerNavigation) {
		this.lancerNavigation = lancerNavigation;
	}

	/**
	 * @return the arreteSimulation
	 */
	public JButton getArreteSimulation() {
		return arreteSimulation;
	}

	/**
	 * @param arreteSimulation
	 *            the arreteSimulation to set
	 */
	public void setArreteSimulation(JButton arreteSimulation) {
		this.arreteSimulation = arreteSimulation;
	}

	/**
	 * @return the reprendreSimulation
	 */
	public JButton getReprendreSimulation() {
		return reprendreSimulation;
	}

	/**
	 * @param reprendreSimulation
	 *            the reprendreSimulation to set
	 */
	public void setReprendreSimulation(JButton reprendreSimulation) {
		this.reprendreSimulation = reprendreSimulation;
	}

	/**
	 * @return the panelsimulation
	 */
	public JPanel getPanelsimulation() {
		return panelSimulation;
	}

	/**
	 * @param panelsimulation
	 *            the panelsimulation to set
	 */
	public void setPanelsimulation(JPanel panelsimulation) {
		this.panelSimulation = panelsimulation;
	}

	/**
	 * @return the genereAccident
	 */
	public JButton getGenereAccident() {
		return genereAccident;
	}

	/**
	 * @param genereAccident
	 *            the genereAccident to set
	 */
	public void setGenereAccident(JButton genereAccident) {
		this.genereAccident = genereAccident;
	}

	/**
	 * @return the genereTraffic
	 */
	public JButton getGenereTraffic() {
		return genereTraffic;
	}

	/**
	 * @param genereTraffic
	 *            the genereTraffic to set
	 */
	public void setGenereTraffic(JButton genereTraffic) {
		this.genereTraffic = genereTraffic;
	}

	/**
	 * @return the routeAccident
	 */
	public JLabel getRouteAccident() {
		return routeAccident;
	}

	/**
	 * @param routeAccident
	 *            the routeAccident to set
	 */
	public void setRouteAccident(JLabel routeAccident) {
		this.routeAccident = routeAccident;
	}

	/**
	 * @return the routeTraffic
	 */
	public JTextField getRouteTraffic() {
		return routeTraffic;
	}

	/**
	 * @param routeTraffic
	 *            the routeTraffic to set
	 */
	public void setRouteTraffic(JTextField routeTraffic) {
		this.routeTraffic = routeTraffic;
	}

	/**
	 * @return the actionEvenement
	 */
	public actionEvenement getActionEvenement() {
		return actionEvenement;
	}

	/**
	 * @param actionEvenement
	 *            the actionEvenement to set
	 */
	public void setActionEvenement(actionEvenement actionEvenement) {
		this.actionEvenement = actionEvenement;
	}

	public FrmPanelSimulation() {
		super(" Simulation ");
		fenetreSimulation();
	}

	public void fenetreSimulation() {

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(950, 250);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		panelSimulation = new JPanel(new GridBagLayout());
		panelSimulation.setBackground(Color.BLACK);
		this.add(panelSimulation);

		/**
		 * Contrainte de mise en forme, elle est utilise pour definir l'emplacement de
		 * composante su le JPanel
		 */
		GridBagConstraints contrainte = new GridBagConstraints();
		/**
		 * Definition de l'espacement entre les composantes
		 */
		contrainte.insets = new Insets(10, 10, 10, 10);

		/**
		 * Redimensionement automatique des composantes
		 */
		contrainte.weightx = 0.5;
		contrainte.weighty = 0.5;

		/**
		 * Composantes de la premiere colonne du GridBagLayout
		 */
		contrainte.anchor = GridBagConstraints.LINE_START;
		demarrerSimulation = new JButton("Demarrer");
		demarrerSimulation.setToolTipText("Cliquer pour affiche le reseau routier");
		demarrerSimulation.addActionListener(new actionEvenement());// ajout d'un ecouteur sur le boutton
		contrainte.fill = GridBagConstraints.HORIZONTAL;
		contrainte.fill = GridBagConstraints.VERTICAL;
		contrainte.ipadx = 10;
		contrainte.gridx = 0;
		contrainte.gridy = 0;
		panelSimulation.add(demarrerSimulation, contrainte);

		/**
		 * Composantes de la deuxieme colonne du GridBagLayout
		 */
		lancerNavigation = new JButton("Lancer la navigation");
		lancerNavigation.setToolTipText("Cliquer pour lancer la navigation");
		lancerNavigation.addActionListener(new actionEvenement());// ajout d'un ecouteur sur le boutton
		contrainte.anchor = GridBagConstraints.CENTER;
		contrainte.fill = GridBagConstraints.HORIZONTAL;
		contrainte.fill = GridBagConstraints.VERTICAL;
		contrainte.gridx = 1;
		contrainte.gridy = 0;
		panelSimulation.add(lancerNavigation, contrainte);

		genereAccident = new JButton("Générer un accident");
		genereAccident.setFont(new Font("Serif", Font.BOLD, 19));
		genereAccident.setToolTipText("cliquer pour générer un accident");
		genereAccident.addActionListener(new actionEvenement());// ajout d'un ecouteur sur le boutton
		contrainte.fill = GridBagConstraints.HORIZONTAL;
		contrainte.fill = GridBagConstraints.VERTICAL;
		contrainte.gridx = 1;
		contrainte.gridy = 2;
		panelSimulation.add(genereAccident, contrainte);

		routeAccident = new JLabel("Entre le nom de la route : ", JLabel.CENTER);
		routeAccident.setFont(new Font("Serif", Font.BOLD, 19));
		routeAccident.setForeground(Color.GREEN);
		contrainte.fill = GridBagConstraints.HORIZONTAL;
		contrainte.fill = GridBagConstraints.VERTICAL;
		contrainte.gridx = 1;
		contrainte.gridy = 1;
		panelSimulation.add(routeAccident, contrainte);

		/**
		 * Composantes de la troisieme colonne du GridBagLayout
		 */
		arreteSimulation = new JButton("Arreter");
		arreteSimulation.setToolTipText("Cliquer pour interompre la simulation");
		arreteSimulation.addActionListener(new actionEvenement());// ajout d'un ecouteur sur le boutton
		contrainte.anchor = GridBagConstraints.CENTER;
		contrainte.fill = GridBagConstraints.HORIZONTAL;
		contrainte.fill = GridBagConstraints.VERTICAL;
		contrainte.gridx = 2;
		contrainte.gridy = 0;
		panelSimulation.add(arreteSimulation, contrainte);

		genereTraffic = new JButton("Générer un traffic");
		genereTraffic.setFont(new Font("Serif", Font.BOLD, 19));
		genereTraffic.setToolTipText("cliquer pour générer un traffic");
		genereTraffic.addActionListener(new actionEvenement());// ajout d'un ecouteur sur le boutton
		contrainte.anchor = GridBagConstraints.CENTER;
		contrainte.fill = GridBagConstraints.HORIZONTAL;
		contrainte.fill = GridBagConstraints.VERTICAL;
		contrainte.gridx = 2;
		contrainte.gridy = 2;
		panelSimulation.add(genereTraffic, contrainte);

		routeTraffic = new JTextField("Nom de la route", 15);
		routeTraffic.setToolTipText("Entrez le nom de la route");
		contrainte.anchor = GridBagConstraints.LINE_START;
		contrainte.fill = GridBagConstraints.HORIZONTAL;
		contrainte.fill = GridBagConstraints.VERTICAL;
		contrainte.ipadx = 40;
		contrainte.gridx = 2;
		contrainte.gridy = 1;
		panelSimulation.add(routeTraffic, contrainte);

		/**
		 * Composantes de la quatrieme colonne du GridBagLayout
		 */
		reprendreSimulation = new JButton("Reprendre");
		reprendreSimulation.setToolTipText("Cliquer pour reprendre la simulation");
		reprendreSimulation.addActionListener(new actionEvenement());// ajout d'un ecouteur sur le boutton
		contrainte.anchor = GridBagConstraints.CENTER;
		contrainte.fill = GridBagConstraints.HORIZONTAL;
		contrainte.fill = GridBagConstraints.VERTICAL;
		contrainte.gridx = 3;
		contrainte.gridy = 0;
		panelSimulation.add(reprendreSimulation, contrainte);

	}
}
