package net.asfun.template.compile;

import net.asfun.template.parse.FixedToken;
import net.asfun.template.parse.Token;

public class TextNode implements Node{
	
	public TextNode(Token tk) {
		token = tk;
	}
	
	private Token token;

	@Override
	public String render(JangodCompiler compiler) {
		if ( token instanceof FixedToken ) {
			return ((FixedToken) token).output();
		} else {
			return "";
		}
	}

}
