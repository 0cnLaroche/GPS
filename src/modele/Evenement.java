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
	
	private double congestion;
	private LocalDateTime time;
	
	public Evenement(){
		this.setCongestion(NULL); //Par défault la congestion est nulle
	}
	public Evenement(double congestion){
		this.setCongestion(congestion);
		this.time = LocalDateTime.now();
	}
	
	public double getCongestion(){
		return this.congestion;
	};
	
	public void setCongestion(double level){
		congestion = level;
	};

}
