package net.asfun.template.parse;

import static net.asfun.template.parse.ParserConstants.*;

public class FixedToken extends Token{

	public FixedToken(String image) throws ParserException{
		super(image);
	}
	
	@Override
	public int getType() {
		return TOKEN_FIXED;
	}
	
	/**
	 * set n is an integer and > 0
	 * change "{\[n]{" and "{\[n]!" and "{\[n]#" and "{\[n]%"
	 * to     "{\[n-1]{"   or "{\[n-1]!"   or "{\[n-1]#"   or "{\[n-1]%"
	 */
	@Override
	protected void parse() {
		content = image.replaceAll("\\{\\\\(\\\\*[\\{!#%])","{$1");
	}

	public boolean isBlank() {
		return content.trim().length()==0;
	}
	
	public String trim() {
		return content.trim();
	}
	
	public String output() {
		return content;
	}
	
	public String toString() {
		if ( isBlank() ) {
			return "[OUT]";
		}
		return "[OUT]\r\n" + content;
	}
}
