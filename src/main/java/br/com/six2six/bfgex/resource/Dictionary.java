package br.com.six2six.bfgex.resource;

import java.util.Map;
import java.util.Set;

public class Dictionary {
    
    private static Map<Integer, Set<String>> WORDS_BY_LENGTH;
    
    private static Map<Integer, Set<String>> MALE_NAMES_BY_LENGTH;

    private static Map<Integer, Set<String>> FEMALE_NAMES_BY_LENGTH;

    private static Map<Integer, Set<String>> LAST_NAMES_BY_LENGTH;
    
    public static Set<String> getWordsByLength(Integer length) {
        if (WORDS_BY_LENGTH == null) {
        	WORDS_BY_LENGTH = ResourceLoader.of("words");
        }
    	return WORDS_BY_LENGTH.get(length);
    }

    public static Set<String> getMaleNameByLength(Integer length) {
        if (MALE_NAMES_BY_LENGTH == null) {
        	MALE_NAMES_BY_LENGTH = ResourceLoader.of("male_names");
        }
    	return MALE_NAMES_BY_LENGTH.get(length);
    }
    
    public static Set<String> getFemaleNameByLength(Integer length) {
        if (FEMALE_NAMES_BY_LENGTH == null) {
        	FEMALE_NAMES_BY_LENGTH = ResourceLoader.of("female_names");
        }
    	return FEMALE_NAMES_BY_LENGTH.get(length);
    }

    public static Set<String> getLastNameByLength(Integer length) {
        if (LAST_NAMES_BY_LENGTH == null) {
        	LAST_NAMES_BY_LENGTH = ResourceLoader.of("surnames");
        }
    	return LAST_NAMES_BY_LENGTH.get(length);
    }
}
