package modele;

import java.awt.Point;
import java.util.ArrayList;

public class Noeud {
	
	private String nom;
	private Point coordonnees;
	private ArrayList<Lien> voisins;
	
	public Noeud(String nom, Point coordonnees){
		this.nom = nom;
		this.coordonnees = coordonnees;
		this.voisins = new ArrayList<Lien>();
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
	public ArrayList<Lien> getVoisinage(){
		return this.voisins;
	}
	public Lien getVoisin(int index){
		return this.voisins.get(index);
	}
	public void addVoisin(Lien e){
		this.voisins.add(e);
	}
	public void removeVoisin(Lien e){
		this.voisins.remove(e.getIndex());
	}
	public ArrayList<Lien> getVoisins(){
		return this.voisins;
	}
	public String toString(){
		String str = "[nom: " + this.nom + ", coordonnees:{x: " + this.getX() + ", y: " + this.getY() + "}]";
		return str;
	}
}
