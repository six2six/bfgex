package br.com.bfgex;

import static org.apache.commons.lang.ObjectUtils.defaultIfNull;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.math.NumberRange;

public class Reducer {

	interface ExpReducer {
		Object exp(Object cell, Object quantity);
	}
	
	interface LiteralReducer {
		String literal(Object quantity);
	}
	
	private static Map<Literal, LiteralReducer> LITERAL_MAP = new HashMap<Literal, LiteralReducer>() {{

		this.put(Literal.DIGIT, new LiteralReducer() {
			public String literal(Object quantity) {
				return (String) defaultIfNull(verifyGreedyForValue(quantity, Randgen.pickDigits(1)),
					Randgen.pickDigits(quantity instanceof NumberRange ? Randgen.pickRange((NumberRange) quantity).intValue() : 1));
			}
		});

		this.put(Literal.SPACE, new LiteralReducer() {
			public String literal(Object quantity) {
				return (String) defaultIfNull(verifyGreedyForValue(quantity, Randgen.pickWhiteSpaces(1)),
						Randgen.pickWhiteSpaces(quantity instanceof NumberRange ? Randgen.pickRange((NumberRange) quantity).intValue() : 1));
			}
		});

		this.put(Literal.WORD, new LiteralReducer() {
			public String literal(Object quantity) {
				return (String) defaultIfNull(verifyGreedyForValue(quantity, Randgen.pickChar()),
						quantity instanceof NumberRange ? Randgen.word(Randgen.pickRange((NumberRange) quantity).intValue()) : Randgen.pickChar());
			}
		});
		
	}};
	
	private static Map<Exp, ExpReducer> EXP_MAP = new HashMap<Exp, ExpReducer>() {{
		this.put(Exp.UNION, new ExpReducer() {
			public Object exp(Object cell, Object quantity) {
				return null;
			}
		});
		
		this.put(Exp.INTERSECTION, new ExpReducer() {
			public Object exp(Object cell, Object quantity) {
				return null;
			}
		});

		this.put(Exp.RANDOM, new ExpReducer() {
			public Object exp(Object cell, Object quantity) {
				return cell instanceof Literal? LITERAL_MAP.get(cell).literal(quantity) : randgen(quantity);
			}
		});

		this.put(Exp.QUANTIFY, new ExpReducer() {
			public Object exp(Object cell, Object quantity) {
				return null;
			}
		});

		this.put(Exp.LITERAL, new ExpReducer() {
			public Object exp(Object cell, Object quantity) {
				return null;
			}
		});
		
	}};
	
	
	private static String verifyGreedyForValue(Object quantity, String value) {
		return quantity != null && quantity.equals("?") ? Randgen.pickOne("", value): null;
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
