/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company;


import java.util.*;

public class N_grams {

    
    public static List<String> ngrams(int n, String str) {
        List<String> ngrams = new ArrayList<String>();
        String[] words = str.split(" ");
        for (int i = 0; i < words.length - n + 1; i++)
            ngrams.add(concat(words, i, i+n));
        return ngrams;
    }

    public static String concat(String[] words, int start, int end) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < end; i++)
            sb.append(i > start ? " " : "").append(words[i]);
        return sb.toString();
    }

    public static void main(String[] args) {
       
        for (int n = 2; n <= 2; n++) {
            
            for (String ngram : ngrams(n, "I am called Lwanga Victor Kwome And I am very grateful"))
                System.out.println(ngram);
            System.out.println();
        }
    }
}
