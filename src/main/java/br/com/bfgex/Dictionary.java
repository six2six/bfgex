package br.com.bfgex;

import java.util.Map;
import java.util.Set;

public class Dictionary {
    
    private static Map<Integer, Set<String>> WORDS_BY_LENGTH;
    
    public static Map<Integer, Set<String>> getWords() {
        if (WORDS_BY_LENGTH == null) {
            WORDS_BY_LENGTH = Resource.of("words");
        }
        return WORDS_BY_LENGTH;
    }
    
    public static Set<String> getWordsByLength(Integer length) {
        return getWords().get(length);
    }
}
