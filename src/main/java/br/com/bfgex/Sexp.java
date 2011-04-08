package br.com.bfgex;

import java.util.Arrays;
import java.util.LinkedList;

import org.apache.commons.lang.StringUtils;

public class Sexp {
    private LinkedList<Object> expressions = new LinkedList<Object>();

    public Sexp(Exp exp) {
        expressions.add(exp);
    }
    
    public LinkedList<Object> getValues() {
        return expressions;
    }
    
    public Sexp add(Object value) {
        expressions.add(value);
        return this;
    }
    
    public Sexp addAll(Object[] arrayValue) {
        expressions.addAll(Arrays.asList(arrayValue));
        return this;
    }
    
    public Object first() {
        return expressions.size() > 0 ? expressions.get(0) : null;
    }
    
    public Object removeLast() {
        return expressions.removeLast();
    }

    public String reduce() {
    	System.out.println(this);
        return (String) reduce(this.getValues());
    }
    
    private Object reduce(LinkedList<Object> expressions) {
    	return this.reduce(expressions, null);
    }
    
    private Object reduce(LinkedList<Object> expressions, Quantifier quantity) {
        Object result = null;
        
        if (expressions.getFirst() instanceof Exp) {
            Exp operation = (Exp) expressions.removeFirst();
        	result = reduceExp(operation, expressions, quantity);
        } else if (expressions.getFirst() instanceof Sexp) {
        	result = reduce(((Sexp) expressions.getFirst()).getValues(), quantity);
        }
        
        return result;
    }
    
    private Object reduceExp(Exp exp, LinkedList<Object> expressions, Quantifier quantity) {
        Object result = null;
        switch (exp) {
        case UNION: 
        	result = genValue(StringUtils.join(mapReduce(expressions), ""), quantity);
        	break;
        case INTERSECTION: 
        	result = genValue(Randgen.pickCollection(mapReduce(expressions)), quantity); 	
        	break;
        case RANDOM:
        case LITERAL: result = genValue(expressions.get(0), quantity);
        	break;
        case QUANTIFY:
        	quantity = (Quantifier) expressions.removeLast(); 
        	result = reduce(expressions, quantity);
        	break;
        }

        return result;
    }
    
    private String genValue(Object reducedValue, Quantifier quantity) {
    	return quantity != null ? quantity.genValue(reducedValue) : (String) reducedValue;
    }
    
    private LinkedList<Object> mapReduce(LinkedList<Object> expressions) {
    	LinkedList<Object> mappedList = new LinkedList<Object>();
    	for (Object object : expressions) {
			mappedList.add(reduce(((Sexp) object).getValues()));
		}
    	return mappedList;
    }
    
    @Override
    public String toString() {
        return "(" + StringUtils.join(expressions, ",") + ")";
    }
}
