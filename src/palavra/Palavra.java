package palavra;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Palavra {
    public static void main(String[] args) {
        int[] histogram = new int[65536];
        String letters = "toinovébânum";
        int wordSize = 6;
        
        try {
            for(int i = 0; i < letters.length(); i++) {
                histogram[letters.charAt(i)]++;
            }
            
            File file = new File("wordlist-preao-20170814.txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "ISO-8859-1"));
            String line;
            
            while((line = bufferedReader.readLine()) != null) {
                if(line.length() == wordSize) {
                    String lowerCaseWord = line.toLowerCase();
                    boolean failed = false;
                    
                    for(int i = 0; i < lowerCaseWord.length(); i++) {
                        histogram[lowerCaseWord.charAt(i)]--;
                        if(histogram[lowerCaseWord.charAt(i)] < 0) {
                            failed = true;
                        }
                    }
                    for(int i = 0; i < lowerCaseWord.length(); i++) {
                        histogram[lowerCaseWord.charAt(i)]++;
                    }
                    
                    if(!failed) {
                        System.out.println(lowerCaseWord);
                    }
                }
            }
            
            bufferedReader.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
}