package modele;

public class Lien {
	
	public Noeud a,b;
	public double poid; //distance
	public Integer nom;
	
	public Lien(Noeud a, Noeud b){
		/**Créer un lien et calcule automatiquement la distance (ou poid) selon l'hypotenuse des coordonnées déclarer dans 
		 * la variable coordonnees de type Point de Noeud.
		 * @author Samuel
		 */
		this.a = a;
		this.b = b;	
		this.poid = Math.hypot(b.getX()-a.getX(), b.getY()-a.getY());
	}
	
	public Integer getNom(){
		return this.nom;
	}
	public void setPoid(Double poid){
		this.poid = poid;
	}
	public Double getPoid(){
		return this.poid;
	}
	public Noeud getVoisin(Noeud courant) throws Exception {

		if(courant.getNom() == a.getNom()){
			return b;
		} else if (courant.getNom() == b.getNom()){
			return a;
		} else {
			throw new Exception("Le point ne fait pas partie du Graph");
		}
	}
	public int getHash(){
		int hash = a.getNom().hashCode() + b.getNom().hashCode();
		return hash;
	}

}
