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
        return (String) reduce(this.getValues(), null);
    }
    
    private Object reduce(LinkedList<Object> expressions, Quantifier quantity) {
        Object result = null;
        Object operation = expressions.removeFirst();
        
        if (operation instanceof Exp) {
            result = reduceExp((Exp) operation, expressions, quantity);
        }
        
        return result;
    }
    
    private Object reduceExp(Exp exp, LinkedList<Object> expressions, Quantifier quantity) {
        Object result = null;
        
        switch (exp) {
        case UNION:
            result = quantity != null ? quantity.getGeneratedValue(null) : null;
            break;

        default:
            break;
        }
        
        return result;
    }
    
    private Object mapReduce(LinkedList<Object> items) {
        LinkedList<Object> reducedItems = new LinkedList<Object>();
        for (Object item : items) {
            reducedItems.add(reduce((LinkedList<Object>) item, null));
        }
        return items;
    }
    
    @Override
    public String toString() {
        return "(" + StringUtils.join(expressions, ",") + ")";
    }
    
    
    
}
