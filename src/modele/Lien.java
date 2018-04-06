package modele;

public class Lien {
	
	private Noeud a,b;
	private double poid; //distance......deffinir la distance comme un int
	private Integer index;
	
	public Lien(Noeud a, Noeud b){
		/**Créer un lien et calcule automatiquement la distance (ou poid) selon l'hypotenuse des coordonnées déclarer dans 
		 * la variable coordonnees de type Point de Noeud.
		 * @author Samuel
		 */
		this.a = a;
		this.b = b;	
		this.poid = Math.hypot(b.getX()-a.getX(), b.getY()-a.getY()); // calculer le poid en precision int donc b.getCoordonnees().x
		this.index = (a.getNom() + b.getNom()).hashCode();
	}
	
	public Integer getIndex(){
		return this.index;
	}
	public void setPoid(Double poid){
		this.poid = poid;
	}
	public Double getPoid(){
		return this.poid;
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
