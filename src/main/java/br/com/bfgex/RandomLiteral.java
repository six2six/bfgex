package br.com.bfgex;

import java.util.Map;
import java.util.TreeMap;

public enum RandomLiteral {
    WORD("w"),
    DIGIT("d"),
    SPACE("s");
    
    private static Map<String, RandomLiteral> mapByKey = new TreeMap<String, RandomLiteral>();
    
    static {
        for (RandomLiteral literal : RandomLiteral.values()) {
        	RandomLiteral.mapByKey.put(literal.getKey(), literal);
        }
    }

    private String key;
    
    private RandomLiteral(String key) {
        this.key = key;
    }
    
    public String getKey() {
        return this.key;
    }
    
    public static RandomLiteral get(String key) {
        return RandomLiteral.mapByKey.get(key);
    }
    
    
}
