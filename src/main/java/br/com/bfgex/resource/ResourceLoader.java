package br.com.bfgex.resource;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class ResourceLoader {

    public static Map<Integer, Set<String>> of(String resource) {
        Map<Integer, Set<String>> resourceItemsLengthMap = new HashMap<Integer, Set<String>>();
        Scanner sc = new Scanner(ResourceLoader.class.getResourceAsStream("/" + resource), "UTF-8");
        
        while (sc.hasNext()) {
            String element = sc.next();
            
            if (!resourceItemsLengthMap.containsKey(element.length())) {
                resourceItemsLengthMap.put(element.length(), new HashSet<String>());
            }
            resourceItemsLengthMap.get(element.length()).add(element);
        }
        
        return resourceItemsLengthMap;
    }
    
}
