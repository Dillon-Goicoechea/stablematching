import java.util.ArrayList;
import java.io.BufferedReader;  
import java.io.FileReader;  
import java.io.IOException;   

public class StableMatching{
    String name = null;
    ArrayList<String> partnerPriority;


    public static ArrayList<StableMatching> readPeople(String filenameString){
        ArrayList<StableMatching> filePeople = new ArrayList<StableMatching>();
        String dataline = ""; 
        String splitChar = ",";  
        try   
        {  
        BufferedReader br = new BufferedReader(new FileReader(filenameString)); 
        while ((dataline = br.readLine()) != null)  
        {  
        String[] personData = dataline.split(splitChar);  
        StableMatching tempObj = new StableMatching();
        tempObj.name=personData[0];
        for(int i=1; i<=5;i++){
        tempObj.partnerPriority.add(personData[i]);
        }
        filePeople.add(tempObj);
        } 
        br.close(); 
        }   
        catch (IOException e)   
        {  
        e.printStackTrace();  
        }  
        
        return filePeople;
    }
    public static void main(String[] args){
        ArrayList<StableMatching> men = readPeople("men.csv"); 
        ArrayList<StableMatching> women = readPeople("women.csv");
        

    }
}