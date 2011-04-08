package br.com.bfgex;

import br.com.bfgex.interpreter.Parser;

public class RegexGen {
  
	public static String of(String pattern) {
		return Parser.parse(pattern).reduce();
	}
}
