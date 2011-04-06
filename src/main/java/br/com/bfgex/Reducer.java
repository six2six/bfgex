package br.com.bfgex;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("serial")
public class Reducer {

	interface ExpReducer {
		Object exp(Object cell, Quantifier quantity);
	}
	
	private static Map<Exp, ExpReducer> EXP_MAP = new HashMap<Exp, ExpReducer>() {{
		this.put(Exp.UNION, new ExpReducer() {
			public Object exp(Object cell, Quantifier quantity) {
				return null;
			}
		});
		
		this.put(Exp.INTERSECTION, new ExpReducer() {
			public Object exp(Object cell, Quantifier  quantity) {
				return null;
			}
		});

		this.put(Exp.RANDOM, new ExpReducer() {
			public Object exp(Object cell, Quantifier  quantity) {
				return cell instanceof Literal? quantity.getGeneratedValue((Literal) cell) : randgen(quantity);
			}
		});

		this.put(Exp.QUANTIFY, new ExpReducer() {
			public Object exp(Object cell, Quantifier  quantity) {
				return null;
			}
		});

		this.put(Exp.LITERAL, new ExpReducer() {
			public Object exp(Object cell, Quantifier  quantity) {
				return null;
			}
		});
		
	}};
	
	
	private static String verifyReluctantForValue(Object quantity, String value) {
		return quantity != null && quantity.equals("?") ? Randgen.pickOne("", value) : null;
	}
	
	public static String reduce(Sexp sexp) {
		return reduce(sexp, null);
	}
	
	private static String reduce(Sexp sexp, Object quantity) {
		return null;
	}
	
	
	private static String randgen(Object quantity) {
		return null;
	}
	
	
	
	
	
	
}
