package vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controleur.actionEvenement;

public class FrmPanelSimulation extends JFrame{
	
	private JPanel panelsimulation;
	
	/**
	 * Bouttons qui servent a genere des evenements (accident et traffic)
	 */
	private JButton genereAccident;
	private JButton genereTraffic;
	
	/**
	 * Champs de saisi pour entre l'endroit ou on veut generer un evenement
	 */
	private JTextField routeAccident;
	private JTextField routeTraffic;
	
	/**
	 * Label d'indication
	 */
	private JLabel accident;
	private JLabel traffic;
	
	private actionEvenement actionEvenement;
	
	
	
	/**
	 * @return the panelsimulation
	 */
	public JPanel getPanelsimulation() {
		return panelsimulation;
	}

	/**
	 * @param panelsimulation the panelsimulation to set
	 */
	public void setPanelsimulation(JPanel panelsimulation) {
		this.panelsimulation = panelsimulation;
	}

	/**
	 * @return the genereAccident
	 */
	public JButton getGenereAccident() {
		return genereAccident;
	}

	/**
	 * @param genereAccident the genereAccident to set
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
	 * @param genereTraffic the genereTraffic to set
	 */
	public void setGenereTraffic(JButton genereTraffic) {
		this.genereTraffic = genereTraffic;
	}

	/**
	 * @return the routeAccident
	 */
	public JTextField getRouteAccident() {
		return routeAccident;
	}

	/**
	 * @param routeAccident the routeAccident to set
	 */
	public void setRouteAccident(JTextField routeAccident) {
		this.routeAccident = routeAccident;
	}

	/**
	 * @return the routeTraffic
	 */
	public JTextField getRouteTraffic() {
		return routeTraffic;
	}

	/**
	 * @param routeTraffic the routeTraffic to set
	 */
	public void setRouteTraffic(JTextField routeTraffic) {
		this.routeTraffic = routeTraffic;
	}

	/**
	 * @return the accident
	 */
	public JLabel getAccident() {
		return accident;
	}

	/**
	 * @param accident the accident to set
	 */
	public void setAccident(JLabel accident) {
		this.accident = accident;
	}

	/**
	 * @return the traffic
	 */
	public JLabel getTraffic() {
		return traffic;
	}

	/**
	 * @param traffic the traffic to set
	 */
	public void setTraffic(JLabel traffic) {
		this.traffic = traffic;
	}

	/**
	 * @return the actionEvenement
	 */
	public actionEvenement getActionEvenement() {
		return actionEvenement;
	}

	/**
	 * @param actionEvenement the actionEvenement to set
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
		this.setSize(800, 300);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		panelsimulation = new JPanel(new GridLayout(3, 2,5,5));
		panelsimulation.setBackground(Color.BLACK);
		this.add(panelsimulation);
		
		accident = new JLabel("Entre ci-dessous le nom de la route");
		accident.setFont(new Font("Serif", Font.BOLD, 19));
		accident.setForeground(Color.GREEN);
		panelsimulation.add(accident);
		
		traffic = new JLabel("Entrez ci-dessous le nom de la route");
		traffic.setFont(new Font("Serif", Font.BOLD, 19));
		traffic.setForeground(Color.GREEN);
		panelsimulation.add(traffic);
		
		routeAccident = new JTextField("Localisation de l'accident", 10);
		routeAccident.setToolTipText("Nom de la route où a lieu l'accident");
		panelsimulation.add(routeAccident);
		
		routeTraffic = new JTextField("Localisation du traffic", 10);
		routeTraffic.setToolTipText("Nom de la route où a lieu le traffic");
		panelsimulation.add(routeTraffic);
		
		genereAccident = new JButton("Générer un accident");
		genereAccident.setFont(new Font("Serif", Font.BOLD, 19));
		genereAccident.setToolTipText("cliquer pour générer un accident");
		genereAccident.addActionListener(new actionEvenement());//ajout d'un ecouteur sur le boutton
		panelsimulation.add(genereAccident);
		
		genereTraffic = new JButton("Générer un traffic");
		genereTraffic.setFont(new Font("Serif", Font.BOLD, 19));
		genereTraffic.setToolTipText("cliquer pour générer un traffic");
		genereTraffic.addActionListener(new actionEvenement());// ajout d'un ecouteur sur le boutton
		panelsimulation.add(genereTraffic);
			
	}
}
