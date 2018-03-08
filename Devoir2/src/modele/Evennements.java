package modele;

import java.util.ArrayList;

public class Evennements {
	
	private Liens localisationEvent;//pour avoir acces a la liste des noms des routes
	
	private String nomRouteEvent;
	
	private String nomButtonClicke;
	
	private ArrayList<String> nomRoute;
	
	public Evennements() {super();}
	
	public Evennements(String nomRouteEvent, String nomButtonClicke) {
		this.nomRouteEvent = nomRouteEvent;
		this.nomButtonClicke = nomButtonClicke;
	}
	
	public Evennements(Liens localisationEvent) {
		this.localisationEvent = localisationEvent;
	}	
	
	public void placerEvenement(String nomRouteAccident) { //change le type de cette methode pour elle renvoie un symbol???????
		/**
		 * chercher dans la liste nomRoute de la classe Liens le nom correspondant 
		 * au nom de la route saisi dans le textfield "routeAccident"
		 * 
		 * dessine une croix, par exemple, sur la route accidentee et
		 * l'enlever de la Liste des routes du graphe	
		 */
		
		nomRoute = localisationEvent.getNomRoute();
		
		for (int i = 0; i < nomRoute.size(); i++) {
			if(nomButtonClicke.equals("Générer un accident")) {
				if(nomRouteEvent.equals(nomRoute.get(i))) {
					/**
					 *renvoyer le symbol correspondant a un evennement de type accident
					 */
				}
			}else {
				if(nomRouteEvent.equals(nomRoute.get(i))) {
					/**
					 * renvoyer le symbol correspondant a un evennement de type traffic
					 */
				}
			}
		}
	}
}
