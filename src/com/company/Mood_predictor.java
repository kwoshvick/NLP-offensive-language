package com.company;

//import static email_cleaner.N_grams.ngrams;
import com.company.N_grams.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import java.util.ArrayList;
import java.util.Arrays;
import javax.xml.bind.annotation.XmlElementDecl;

public class Mood_predictor {

        public static List<String> bigrams(int n, String str) {
        List<String> bigram = new ArrayList<String>();
        String[] words = str.split(" ");
        for (int i = 0; i < words.length - n + 1; i++)
            bigram.add(concat(words, i, i+n));
        return bigram;
    }

    public static String concat(String[] words, int start, int end) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < end; i++)
            sb.append(i > start ? " " : "").append(words[i]);
        return sb.toString();
    }
    
    
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // TODO code application logic here
        System.out.print("Please enter words: ");
        Scanner scan = new Scanner(System.in);
        String myinput = scan.nextLine();
    
        MaxentTagger mt = new MaxentTagger("/home/kwoshvick/Documents/stanford-postagger-2017-06-09/models/english-left3words-distsim.tagger");
         
        // The tagged string
        String tagged = mt.tagTokenizedString(myinput);
     
        ArrayList<String> wordArrayList = new ArrayList();
        for(String word : tagged.split("/")) {
          word = word.replaceAll("([a-zA-Z]+)_", "");
            wordArrayList.add(word);
        }

        StringBuilder builder = new StringBuilder();
        for( String s : wordArrayList) {
            builder.append(s);

        myinput = s;
            
        
        float count = 0;
        float count1 = 0;
        float count2 = 0;
        float count3 = 0;
        float prob_goodbox = 0;
        float prob_badbox = 0;
        float prob_positive = 0;
        float prob_bad = 0;
         String text = null;
        String text2 = null;
        List final_prob = new ArrayList<Float>();
        List final_prob1 = new ArrayList<Float>();
                   
          for (int n = 2; n <= 2; n++) {
            
            for (String ngram : N_grams.ngrams(n, myinput)){
        String arr[] = ngram.split(" ", 1);
        
        String out_bigrams = arr[0];
        
        String arr2[] = out_bigrams.split(" ",2);
        
        String REGEX = "\\b"+out_bigrams+"\\b";
        String REGEX1 = "\\b"+arr2[0]+"\\b";
        
         System.out.println("Regex1"+REGEX);
         System.out.println("***************");
         System.out.println("Regex2"+REGEX1);
         System.out.println("***************");
               
//        Scanner std = new Scanner(new FileReader("/home/kwoshvick/negative-pos.txt"));
        Scanner std = new Scanner(new FileReader("/home/kwoshvick/s-ps.txt"));
        Scanner std2 = new Scanner(new FileReader("/home/kwoshvick/s-ns.txt"));
//        Scanner std2 = new Scanner(new FileReader("/home/kwoshvick/positive-pos.txt"));
        Pattern p = Pattern.compile(REGEX);
        Pattern p1 = Pattern.compile(REGEX1);
        
        while (std.hasNextLine()) {
        text = std.nextLine();
   
            Matcher m = p.matcher(text);
            Matcher m1 = p1.matcher(text);

            while (m.find()) {
                count++;
                       
            }
            while (m1.find()){
                count1++;
         
            }
        }
        
        while (std2.hasNextLine()) {
         text2 = std2.nextLine();
   
            Matcher m2 = p.matcher(text2);
            Matcher m3 = p1.matcher(text2);

            while (m2.find()) {
                count2++;
               
            }
            
            while (m3.find()){
                count3++;
                
            }
        }
        
        if(count3==0){
            prob_goodbox = 0;
        }else{
         prob_goodbox = count2/count3;
         final_prob.add(prob_goodbox);
        }
        if(count1==0){
            prob_badbox = 0;
           
        }else{
         prob_badbox = count/count1;
         final_prob1.add(prob_badbox);
        }
            
        //Multiply the probabilities
 
            float x = 1;
          for (int i = 0; i < final_prob1.size(); i++) {
              x *= (float) final_prob1.get(i);

          }
         prob_bad = x;
       
           float y = 1;
          for (int i = 0; i < final_prob.size(); i++) {
              y *= (float) final_prob.get(i);
          }
          prob_positive = y;
            }
                             
//        Show the probabilities for each corpus
        System.out.println("");
        System.out.println(" Probability of bigram that is Positive mood = " + prob_positive);
        System.out.println("¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬");
        System.out.println(" Probability of bigram that is Negative mood = " + prob_bad);
        System.out.println("¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬");
        
//        Print out where the input phrase might belong to based on highesst probability
        if(prob_positive>0&&prob_positive>prob_bad){
            System.out.println("The phrase with POS `"+myinput + "`" + " BELONGS TO POSITIVE MOOD");
        } else if(prob_positive==prob_bad){
            System.out.println("The phrase with POS `"+myinput + "`" + " BELONGS TO EITHER POSITIVE OR NEGATIVE MOOD");
        } else{
             System.out.println("The phrase POS `"+myinput + "`"  + " BELONGS BELONGS TO NEGATIVE MOOD");
        }

            }
            
    }

    }}
    




