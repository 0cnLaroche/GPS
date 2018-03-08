package controleur;

import vue.FrmPanelSimulation;
import vue.FrmPanelUtilisateur;

public class Principale {

	public static void main(String[] args) {

		FrmPanelUtilisateur frm = new FrmPanelUtilisateur();
		frm.setVisible(true);
		
		FrmPanelSimulation frmSimu = new FrmPanelSimulation();
		frmSimu.setVisible(true);

	}
}
