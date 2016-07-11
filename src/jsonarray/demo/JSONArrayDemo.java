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
    
    private static void get_hotel(String scores, int avg_score) throws JSONException {
       
        Set<Integer> id = new HashSet<Integer>();
        JSONArray arr = new JSONArray(scores);
        for (int i=0; i<arr.length(); i++){
        JSONObject jsonProductObject = arr.getJSONObject(i);
        int score = jsonProductObject.getInt("score");
        if(score>=avg_score){
            id.add(jsonProductObject.getInt("hotel_id"));
        }
      }
         //System.out.println(id);
         //if hotel_id needs to be sorted 
         TreeSet sortedId = new TreeSet<Integer>(id);
         System.out.println(sortedId);
    }
    
}
