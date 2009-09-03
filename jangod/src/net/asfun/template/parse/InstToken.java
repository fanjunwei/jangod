package net.asfun.template.parse;

import static net.asfun.template.parse.ParserConstants.*;

/**
 * Do something hard to be done by TagToken
 * @author fangchq<anysome@com.gmail>
 *
 */
public class InstToken extends Token {
	
	private String instName;
	private String helpers;

	public InstToken(String image) throws ParserException{
		super(image);
	}
	
	@Override
	public int getType() {
		return TOKEN_INST;
	}

	@Override
	protected void parse() throws ParserException{
		content = image.substring(2, image.length()-2).trim();
		int postBlank = content.indexOf(' ');
		if ( postBlank > 0 ) {
			instName = content.substring(0, postBlank);
			helpers = content.substring(postBlank).trim();
		}
		else {
			instName = content;
			helpers = "";
		}	
	}

	public String getInstName() {
		return instName;
	}
	
	public String getHelpers() {
		return helpers;
	}
	
	public String toString() {
		if ( helpers.length() == 0) {
			return "[INST]\r\n" + instName;
		}
		return "[INST]\r\n" + instName + "\r\n\t" + helpers;
	}

}
