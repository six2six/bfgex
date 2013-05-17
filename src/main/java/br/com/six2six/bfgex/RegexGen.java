package br.com.six2six.bfgex;

import br.com.six2six.bfgex.interpreter.Parser;

public class RegexGen {
  
	public static String of(String pattern) {
		return Parser.parse(pattern).reduce();
	}
}
