package net.asfun.template.parse;

/**
 * Do something hard to be done by TagToken
 * @author fangchq<anysome@com.gmail>
 *
 */
public class InstToken extends Token {

	public InstToken(String image) {
		super(image);
	}

	@Override
	protected void parse() {
		content = image.substring(2, image.length()-2).trim();
		// TODO Auto-generated method stub
		
	}

}
