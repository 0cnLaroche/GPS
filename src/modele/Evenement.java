package modele;

import java.time.LocalDateTime;

public class Evenement {
	//Les constantes représente un facteur de congestion.
	public static final float NULL = 1;
	public static final float LOW = (float) 1.25;
	public static final float MEDIUM = (float) 1.5;
	public static final float HIGH = 2;
	public static final float VERYHIGH = 3;
	public static final double FULL = Double.MAX_VALUE; //Ou infini. Le liens sera toujours écarter par Djikstra
	//Constantes type d'evenement
	public static final String TRAFFIC = "traffic";
	public static final String ACCIDENT = "accident";
	
	private double congestion;
	private String type;
	private LocalDateTime time;
	
	public Evenement(){
		this.setCongestion(NULL); //Par défault la congestion est nulle
		this.time = LocalDateTime.now();
	}
	public Evenement(String type, double congestion){
		this.type = type;
		this.setCongestion(congestion);
		this.time = LocalDateTime.now();
	}
	
	public double getCongestion(){
		return this.congestion;
	};
	
	public void setCongestion(double level){
		congestion = level;
	};
	public void setTime(LocalDateTime time){
		this.time = time;
	}
	public LocalDateTime LocalDateTime(){
		return this.time;
	}
	public void getImage(){
		//retourner une image specifique au type d'evenement
		//TODO: implementer l'importation et le retour de l'image
		switch(type){
		case TRAFFIC : 
			break;
		case ACCIDENT : 
			break;
		default:
			break;
		}
	}
	public String getType(){
		return this.type;
	}
	public void setType(String type){
		this.type = type;
	}

}
