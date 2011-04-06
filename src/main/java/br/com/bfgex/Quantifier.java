package br.com.bfgex;

import static org.apache.commons.lang.ObjectUtils.defaultIfNull;

import java.util.regex.Pattern;

import org.apache.commons.lang.math.NumberRange;

public class Quantifier {
    
    private static Pattern QUANTIFIERS = Pattern.compile("[+?*]");

    private NumberRange numberRange;
    
    private String quantity;
    
    public Quantifier(NumberRange numberRange) {
        this.numberRange = numberRange;
    }
    
    public Quantifier(String quantity) {
        if (!QUANTIFIERS.matcher(quantity).find()) {
            throw new IllegalArgumentException("invalid quantifier");
        }
        this.quantity = quantity;
    }
    
    
    public String verifyReluctant(Literal literal) {
        return quantity != null && quantity.equals("?") ? Randgen.pickOne("", getLiteralValue(literal, 1)) : null;
    }

    public String verifyGreedy(Literal literal) {
        return quantity != null && quantity.matches("[+*]") ? getLiteralValue(literal, null) : null;
    }
    
    public String verifyRange(Literal literal) {
        return numberRange != null ? getLiteralValue(literal, Randgen.pickRange(numberRange).intValue()): null;
    }
    
    public String getGeneratedValue(Literal literal) {
        return (String) defaultIfNull(verifyReluctant(literal), defaultIfNull(verifyGreedy(literal), verifyRange(literal))); 
    }
    
    public String getGeneratedValue(Object value) {
        return value instanceof Literal ? getGeneratedValue((Literal) value) : null; 
    }
    
    private String getLiteralValue(Literal literal, Integer length) {
        String result = null;
        
        switch (literal) {
        case DIGIT: result = Randgen.pickDigits(length);
            break;
        case SPACE: result = Randgen.pickWhiteSpaces(length);
            break;
        case WORD: result = Randgen.word(length);
            break;
        }
        
        return result;
    }
    
}
