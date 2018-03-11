package modele;

public class Lien {
	
	public Noeud a,b;
	public double poid; //distance
	public Integer nom;
	
	public Lien(Noeud a, Noeud b, Double poid){
		this.a = a;
		this.b = b;
		this.poid = poid;
	}
	
	public Integer getNom(){
		return this.nom;
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
