package net.asfun.template.parse;

//import java.util.logging.Logger;

public class TokenManager implements ParserConstants{

	private char[] is;
	private int currPost = 0;
	private int tokenStart = 0;
	private int tokenLength = 0;
	private int tokenKind = -1;
	private int length = 0;
	private char[] image;
	
//	private static final Logger logger = Logger.getLogger("asfun.jandog");
	
	public void init(String inputstream) {
		is = inputstream.toCharArray();
		length = inputstream.length();
		currPost = 0;
		tokenStart = 0;
		tokenKind = -1;
		image = new char[1024];
	}
	
	public Token getNextToken() throws ParserException {
		char c = 0;
		while ( currPost < length ) {
			c = is[currPost++];
			if ( currPost == length ) {
				return getEndToken();
			}
			switch( c ) {
			//mayby a new token is starting
			case TOKEN_PREFIX :
				if ( currPost < length ) {
					c = is[currPost];
					switch( c ) {
					case TOKEN_INST :
					case TOKEN_TAG :
					case TOKEN_ECHO :
					case TOKEN_NOTE :
						//match token two ends
						if ( ! matchToken(c) && tokenKind > 0 ) {
							continue;
						}
						tokenLength = currPost-tokenStart-1;
						if ( tokenLength > 0 ) {
							//start a new token
							System.arraycopy(is, tokenStart, image, 0, tokenLength);
							tokenStart = --currPost;
							int kind = tokenKind;
							tokenKind = c;
							return newToken(kind);
						} else {
							tokenKind = c;
						}
						break;
					default :
						//nothing continue;
					}
				}
				//reach the stream end
				else {
					return getEndToken();
				}
				break;
			//mayby current token is closing	
			case TOKEN_INST :
			case TOKEN_TAG :
			case TOKEN_ECHO2 :
			case TOKEN_NOTE :
				//match token two ends
				if ( ! matchToken(c) ) {
					continue;
				}
				if ( currPost < length ) {
					c = is[currPost];
					if ( c == TOKEN_POSTFIX ) {
						tokenLength = currPost-tokenStart+1;
						if ( tokenLength > 0 ) {
							//start a new token
							System.arraycopy(is, tokenStart, image, 0, tokenLength);
							tokenStart = ++currPost;
							int kind = tokenKind;
							tokenKind = TOKEN_FIXED;
							return newToken(kind);
						}
					}
				} else {
					return getEndToken();
				}
				break;
			default:
				//nothing continue;
			}
		}
		return null;
	}
	
	private Token getEndToken() throws ParserException {
		tokenLength = currPost-tokenStart;
		System.arraycopy(is, tokenStart, image, 0, tokenLength);
		return Token.newToken(TOKEN_FIXED, image);
	}
	
	private Token newToken(int kind) throws ParserException {
		Token token = Token.newToken(kind, image);
		image = new char[1024];
		return token;
	}
	
	private boolean matchToken(char kind) {
		if ( kind == TOKEN_ECHO ) {
			return tokenKind == TOKEN_ECHO2;
		} else if ( kind == TOKEN_ECHO2 ) {
			return tokenKind == TOKEN_ECHO;
		} else {
			return kind == tokenKind;
		}
	}
	
}
