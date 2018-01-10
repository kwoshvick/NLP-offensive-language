package com.company;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.*;

public class OffensiveLanguage_predictor {
//    public static MaxentTagger mt = new MaxentTagger("/home/kwoshvick/stanford-postagger-2017-06-09/models/english-left3words-distsim.tagger");


    public static String pos_converter(String text) {
//        try {
//            pubic static String z;
            MaxentTagger mt = new MaxentTagger("/home/kwoshvick/stanford-postagger-2017-06-09/models/english-left3words-distsim.tagger");


//            Scanner scanner = new Scanner(new InputStreamReader(System.in));
//            System.out.println("Please enter your input: ");
//            String text2 = (scanner.nextLine()).toLowerCase();

            String tagged = mt.tagTokenizedString(text);
            ArrayList<String> wordArrayList = new ArrayList();
            for (String word : tagged.split("/")) {
                word = word.replaceAll("([a-zA-Z]+)_", "");
                wordArrayList.add(word);
            }
            StringBuilder builder = new StringBuilder();


            for (String s : wordArrayList) {
                builder.append(s);
//            System.out.println(s);
            }

            String ts = builder.toString();

//            System.out.println(ts);

//        } catch (Exception e) {
//        }
//
        return ts;
    }

    public static void main(String args[]) throws IOException {

        OffensiveLanguage_predictor op = new OffensiveLanguage_predictor();

        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        System.out.println("Please enter your input: ");
        String ts = (scanner.nextLine());
//        System.out.println(ts);

        String m = pos_converter(ts);
//        System.out.println(m);



        String w[] = m.split(" ");
//        System.out.print(Arrays.toString(w));
        String s[] = new String[100];
        String sn[] = new String[100];
        int cs = 0;
        BufferedReader br = new BufferedReader(new FileReader("/home/kwoshvick/IdeaProjects/NLP-offensive-language/dataset/pos_non-offensive.txt"));
        String sx = null;
        while ((sx = br.readLine()) != null) {
            s[cs] = sx;
            cs++;
        }
        int csn = 0;
        br = new BufferedReader(new FileReader("/home/kwoshvick/IdeaProjects/NLP-offensive-language/dataset/pos_offensive.txt"));
        sx = null;
        while ((sx = br.readLine()) != null) {
            sn[csn] = sx;
            csn++;

        }
        double cg1 = op.compute_bigram(s, cs, w);
        double cg2 = op.compute_bigram(sn, csn, w);
        System.out.println("Probability that its not abusive/offensive phrase : \t" + cg1);
        System.out.println("Probability that its  abusive/offensive phrase: \t" + cg2);
        if(cg1>cg2){
            System.out.println(" not abusive/offensive phrase \t");
        }else {
            System.out.println("abusive/offensive phrase : \t" );
        }

    }


    public double compute_bigram(String[] s, int cs, String[] w) {
        double cg = 1;
        for (int i = 0; i < w.length - 1; i++) {
            Pattern p1 = Pattern.compile(w[i]);
            Pattern p = Pattern.compile(w[i] + " " + w[i + 1]);
            double c1 = 0;
            double c = 0;
            for (int j = 0; j < cs; j++) {
                Matcher m1 = p1.matcher(s[j]);
                Matcher m = p.matcher(s[j]);
                if (m1.find()) {
                    c1 = c1 + 1;
                }
                if (m.find()) {
                    c = c + 1;
                }
            }
            double pc = (Double) (c / c1);
            cg *= pc;
        }
        return cg;
    }

}
