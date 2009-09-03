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
	 * change "\{" and "\}" to "{" or "}"
	 * TODO 更改特殊字符的替换, {\[n]{  ->  {\[n-1]{
	 * change "{\{" and "{\!" and "{\#" and "{\%"
	 * to     "{{"   or "{!"   or "{#"   or "{%"
	 */
	@Override
	protected void parse() {
		content = image.replaceAll("\\\\\\{", "{").replaceAll("\\\\\\}", "}");;
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
