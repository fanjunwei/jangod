/**********************************************************************
Copyright (c) 2009 Asfun Net.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
**********************************************************************/
package net.asfun.template.parse;

import static net.asfun.template.parse.ParserConstants.*;
import java.util.List;

public class EchoToken extends Token {
	
	private FilterParser fp;
	
	public EchoToken(String image) throws ParserException{
		super(image);
	}
	
	public String getVariable() {
		return fp.getVariable();
	}
	
	/**
	 * get the filters
	 * @return filters
	 */
	public List<String> getFilters() {
		return fp.getFilters();
	}
	
	/**
	 * get filters' args
	 * @return
	 */
	public List<String[]> getArgss() {
		return fp.getArgss();
	}

	/**
	 * Get var and filters
	 * Like that if
	 *     image = {{obj.attr.attr|filter1:"ar|g1",arg2|filter2:'a:",b"c' }}
	 * then
	 *     var = obj.attr.attr
	 *     filter = [
	 *         [filter1,ar|g1,arg2],
	 *         [filter2,a:",b"c]   ]
	 * @throws ParserException 
	 */
	@Override
	protected void parse() throws ParserException {
		fp = new FilterParser(image.substring(2, image.length()-2).trim());
		fp.parse();
	}

	@Override
	public String toString() {
		String s = "[VAR]\r\n" + fp.getVariable();
		int i,j;
		for (i=0; i<fp.getFilters().size(); i++) {
			s += "\r\n\t" + fp.getFilters().get(i);
			String[] args = fp.getArgss().get(i);
			if ( args != null) {
				s += "\r\n\t";
				for(j=0; j<args.length; j++) {
					s += "\t" + args[j];
				}
			}
		}
		return s;
	}

	@Override
	public int getType() {
		return TOKEN_ECHO;
	}
}
