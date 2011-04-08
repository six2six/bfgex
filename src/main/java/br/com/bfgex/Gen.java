package br.com.bfgex;

public class Gen {
  
	public static String of(String pattern) {
		return Parser.parse(pattern).reduce();
	}
}
