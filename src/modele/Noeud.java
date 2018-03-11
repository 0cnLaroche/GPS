package modele;

import java.util.List;

public class Noeud {
	
	public String nom;
	public List<Lien> voisins;
	
	public Noeud(String nom){
		this.nom = nom;
	}
	
	public String getNom(){
		return this.nom;
	}
	public void ajouterVoisin(Lien e){
		this.voisins.add(e);
	}
	public List<Lien> getVoisins(){
		return this.voisins;
	}
}
