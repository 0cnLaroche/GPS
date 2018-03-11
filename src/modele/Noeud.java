package modele;

import java.awt.Point;
import java.util.List;

public class Noeud {
	
	public String nom;
	private Point coordonnees;
	public List<Lien> voisins;
	
	public Noeud(String nom, Point coordonnees){
		this.nom = nom;
		this.coordonnees = coordonnees;
	}
	
	public String getNom(){
		return this.nom;
	}
	public Point getCoordonnees(){
		return this.coordonnees;
	}
	public void setCoordonnees(Point point){
		this.coordonnees = point;
	}
	public Double getX(){
		return coordonnees.getX();
	}
	public Double getY(){
		return coordonnees.getY();
	}
	public void addVoisin(Lien e){
		this.voisins.add(e);
	}
	public void removeVoisin(Lien e){
		this.voisins.remove(e.getNom());
	}
	public List<Lien> getVoisins(){
		return this.voisins;
	}
}
