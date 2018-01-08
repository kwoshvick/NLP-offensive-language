/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company;


import java.io.IOException;
 
import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import java.util.ArrayList;
import java.util.Arrays;
import javax.xml.bind.annotation.XmlElementDecl;


public class Taggers {
    
 
    public static void main(String[] args) throws IOException,
            ClassNotFoundException {
        MaxentTagger mt = new MaxentTagger("/home/kwoshvick/Documents/stanford-postagger-2017-06-09/models/english-left3words-distsim.tagger");
        String sample = "I am victor";
        String tagged = mt.tagTokenizedString(sample);
        

ArrayList<String> wordArrayList = new ArrayList();
for(String word : tagged.split("/")) {
  word = word.replaceAll("([a-zA-Z]+)_", "");
    wordArrayList.add(word);
}


StringBuilder builder = new StringBuilder();
for( String s : wordArrayList) {
    builder.append(s);


System.out.println("\n");
//        System.out.println(Arrays.toString(wordArrayList));
        System.out.println(s);
    }

}}

    

