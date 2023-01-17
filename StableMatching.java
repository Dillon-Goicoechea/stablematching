import java.util.ArrayList;
import java.io.BufferedReader;  
import java.io.FileReader;  
import java.io.IOException;   

public class StableMatching{
    private String name = "";
    private ArrayList<String> partnerPriority = new ArrayList<String>();
    private String partner= "";

    public void updatePartner(String newPartner){
        partner=newPartner;
    }
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

    public static void pairPeople(ArrayList<StableMatching> men,ArrayList<StableMatching> women){
        int freeMen=5;

        while(freeMen >0){
            for(StableMatching woman:women){
                int indexOfCurrWoman = women.indexOf(woman);
                
                for(int i=0; i<5;i++){
                    if(woman.partner!=""){
                        break;
                    }
                    
                    StableMatching manTemp= new StableMatching();
                    for(StableMatching man:men){
                        if(man.name.equals(woman.partnerPriority.get(i))){
                            manTemp=man;
                            
                        }
                    }
                    int indexOfCurrMan=men.indexOf(manTemp);
                    
                    if(manTemp.partner == "" ){
                        
                        women.get(indexOfCurrWoman).updatePartner(manTemp.name);
                        woman.updatePartner(manTemp.name);
                        men.get(indexOfCurrMan).updatePartner(woman.name);
                        manTemp.updatePartner(woman.name);
                        freeMen--;
                    }
                    else{
                    
                        if(manTemp.partnerPriority.indexOf(woman.name)<manTemp.partnerPriority.indexOf(manTemp.partner)){
                        int indexofCurrPartner=0;
                        for(StableMatching womanTemp:women){
                            if (womanTemp.name == manTemp.partner){
                                indexofCurrPartner=women.indexOf(womanTemp);
                            }
                        }
                        StableMatching womanTemp= women.get(indexofCurrPartner);
                        womanTemp.updatePartner("");
                        women.get(indexOfCurrWoman).updatePartner(manTemp.name);
                        woman.updatePartner(manTemp.name);
                        men.get(indexOfCurrMan).updatePartner(woman.name);
                        manTemp.updatePartner(woman.name);
                        }
                    
                    }
                

                }
            }
        }

        for(StableMatching woman:women){
            System.out.println(woman.name +" & "+ woman.partner);
        }

    }

    public static void main(String[] args){
        ArrayList<StableMatching> men = readPeople("men.csv"); 
        ArrayList<StableMatching> women = readPeople("women.csv");
        pairPeople(men, women);
    }
}