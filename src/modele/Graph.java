package modele;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import outils.CSV;

public class Graph {

	private HashMap<String, Noeud> noeuds; // indexé par lettres de l'alphabet
	private HashMap<Integer, Lien> liens;
	private HashMap<Integer, Evenement> evenements; //Index = id du lien concerne
	private Point coordonnnePointAjour;

	private ArrayList<Noeud> plusCourtChemin; // contient les noeuds se trouvant dans le chemin
												// le plus court

	//private Noeud tempoPlusPetit; // contient le noeud qui a le plus petit chemin dans le graphe

	private ArrayList<Noeud> noeudCheminPlusCourt; // tableau qui contient les noeuds du chemin le plus court
	
	private int cpt = 1;
	
	/**
	 * constructeur de class
	 */
	public Graph() {
		this.noeuds = new HashMap<String, Noeud>();
		this.liens = new HashMap<Integer, Lien>();
		this.plusCourtChemin = new ArrayList<Noeud>();
		this.noeudCheminPlusCourt = new ArrayList<Noeud>();
		this.evenements = new HashMap<Integer, Evenement>();
	}

	/**
	 * Methodes accesseurs
	 */

	/**
	 * @return the plusCourtChemin
	 */
	public ArrayList<Noeud> getPlusCourtChemin() {
		return plusCourtChemin;
	}

	/**
	 * @return the noeudCheminPlusCourt
	 */
	public ArrayList<Noeud> getNoeudCheminPlusCourt() {
		return noeudCheminPlusCourt;
	}

	/**
	 * @param noeudCheminPlusCourt
	 *            the noeudCheminPlusCourt to set
	 */
	public void setNoeudCheminPlusCourt(ArrayList<Noeud> noeudCheminPlusCourt) {
		this.noeudCheminPlusCourt = noeudCheminPlusCourt;
	}

	/**
	 * @param plusCourtChemin
	 *            the plusCourtChemin to set
	 */
	public void setPlusCourtChemin(ArrayList<Noeud> plusCourtChemin) {
		this.plusCourtChemin = plusCourtChemin;
	}

	/**
	 * @return the noeuds
	 */
	public HashMap<String, Noeud> getNoeuds() {
		return noeuds;
	}
	/**
	 * @return the liens
	 */
	public HashMap<Integer, Lien> getLiens() {
		return liens;
	}
	public ArrayList<Lien> getListeLiens(){
		Iterator it = liens.entrySet().iterator();
		ArrayList<Lien> list = new ArrayList<Lien>();
		while(it.hasNext()){
			Map.Entry pair = (Entry) it.next();
			list.add((Lien) pair.getValue());
		}
		return list;		
	}
	public ArrayList<Noeud> getListeNoeuds(){
		Iterator it = noeuds.entrySet().iterator();
		ArrayList<Noeud> list = new ArrayList<Noeud>();
		while(it.hasNext()){
			Map.Entry pair = (Entry) it.next();
			list.add((Noeud) pair.getValue());
		}
		return list;		
	}
	public HashMap<Integer, Evenement> getEvenements(){
		return this.evenements;
	}
	/**
	 * @return the coordonnnePointAjour
	 */
	public Point getCoordonnnePointAjour() {
		return coordonnnePointAjour;
	}

	/**
	 * @param coordonnnePointAjour
	 *            the coordonnnePointAjour to set
	 */
	public void setCoordonnnePointAjour(Point coordonnnePointAjour) {
		this.coordonnnePointAjour = coordonnnePointAjour;
	}

	/**
	 * @param noeuds
	 *            the noeuds to set
	 */
	public void setNoeuds(HashMap<String, Noeud> noeuds) {
		this.noeuds = noeuds;
	}
	/**
	 * @param liens
	 *            the liens to set
	 */
	public void setLiens(HashMap<Integer, Lien> liens) {
		this.liens = liens;
	}

	public Noeud getNoeud(String nom) {
		return this.noeuds.get(nom);
	}

	public Lien getLien(int index) {
		return this.liens.get(index);
	}
	public Evenement getEvenement(int index){
		return this.evenements.get(index);
	}

	public void addLien(Noeud un, Noeud deux) {
		Lien l = new Lien(un, deux);
		liens.put(l.hashCode(), l);
		un.addVoisin(l);
		deux.addVoisin(l);
	}

	public void addNoeud(Noeud noeud) {
		this.noeuds.put(noeud.getNom(), noeud);
	}
	
	public void addEvenement(Lien lien, Evenement evenement){
		evenements.put(lien.getIndex(), evenement);
		//lien.addEvenement
		
	}

	public void setLiens(CSV source) {
		for (ArrayList<String> champs : source) {

			if (source.get(0).equals(champs)) // La premiere rangee contiens les titres. On ignore
				continue;

			String[] split = champs.get(1).split(";"); // Sépare la colonne de gauche
			for (String s : split) {
				this.addLien(noeuds.get(champs.get(0)), noeuds.get(s)); // Appelle la methode de classe
																		// Les noeuds seront charges de leurs voisins
			}
		}
	}

	public void setNoeuds(CSV source) {
		for (ArrayList<String> champs : source) {

			if (source.get(0).equals(champs)) // La premiere rangee contiens les titres. On ignore
				continue;

			String nom = champs.get(0);

			Integer x = (int) Double.parseDouble(champs.get(1)); // La methode getX() de Point retourne un Double
			Integer y = (int) Double.parseDouble(champs.get(2)); // Mais les variables x et y sont des int
																	// Il faut donc faire la conversion de Double à int
			Noeud n = new Noeud(nom, new Point(x, y));
			this.addNoeud(n);
		}
	}
	public Trajet getTrajet(){
		if (noeudCheminPlusCourt != null && !noeudCheminPlusCourt.isEmpty()){
			Trajet trajet = new Trajet();
			ArrayList<Noeud> noeudsEnOrdre = new ArrayList<Noeud>();
			for (int i=noeudCheminPlusCourt.size()-1;i>0;i-- ){
				Noeud depart = noeudCheminPlusCourt.get(i);
				Noeud arrive = noeudCheminPlusCourt.get(i-1);
				trajet.addNoeud(depart);
				trajet.addNoeud(arrive);
				trajet.addLien(depart, arrive);
				noeudsEnOrdre.add(noeudCheminPlusCourt.get(i));
			}
			noeudsEnOrdre.add(noeudCheminPlusCourt.get(0));
			trajet.setListeNoeuds(noeudsEnOrdre);
			return trajet;
		}
		return null;

	}

	/**
	 * Methode qui permet de calculer le chemin le plus court en utilisant
	 * l'algorithme de Dijkstra. Cette methode stocke les noeuds correspondant au
	 * chemin le plus court dans l'attribut NoeudCheminPlusCourt (ArrayList) de sa
	 * classe
	 * 
	 * @param depart
	 *            le point de depart
	 * @param destination
	 *            le point d'arriver
	 */
	public void calculCheminCourt(Noeud depart, Noeud destination) {
		
		Noeud courantTempo = depart;
		Noeud tempoVisite = null;

		depart.setLongueurChemin(0); // la distance du noeud de depart = 0
		depart.setStatu(true); // son statu est permanent
		
		//for(int i = 0; i < 100; i++) {// Enlever apres le test 

		/**
		 * On arrete l'algorithme si tout les noeuds du graphe sont a permanent ou si
		 * tout les noeuds temporaires restant on un chemin infini
		 */
		while (true) {
			/**
			 * 
			 */
			if (arretAlgo1() || arretAlgo2())
				break;
			
			//System.out.println("Le noeud courant est : " + courantTempo.toString() + " et ses liens avec les noeud voisin sont : ");
			//System.out.println("Le predecesseur du noeud courant est : " + courantTempo.getPredecesseur());
			//System.out.println(cpt++ + "eme fois");
			/**
			 * Visiter tout les noeud adjacent au noeud courant et change leur labels s'il
			 * le faut
			 */
			
			for (Lien lien : courantTempo.getVoisins()) {
				try {

					if (lien.getNoeudUn().getNom().equals(courantTempo.getNom())) {
						if (!lien.getNoeudDeux().isStatu()) {
							tempoVisite = lien.getNoeudDeux(); // si le 1er noeud de lien = noeud courant on recupere le
																// 2eme noeud si celui ci a un statu temporaire
						}else continue;

					} else {
						if (!lien.getNoeudUn().isStatu()) {
							tempoVisite = lien.getNoeudUn(); // sinon on recupere le 1er noeud si celui ci a un statu
																// temporaire
						}else continue;
					}

					/**
					 * determiner si le noeud adjacent a une distance plus grand que celle du noeud
					 * courant + le poid du lien et si il est temporaire. Si oui, change les labels
					 * du noeud adjacent.
					 */
					if (courantTempo.getLongueurChemin() + lien.getPoid() * lien.getCongestion() < tempoVisite.getLongueurChemin()
							&& !tempoVisite.isStatu()) {
						tempoVisite.setLongueurChemin(courantTempo.getLongueurChemin() + lien.getPoid());
						tempoVisite.setPredecesseur(courantTempo.getNom());
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

			} // ------------------fin du for------------------------------
			
			/**
			 * chercher le noeud qui a le plus petit chemin parmi tout les noeuds du graphe
			 * c'est lui qui devient le noeud courant
			 */
			
			courantTempo = this.noeudCheminCourt(tempoVisite);
			courantTempo.setStatu(true);
			
			/*for(Entry<String, Noeud> noeud : noeuds.entrySet()) {
				System.out.println(noeud.getValue().toString());
			}*/
			
		} // -------------------------fin du while---------------------------
			
		//}//

		/**
		 * On extrait les noeuds appartenant au chemin le plus court et on les stocke
		 * dans l'attribut noeudCheminPlusCourt
		 */
		cheminPlusCourt(depart, destination);

	}

	/**
	 * Methode qui cherche le noeud qui a le plus petit chemin parmis tout les noeud
	 * du graphe et le stocke dans l'attribut tempoPlusPetit
	 */

	private Noeud noeudCheminCourt(Noeud tempoDernierVisite) { // faire la recherche du plus petit parmi tout les noeuds du graphe
		Point point = new Point(0, 0);
		Noeud tempoPlusPetit;
		if(tempoDernierVisite.isStatu()) {
			tempoPlusPetit = new Noeud("", point);
		}else {
			tempoPlusPetit = tempoDernierVisite;
		}
		for (Entry<String, Noeud> noeud : noeuds.entrySet()) {
			if (noeud.getValue().getLongueurChemin() < tempoPlusPetit.getLongueurChemin()
					&& !noeud.getValue().isStatu()) {
				tempoPlusPetit = noeud.getValue();
			}
		}
		//System.out.println("Le noeud qui le plus court chemin : " + tempoPlusPetit.toString());
		//System.out.println();
		//System.out.println("Le noeud courant temporaire est : " + tempoPlusPetit.toString());
		return tempoPlusPetit;
	}

	/**
	 * Methode qui parcour tout le talbeau contenant les noueds du graphe et verifie
	 * si tout les noeuds sont permanent i.e on arrete l'algo
	 * 
	 * @return true si tout les noeuds sont permanent
	 */
	private boolean arretAlgo1() {
		boolean res = false;
		int nbrElement = 0;
		for (Entry<String, Noeud> noeud : noeuds.entrySet()) {
			if (noeud.getValue().isStatu())
			{
				nbrElement++;
			}

		}
		if (nbrElement == noeuds.size()) {
			res = true;
			System.out.println("le nombre d'element dans MapNoueds : " + noeuds.size() + " variable de controle : " + nbrElement);
		}
		return res;
	}

	/**
	 * Methode qui parcour tout le talbeau contenant les noueds du graphe et verifie
	 * si tout les noeuds temporaire qui reste ont une distance infinie i.e on
	 * arrete l'algo
	 * 
	 * @return true si tout les noeuds temporaire qui reste ont une distance infinie
	 */
	private boolean arretAlgo2() {
		boolean res = false;
		int nbrElement = 0;
		for (Entry<String, Noeud> noeud : noeuds.entrySet()) {
			if (noeud.getValue().getLongueurChemin() == Double.MAX_VALUE && !noeud.getValue().isStatu()) {
				nbrElement++;
			}	
		}
		if (nbrElement == noeuds.size()) {
			res = true;
			System.out.println("1 le nombre d'element dans MapNoueds : " + noeuds.size() + " 1 variable de controle : " + nbrElement);
		}
			
		return res;
	}

	/**
	 * Methode qui permet d'extraire les noeuds appartenant au chemin le plus court
	 * 
	 * @param depart
	 *            point de depart
	 * @param destination
	 *            point d'arriver
	 */
	private void cheminPlusCourt(Noeud depart, Noeud destination) {
		Noeud destinationTempo = destination;
		if (destinationTempo.getPredecesseur().equals(depart.getNom())) {
			noeudCheminPlusCourt.add(destinationTempo);
			noeudCheminPlusCourt.add(this.getNoeud(depart.getNom()));
		} else {
			noeudCheminPlusCourt.add(destinationTempo);
			destinationTempo = this.getNoeud(destination.getPredecesseur());
			cheminPlusCourt(depart, destinationTempo);// appel recursive
		}
	}

}
