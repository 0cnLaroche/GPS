package modele;

import java.time.*;
import java.lang.Math;

public class Lien {
	
	private Noeud a,b;
	private double poid; //distance......deffinir la distance comme un int
	private Integer index;
	public Evenement evenement;
	
	public Lien(Noeud a, Noeud b){
		/**Créer un lien et calcule automatiquement la distance (ou poid) selon l'hypotenuse des coordonnées déclarer dans 
		 * la variable coordonnees de type Point de Noeud.
		 * @author Samuel
		 */
		this.a = a;
		this.b = b;	
		this.poid = Math.hypot(b.getX()-a.getX(), b.getY()-a.getY()); // calculer le poid en precision int donc b.getCoordonnees().x
		this.index = (a.getNom().hashCode() + b.getNom().hashCode());
		
	}
	
	public Integer getIndex(){
		return this.index;
	}
	public void setPoid(Double poid){
		this.poid = poid;
	}
	
	public Double getPoid(){
		/** La méthode ajuste le poid à la hausse si l'heure courante s'approche des heures de pointes. 
		 * Les heures de haute densité sont 8h et 17h.
		 * La densité suit un fonction cosinus entre 4h et 21h, la densité est constante à l'exterieur de ces heures.
		 * Plus on s'approche de 8h et 17h, plus le poid (temps) retourné est élevé.
		 * Lors des pointes, le facteur maximum est 3x le poid normal.
		 * 
		 * Si un évènement (traffic ou accident) affecte la circulation, le poid est haussé.
		 * @author Samuel
		 */
		double poid;
		double time = (double) LocalTime.now().getHour() + (double) LocalTime.now().getMinute() / 60;;
		if (3.94 < time && time < 20.905){
			//poid = poid départ (distance) * facteur heures de pointes * facteur evenement
			poid = this.poid * (Math.cos(time / 1.35 + 0.7 / Math.PI ) + 2);
		} else {
			poid = this.poid;
		}
		if (evenement == null) {
			return poid;
		} else {
			return poid * evenement.getCongestion();
		}
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
	public Noeud getNoeudUn(){
		return this.a;
	}
	public Noeud getNoeudDeux(){
		return this.b;
	}

	public int hashCode(){
		int hash = a.getNom().hashCode() + b.getNom().hashCode();
		return hash;
	}
	public String toString(){
		String str = "[index: " + this.index + ", Noeuds:{" + a.getNom() + "," + b.getNom() + "}, poid: ";
		str += this.poid + "]\n";
		return str;
	}

}
