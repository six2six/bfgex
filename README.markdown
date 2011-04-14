regex based generation
=========================================

installing:

 mvn clean install


 use like a maven dependency on your project

 	  <dependency>
	     <groupId>br.com.bfgex</groupId>
		 <artifactId>bfgex</artifactId>
		 <version>1.0-SNAPSHOT</version>
	   </dependency>

usage
=======================================

RegexGen.of("(\\w{4})(\\d{3})"); //return zori873