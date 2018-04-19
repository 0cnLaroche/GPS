package modele;

import java.util.ArrayList;

public class Trajet extends Graph {
	
	private ArrayList<Noeud>noeudsEnOrdre;
	
	public Double getLongueur(){
		Double total = 0.0;
		for (Lien l : this.getListeLiens()){
			total += l.getPoid()*l.getCongestion();
		}
		return total;
	}
	public int size(){
		int size = this.getListeNoeuds().size();
		return size;
	}
	public void setListeNoeuds(ArrayList<Noeud> liste){
		this.noeudsEnOrdre = liste;
	}
	@Override
	public ArrayList<Noeud> getListeNoeuds(){
		return this.noeudsEnOrdre;
	}

}
