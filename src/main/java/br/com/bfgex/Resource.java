package br.com.bfgex;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Resource {

    public static Map<Integer, Set<String>> of(String resource) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Map<Integer, Set<String>> resourceItemsLengthMap = new HashMap<Integer, Set<String>>();
        try {
            Scanner sc = new Scanner(new File(URLDecoder.decode(classLoader.getResource(resource).getPath(), "UTF-8")));
            
            while (sc.hasNext()) {
                String element = sc.next();
                
                if (!resourceItemsLengthMap.containsKey(element.length())) {
                    resourceItemsLengthMap.put(element.length(), new HashSet<String>());
                }
                resourceItemsLengthMap.get(element.length()).add(element);
            }
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("invalid file", e);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("invalid file", e);
        }
        
        return resourceItemsLengthMap;
    }
}
