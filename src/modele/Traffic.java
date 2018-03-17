package modele;

public class Traffic extends Evenements{
	
	private String nomRouteTraffic;
	
	public Traffic(String nomRouteTraffic) {
		//super(localisationEvent);
		this.nomRouteTraffic = nomRouteTraffic;
	}
	
	public void placerAccident(String nomRouteAccident) {
		/**
		 * dessine une croix par exemple sur la route accidentee et
		 * l'enlever de la List des routes du graphe	
		 */
	}

}
