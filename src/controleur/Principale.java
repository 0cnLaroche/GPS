package controleur;

import vue.FrmPanelSimulation;
import modele.Graph;
import outils.*;

public class Principale {
	
	public static Graph graphe;
	
	public static void main(String[] args) {
		
		Graph graphe =  new Graph();
		graphe.setNoeuds(new CSV("res/Coordonnees.csv"));
		graphe.setLiens(new CSV("res/liensCorrecte.csv"));
		
		ActionEvenement action = new ActionEvenement(graphe);
		
		FrmPanelSimulation frmSimu = new FrmPanelSimulation(action);
		frmSimu.setVisible(true);	
	}
}
