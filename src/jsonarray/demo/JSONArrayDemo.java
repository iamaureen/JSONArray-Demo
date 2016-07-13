/*
Amazon Interview Question July, 2016
https://www.careercup.com/question?id=5633477589336064
 */
package jsonarray.demo;

import java.io.*;
import java.util.*;
import org.json.*;

/**
 *
 * @author Ishrat
 */
public class JSONArrayDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, JSONException {
        // TODO code application logic here
     FileInputStream fstream = new FileInputStream("input.json");
     DataInputStream in = new DataInputStream(fstream);
     BufferedReader br = new BufferedReader(new InputStreamReader(in));
     String s, scores="";
     while((s=br.readLine())!=null){
        scores = scores + s;
     }
     in.close();
     //get the hotel_ids whose score is equal or greater than 5
     get_hotel(scores,5);
    }
    
    private static void get_hotel(String scores, int min_avg_score) throws JSONException {
       
        TreeSet hotelId = new TreeSet<Integer>();
        
        final HashMap<Integer, pair> countMap=new HashMap<Integer, pair>();
        JSONArray arr = new JSONArray(scores);
        
        for (int i=0; i<arr.length(); i++){
        JSONObject obj = arr.getJSONObject(i);
        //get hotel id and score
        int id=obj.getInt("hotel_id");
        int score=obj.getInt("score");
        
        //check for hotel id, if not exists add       
        if(!countMap.containsKey(id)){
            pair pair_score = new pair();
            pair_score.setTotal_score(score);
            pair_score.setCount(1);
           
            countMap.put(id, pair_score);
             
        }
        //if exists add the score to the previous one
        else{
           pair temp=countMap.get(id);
           int temp_score=temp.getTotal_score();
           int temp_count=temp.getCount();

           temp_score= temp_score + score;
           temp_count++;
           pair p=new pair(temp_score,temp_count);
           countMap.put(id, p);
            
        }        
        
      }
        for(int key: countMap.keySet()){
            
            pair p = (pair)countMap.get(key);
            int avg=(int) Math.ceil(p.getTotal_score()/ p.getCount());
            if(avg>=min_avg_score){
                hotelId.add(key);
            }
        }
     
         System.out.println(hotelId);
    }
    
}

class pair{

    int total_score;
    int count;
    
    pair() {}
    
    public pair(int total_score, int count) {
        this.total_score = total_score;
        this.count = count;
    }
    

    public int getTotal_score() {
        return total_score;
    }

    public void setTotal_score(int total_score) {
        this.total_score = total_score;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
