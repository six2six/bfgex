package br.com.bfgex;

import static org.apache.commons.lang.ObjectUtils.defaultIfNull;

import java.util.regex.Pattern;

import org.apache.commons.lang.math.NumberRange;

public class Quantifier {
    
    private static Pattern QUANTIFIERS = Pattern.compile("[+?*]");

    private NumberRange numberRange;
    
    private Integer number;
    
    private String quantity;
    
    public Quantifier(NumberRange numberRange) {
        this.numberRange = numberRange;
    }
    
    public Quantifier(Integer number) {
    	this.number = number;
    }
    
    public Quantifier(String quantity) {
        if (!QUANTIFIERS.matcher(quantity).find()) {
            throw new IllegalArgumentException("invalid quantifier");
        }
        this.quantity = quantity;
    }

    public String genValue(Object value) {
        return (String) defaultIfNull(verifyReluctant(value), defaultIfNull(verifyGreedy(value), verifyNumberRange(value))); 
    }
    
    
    private String verifyReluctant(Object value) {
        return quantity != null && quantity.equals("?") ? Randgen.pickOne("", pickValue(value, 1)) : null;
    }

    private String verifyGreedy(Object value) {
        return quantity != null && quantity.matches("[+*]") ? pickValue(value, null) : null;
    }
    
    private String verifyNumberRange(Object value) {
    	Integer numberValue = null;
    	if (numberRange != null ) {
    		numberValue = Randgen.pickRange(numberRange).intValue(); 
    	} else if (number != null) {
    		numberValue = number;
    	}
        return numberValue != null ? pickValue(value, numberValue): null;
    }
    
    private String pickValue(Object value, Integer length) {
        String result = null;

        if (value instanceof String) {
			result = (String) value;
        } else {
            switch ((RandomLiteral) value) {
            case DIGIT: result = Randgen.pickDigits(length);
                break;
            case SPACE: result = Randgen.pickWhiteSpaces(length);
                break;
            case WORD: result = Randgen.word(length);
                break;
            }
        }
        return result;
    }

	@Override
	public String toString() {
		return defaultIfNull(quantity, defaultIfNull(number, numberRange)).toString();
	}
 }
