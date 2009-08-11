package net.asfun.template.parse;

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
	 */
	@Override
	protected void parse() {
		content = image.replaceAll("\\{", "{").replaceAll("\\}", "}");
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
