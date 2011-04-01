package br.com.bfgex;

import java.util.regex.Pattern;

public class Randgen {
    
    public static Pattern LENGTH_PATTERN = Pattern.compile("\\{(\\d+)(\\,)?(\\d+)?\\}");
    
    
    public static String word(String pattern) {
        String result = "";
            if (pattern.matches("\\{\\d+\\}")) {
                
            }    
        return result;
    }
    
    public static void main(String[] args) {
        System.out.println();     
    }

    
    
}
