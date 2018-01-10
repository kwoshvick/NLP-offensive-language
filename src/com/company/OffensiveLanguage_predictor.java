package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.*;

public class OffensiveLanguage_predictor {
    public static void main(String args[]) throws IOException {

        OffensiveLanguage_predictor op = new OffensiveLanguage_predictor();

        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        System.out.println("Please enter your input: ");
        String ts = (scanner.nextLine()).toLowerCase();
        String w[] = ts.split(" ");
//        System.out.print(Arrays.toString(w));
        String s[] = new String[100];
        String sn[] = new String[100];
        int cs = 0;
        BufferedReader br = new BufferedReader(new FileReader("/home/kwoshvick/IdeaProjects/NLP-offensive-language/src/com/company/tr07_p.txt"));
        String sx = null;
        while ((sx = br.readLine()) != null) {
            s[cs] = sx.toLowerCase();
            cs++;
        }
        int csn = 0;
        br = new BufferedReader(new FileReader("/home/kwoshvick/IdeaProjects/NLP-offensive-language/src/com/company/tr07_n.txt"));
        sx = null;
        while ((sx = br.readLine()) != null) {
            sn[csn] = sx.toLowerCase();
            csn++;

        }
        double cg1 = op.compute_bigram(s, cs, w);
        double cg2 = op.compute_bigram(sn, csn, w);
        System.out.println("Positive: \t" + cg1);
        System.out.println("Negative: \t" + cg2);

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
