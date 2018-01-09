package com.company;
/* Bi-grams **** 
get a string of test data. 
for each string (ts input by a user command input) we want to 
get the n prob of belonging to the training data (s[] from a text)set import java.util.regex.*/
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.*;

public class Example06 {
	public static void main(String args[]) throws IOException {

		Scanner scanner = new Scanner(new InputStreamReader(System.in));
		System.out.println("Please enter your input: ");
		String ts = (scanner.nextLine()).toLowerCase();
		String  w[] = ts.split(" ");
		
		String s[] = new String[100];  
		int cs=0;
		BufferedReader br = new BufferedReader(new FileReader("/home/kwoshvick/IdeaProjects/NLP-offensive-language/src/com/company/tr06.txt"));
		String sx = null;
		while ((sx = br.readLine()) != null) {
			s[cs]=sx.toLowerCase();
			cs++;           

		}

		


		double cg=1;
		for(int i=0;i<w.length-1;i++)
		{
			Pattern p1 = Pattern.compile(w[i]);
			Pattern p = Pattern.compile(w[i]+" "+w[i+1]);
			double c1=0;
			double c=0;             
			for(int j=0;j<cs;j++)
			{   
				Matcher m1 = p1.matcher(s[j]);
				Matcher m = p.matcher(s[j]);
				if(m1.find())
				{
					c1=c1+1;
				}
				if(m.find())
				{
					c=c+1;
				}	
			}
		
			double pc=(Double)(c/c1); 
		
			cg*=pc;
		
		}
		System.out.println(" Final prob= "+cg);
	}
}
