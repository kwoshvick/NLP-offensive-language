package com.company;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;

import java.io.*;
import java.util.ArrayList;

public class pos_tagger {

    public static MaxentTagger mt = new MaxentTagger("/home/kwoshvick/Documents/stanford-postagger-2017-06-09/models/english-left3words-distsim.tagger");

    public static void writeToFile(String text, String writeFileName) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File(writeFileName), true));
            String tagged = mt.tagTokenizedString(text);
            ArrayList<String> wordArrayList = new ArrayList();
            for (String word : tagged.split("/")) {
                word = word.replaceAll("([a-zA-Z]+)_", "");
                wordArrayList.add(word);
            }
            StringBuilder builder = new StringBuilder();
            for (String s : wordArrayList) {
                builder.append(s);
                bw.write(s);
                bw.newLine();
            }
            bw.close();
        } catch (Exception e) {
        }
    }

    public static void readFile(String readFileName, String writeFileName){
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(readFileName));
            String line = reader.readLine();
            while (line != null) {
                writeToFile(line,writeFileName);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws Exception {
        String read_file_name = "/home/kwoshvick/IdeaProjects/NLP-offensive-language/src/com/company/sexist.txt";
        String write_file_name = "/home/kwoshvick/IdeaProjects/NLP-offensive-language/src/com/company/pos_sexist.txt";
        String read_file_name2 = "/home/kwoshvick/IdeaProjects/NLP-offensive-language/src/com/company/non-sexist.txt";
        String write_file_name2 = "/home/kwoshvick/IdeaProjects/NLP-offensive-language/src/com/company/pos_non-sexist.txt";
        readFile(read_file_name,write_file_name);
        readFile(read_file_name2,write_file_name2);

    }
}
