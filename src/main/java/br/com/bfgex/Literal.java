package br.com.bfgex;

import java.util.Map;
import java.util.TreeMap;

public enum Literal {
    WORD("w"),
    DIGIT("d"),
    SPACE("s");
    
    private static Map<String, Literal> mapByKey = new TreeMap<String, Literal>();
    
    static {
        for (Literal literal : Literal.values()) {
            Literal.mapByKey.put(literal.getKey(), literal);
        }
    }

    private String key;
    
    private Literal(String key) {
        this.key = key;
    }
    
    public String getKey() {
        return this.key;
    }
    
    public static Literal get(String key) {
        return Literal.mapByKey.get(key);
    }
    
    
}
