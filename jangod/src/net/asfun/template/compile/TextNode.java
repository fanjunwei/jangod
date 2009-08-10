package net.asfun.template.compile;

import net.asfun.template.parse.FixedToken;
import net.asfun.template.parse.Token;

public class TextNode implements Node{
	
	private Token token;

	@Override
	public String compile(JangodCompiler compiler) {
		if ( token instanceof FixedToken ) {
			return ((FixedToken) token).output();
		} else {
			return "";
		}
	}

}
