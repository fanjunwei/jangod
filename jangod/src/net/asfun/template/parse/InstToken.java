package net.asfun.template.parse;

/**
 * Do something hard to be done by TagToken
 * @author fangchq<anysome@com.gmail>
 *
 */
public class InstToken extends Token {

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
		// TODO Auto-generated method stub
		
	}
	
	public String toString() {
		return "[INS]\r\n" + content;
	}

}
