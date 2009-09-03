package net.asfun.template.parse;

import static net.asfun.template.parse.ParserConstants.*;

public abstract class Token{

	protected String image;
	//useful for some token type
	protected String content;
	
	public Token(String image2) throws ParserException{
		image = image2;
		parse();
	}

	public String getImage() {
		return image;
	}
	
	public String toString() {
		return image;
	}
	
	protected abstract void parse() throws ParserException;
	
	public abstract int getType();

	public static Token newToken(int tokenKind, String image2) throws ParserException {
		switch( tokenKind ) {
		case TOKEN_FIXED : 
			return new FixedToken(image2);
		case TOKEN_NOTE :
			return new NoteToken(image2);
		case TOKEN_ECHO :
			return new EchoToken(image2);
		case TOKEN_TAG :
			return new TagToken(image2);
		case TOKEN_INST :
			return new InstToken(image2);
		default :
			throw new ParserException("Creating a token with unknown type" + (char)tokenKind);	
		}
	}
	
}
