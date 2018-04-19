package controleur;

import vue.FrmPanelSimulation;
import vue.FrmPanelUtilisateur;
import modele.Graph;
import outils.*;

public class Principale {
	
	public static Graph graphe;
	
	public static void main(String[] args) {
		
		Graph graphe =  new Graph();
		graphe.setNoeuds(new CSV("res/Coordonnees.csv"));
		graphe.setLiens(new CSV("res/liensCorrecte.csv"));
		
		ActionEvenement action = new ActionEvenement(graphe);
		
		FrmPanelUtilisateur frmUtil = new FrmPanelUtilisateur(action);
		frmUtil.setVisible(true);
		FrmPanelSimulation frmSimu = new FrmPanelSimulation(action);
		frmSimu.setVisible(true);
	}
}
