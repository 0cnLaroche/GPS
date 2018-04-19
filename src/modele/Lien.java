package modele;

import java.time.*;
import java.lang.Math;


public class Lien {
	
	private Noeud a,b;
	private double poid; //distance......deffinir la distance comme un int
	private Integer index;
	private Evenement evenement;
	
	public Lien(Noeud a, Noeud b){
		/**Créer un lien et calcule automatiquement la distance (ou poid) selon l'hypotenuse des coordonnées déclarer dans 
		 * la variable coordonnees de type Point de Noeud.
		 * @author Samuel
		 */
		this.a = a;
		this.b = b;	
		this.poid = Math.hypot(b.getX()-a.getX(), b.getY()-a.getY()); // calculer le poid en precision int donc b.getCoordonnees().x
		this.index = a.getNom().hashCode() + b.getNom().hashCode();
	}
	public double getCongestion(){
		/**
		 * La méthode retourne un facteur de congestion à la hausse si l'heure courante s'approche
		 * des heures de pointes. Les heures de haute densité sont 8h et 17h. La
		 * densité suit une fonction cosinus entre 4h et 21h, la densité est
		 * constante à l'exterieur de ces heures. Plus on s'approche de 8h et
		 * 17h, plus le poid (temps) retourné est élevé. Lors des pointes, le
		 * facteur maximum est 3x le poid normal.
		 * 
		 * Si un évènement (traffic ou accident) affecte la circulation, le facteur
		 * est haussé.
		 * 
		 * @author Samuel
		 */
		double congestion = 1; //Pour s'assurer que 1 est retourne si jamais il y a une erreur avec la formule
		
		float time = (float) LocalTime.now().getHour() + (float) LocalTime.now().getMinute() / 60;
		
		if (7.368 < time && time < 8.625) {
			// poid = poid départ (distance) * facteur heures de pointes *
			// facteur evenement
			congestion = (Math.cos(time / 0.2 + 4) + 2);
		} else if (15.536 < time && time < 18.05){
			congestion = (Math.cos(time / 0.4 + 2) + 2);
		}
		if (evenement == null) {
			return congestion;
		} else {
			return congestion * evenement.getCongestion();
		}
	}
	
	public Integer getIndex(){
		return this.index;
	}
	public void addEvenement(Evenement e){
		this.evenement = e;
	}
	public Evenement getEvenement(){
		return this.evenement;
	}
	public void setPoid(Double poid){
		this.poid = poid;
	}

	public Double getPoid() {
		return poid;
		
	}
	public Double comparePoid(Lien autre){
		return this.poid - autre.getPoid();
	}
	public Noeud getVoisin(Noeud courant) throws Exception {

		if(courant.getNom() == a.getNom()){
			return b;
		} else if (courant.getNom() == b.getNom()){
			return a;
		} else {
			throw new Exception("Le point ne fait pas partie de ce liens Lien");
		}
	}
	public boolean contiens(Noeud n){
		if (n.getNom() == a.getNom() || n.getNom() == b.getNom()){
			return true;
		} else 
			return false;
	}
	public Noeud getNoeudUn(){
		return this.a;
	}
	public Noeud getNoeudDeux(){
		return this.b;
	}
	public int getHash(){
		int hash = a.getNom().hashCode() + b.getNom().hashCode();
		return hash;
	}
	public String toString(){
		String str = "[index: " + this.index + ", Noeuds:{" + a.getNom() + "," + b.getNom() + "}, poid: ";
		str += this.poid + "]";
		return str;
	}

}
