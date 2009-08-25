package net.asfun.template.util;

import java.util.Iterator;

/**
 * Whitespace and comma as separator
 * quote to accept them as normal char
 * @author fangchq
 *
 */
public class HelperStringTokenizer implements Iterator<String>, Iterable<String>{

	private char[] helpers;
	private int currPost = 0;
	private int tokenStart = 0;
	private int length = 0;
//	private int startChar = -1;
	private int lastStart = 0;
	private boolean useComma = false;
	private char quoteChar = 0;
	private boolean inQuote = false;
	
	public HelperStringTokenizer(String tobeToken) {
		helpers = tobeToken.toCharArray();
		length = tobeToken.length();
	}
	
	/**
	 * use Comma as token split or not
	 * true use it;
	 * false don't use it.
	 * @param onOrOff
	 */
	public void splitComma(boolean onOrOff) {
		useComma = onOrOff;
	}
	
	
	@Override
	public boolean hasNext() {
		return length > currPost;
	}

	@Override
	public String next() {
		String token;
		while( currPost < length ) {
			token = makeToken();
			if (token != null) {
				return token;
			}
		}
		return null;
	}
	
	private String makeToken() {
		char c = helpers[currPost++];
		if ( c == '"' | c == '\'') {
			if ( inQuote ){
				if ( quoteChar == c ) {
					inQuote = false;
				}
			} else {
				inQuote = true;
				quoteChar = c;
			}
		}
		if ( Character.isWhitespace(c) || (useComma && c == ',') ) {
			if ( ! inQuote ) {
				return newToken();
			}
		}	
//		if ( c =='"' | c == '\'' ) {
//			//reach the end of current token
//			if (startChar == c) {
//				return newToken();
//			} 
//			//start a new token
//			else if(startChar == -1) {
//				startChar = c;
//				tokenStart = currPost;
//			}
//			//in a token
//		}
//		else if ( Character.isWhitespace(c) || (useComma && c == ',') ) {
//			if ( Character.isWhitespace(startChar) ) {
//				return newToken();
//			} 
//			else if(startChar == -1) {
//				return makeToken();
//			}
//		}
//		else if ( startChar == -1) {
//			startChar = 32;// ' '
//			tokenStart = currPost - 1;
//		}
		if ( currPost == length ) {
			return getEndToken();
		}
		return null;
	}

	private String getEndToken() {
		return String.copyValueOf(helpers, tokenStart, currPost-tokenStart);
	}
	
	private String newToken() {
		lastStart = tokenStart;
		tokenStart = currPost;
//		startChar = -1;
		return String.copyValueOf(helpers, lastStart, currPost-lastStart-1);
	}


	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Iterator<String> iterator() {
		return this;
	}
	
	public String[] allTokens() {
		String[] res = new String[30];
		int i = 0;
		for (String token: this) {
			res[i++] = token;
		}
		String[] tokens = new String[i];
		System.arraycopy(res, 0, tokens, 0, i);
		return tokens;
	}
}
