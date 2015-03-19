package br.com.six2six.bfgex.interpreter;

import static org.apache.commons.lang.ObjectUtils.defaultIfNull;

import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberRange;

import br.com.six2six.bfgex.RandomGen;

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
        return quantity != null && quantity.equals("?") ? RandomGen.pickOne("", pickValue(value, 1)) : null;
    }

    private String verifyGreedy(Object value) {
        return quantity != null && quantity.matches("[+*]") ? pickValue(value, null) : null;
    }
    
    private String verifyNumberRange(Object value) {
    	Integer numberValue = null;
    	if (numberRange != null ) {
    		numberValue = RandomGen.pickRange(numberRange).intValue(); 
    	} else if (number != null) {
    		numberValue = number;
    	}
        return numberValue != null ? pickValue(value, numberValue): null;
    }
    
    private String pickValue(Object value, Integer length) {
        String result = null;

        if (value instanceof String) {
			result = (String) StringUtils.repeat((String) value, length);
        } else if (value instanceof String[]) {
            StringBuilder sb = new StringBuilder();
            
            for (int i = 0; i < length; i++) {
                sb.append(RandomGen.pickArray((String[]) value));
            }
            
            result = sb.toString();
        } else {
            switch ((RandomLiteral) value) {
            case DIGIT: result = RandomGen.pickDigits(length);
                break;
            case SPACE: result = RandomGen.pickWhiteSpaces(length);
                break;
            case WORD: result = RandomGen.word(length);
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
