/* Create a java program that takes regular expression ( regex),
it then determines if a given sentence ( from the user input in a text file t.txt)  is a valid derivation of the regex
we use a static string s
*/

package com.company;

import java.util.*;
import java.util.regex.*;
import java.io.*;

    
public class Example04 {
    public static void main(String args[]) throws IOException {

    String rex = "f[o|0]+[l|1]";

           
          

        Pattern p = Pattern.compile(rex);
        BufferedReader br = new BufferedReader(new FileReader("/home/kwoshvick/IdeaProjects/NLP-offensive-language/src/com/company/t.txt"));
 	    String s = null;
	    while ((s = br.readLine()) != null) {

                  
            s = br.readLine();
            
           Matcher m = p.matcher(s);
           System.out.println(s+":\t is a valid word:\t"+m.matches());
         }
  }
}


/* File t.txt
fool
f00l
f000l
fool
foolish
f00lish 
*/

/* output
f00l:	 is a valid word:	true
fool:	 is a valid word:	true
f00lish :	 is a valid word:	false
*/
