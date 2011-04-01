package br.com.bfgex;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class Sexp {
    private LinkedList<Object> expressions = new LinkedList<Object>();

    public Sexp(Exp exp) {
        expressions.add(exp);
    }
    
    public List<Object> getValues() {
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
    
    public Object pop() {
        return expressions.pop();
    }

    @Override
    public String toString() {
        return "(" + StringUtils.join(expressions, ",") + ")";
    }
    
    
    
}
