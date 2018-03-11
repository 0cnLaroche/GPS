package modele;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSV {
	
	private static ArrayList list;
	
	private static void load(String fileName) {

        String line = null;

        try {
            FileReader fileReader = 
                new FileReader(fileName);

            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                list.add(parseLine(line));
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
	private static String[] parseLine(String line){
		
		line.replaceAll("\"", "");
		String[] row = line.split(",");
		
		return row;
	}
	
	public CSV(String filePath){
		list = new ArrayList();
		load(filePath);
		
	}
	public String getFieldName(int index){
		
		String[] r = (String[]) list.get(1);
		String name = r[index];
		return name;
	}
	public void print(){
		list.toString();
	}
}
