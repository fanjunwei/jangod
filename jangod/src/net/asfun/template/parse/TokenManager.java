package net.asfun.template.parse;

public class TokenManager implements ParserConstants{

	private static char[] is;
	private static int currPost = 0;
	private static int tokenStart = 0;
	private static int tokenLength = 0;
	private static int tokenKind = TOKEN_TAG;
	private static boolean isEcho = false;
	private static int length = 0;
	private static char[] image = new char[1024];
	
	public static void init(String inputstream) {
		is = inputstream.toCharArray();
		length = inputstream.length();
		currPost = 0;
		tokenStart = 0;
	}
	
	public static Token getNextToken() {
		char c = 0;
		while ( currPost < length ) {
			c = is[currPost++];
			switch( c ) {
			//mayby a new token is starting
			case TOKEN_PREFIX :
				if ( currPost < length ) {
					c = is[currPost];
					switch( c ) {
					case TOKEN_CTRL :
					case TOKEN_TAG :
					case TOKEN_ECHO :
					case TOKEN_NOTE :
						tokenLength = currPost-tokenStart-1;
						if ( tokenLength > 0 ) {
							//start a new token
							System.arraycopy(is, tokenStart, image, 0, tokenLength);
							tokenStart = --currPost;
							int kind = tokenKind;
							tokenKind = c;
							isEcho = false;
							return Token.newToken(kind, image);
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
			case TOKEN_CTRL :
			case TOKEN_TAG :
			case TOKEN_ECHO2 :
			case TOKEN_NOTE :
				if ( currPost < length ) {
					c = is[currPost];
					if ( c == TOKEN_POSTFIX ) {
						tokenLength = currPost-tokenStart-1;
						if ( tokenLength > 0 ) {
							//start a new token
							System.arraycopy(is, tokenStart, image, 0, tokenLength);
							tokenStart = --currPost;
							int kind = tokenKind;
							tokenKind = c;
							isEcho = true;
							return Token.newToken(kind, image);
						}
					}
				} else {
					return getEndToken();
				}
				break;
			default:
				if ( currPost == length ) {
					return getEndToken();
				}
			}
		}
		return null;
	}
	
	private static Token getEndToken() {
		tokenLength = currPost-tokenStart;
		System.arraycopy(is, tokenStart, image, 0, tokenLength);
		if (isEcho) {
			return Token.newToken(TOKEN_ECHO, image);
		}
		return Token.newToken(tokenKind, image);
	}
	
}
