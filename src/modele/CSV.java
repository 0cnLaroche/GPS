package modele;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class CSV extends ArrayList<ArrayList>{
	
	/**Pour importer des fichiers .csv 
	 * @author Samuel
	 */
	
	//private static ArrayList<ArrayList> list;
	
	private void load(String fileName) { //Lit le fichier .csv et remplis l'objet list
		
        String line = null;

        try {
            FileReader fileReader = 
                new FileReader(fileName);

            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                //System.out.println(line);
                this.add(parseLine(line));
            }   

            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
	}
	private static ArrayList<String> parseLine(String line){
		
		line.replaceAll("\"", ""); //Retire les "
		String[] array = line.split(","); //chaque champ est séparé par une virgule
		ArrayList<String> row = new ArrayList<String>(Arrays.asList(array)); //Convert Array to ArrayList
		
		return row;
	}
	
	public CSV(String filePath){
		super();
		//list = new ArrayList();
		load(filePath);
		
	}
	public String getFieldName(int index){
		/**Obtenir le nom d'un champ
		 * @param index Représente le numéro de colonne
		 * @author Samuel
		 */
		String name = (String) this.get(1).get(index);
		return name;
	}
	public void print(){
		/**
		 * Imprime le ficher dans la console
		 */
		for (int i=0;i<this.size();i++){
			String line = "";
			ArrayList row = this.get(i);
			for (int j=0;j<row.size();j++){
				line += row.get(j)+",";
			}
			System.out.println(line);
		}
		//Tested
	}
}
