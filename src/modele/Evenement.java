package modele;

public class Evenement {
	
	public static final float NULL = 1;
	public static final float LOW = (float) 1.25;
	public static final float MEDIUM = (float) 1.5;
	public static final float HIGH = 2;
	public static final float VERYHIGH = 3;
	public static final float FULL = 0;
	
	private float congestion;
	
	public Evenement(){
		this.setCongestion(NULL);
	}
	
	public double getCongestion(){
		return this.congestion;
	};
	
	public void setCongestion(float level){
		congestion = level;
	};

}
