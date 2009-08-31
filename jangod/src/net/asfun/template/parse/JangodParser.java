package net.asfun.template.parse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.NoSuchElementException;

import net.asfun.template.util.JangodLogger;

public class JangodParser implements Iterator<Token>{
	
	private TokenManager tm = new TokenManager();
	private Token token;
	private boolean proceeding = true;

	public JangodParser(String script) {
		tm.init(script);
	}

	public JangodParser(Reader reader) throws ParserException {
		BufferedReader br = new BufferedReader(reader);
		StringBuffer buff = new StringBuffer();
		String line;
		try {
			while( (line=br.readLine()) != null ) {
				buff.append(line);
				buff.append("\n");
			}
		} catch (IOException e) {
			throw new ParserException("Read template reader fault.");
		}
		tm.init(buff.toString());
	}

	@Override
	public boolean hasNext(){
		if ( proceeding ) {
			try {
				token = tm.getNextToken();
				if ( token != null ) {
					return true;
				} else {
					proceeding = false;
					return false;
				}
			} catch (ParserException e) {
				JangodLogger.severe(e.getMessage(), e.getCause());
				token = null;
				//TODO go on proceeding or not
			}
		}	
		return false;
	}

	@Override
	public Token next() {
		if ( proceeding ) {
			if ( token == null ) {
				try {
					Token tk = tm.getNextToken();
					if ( tk == null ) {
						proceeding = false;
						throw new NoSuchElementException();
					}
					return tk;
				} catch (ParserException e) {
					JangodLogger.severe(e.getMessage(), e.getCause());
					//go on proceeding or not
					throw new NoSuchElementException();
				}
			} else {
				Token last = token;
				token = null;
				return last;
			}
		}
		throw new NoSuchElementException();
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}
}
