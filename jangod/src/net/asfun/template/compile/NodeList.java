package net.asfun.template.compile;

import static net.asfun.template.parse.ParserConstants.*;
import static net.asfun.template.util.logging.*;

import java.util.ArrayList;
import java.util.List;

import net.asfun.template.parse.EchoToken;
import net.asfun.template.parse.InstToken;
import net.asfun.template.parse.JangodParser;
import net.asfun.template.parse.TagToken;
import net.asfun.template.parse.Token;

public class NodeList {
	
	/**
	 * general the node tree
	 * @param parser
	 * @param endTagName
	 * @param path
	 * @return
	 */
	public static List<Node> makeList(JangodParser parser, String endTagName, int level) {
		List<Node> nodes = new ArrayList<Node>();
		Token token;
		TagToken tag;
		while( parser.hasNext() ) {
			token = parser.next();
			switch(token.getType()) {
				case TOKEN_FIXED :
				case TOKEN_NOTE :		
					TextNode xn = new TextNode(token);
					nodes.add(xn);
					break;
				case TOKEN_INST :
					try {
						InstNode in = new InstNode((InstToken) token);
						nodes.add(in);
					} catch (CompilerException e) {
						JangodLogger.log(Level.WARNING, "Can't create node with token >>> " + token, e.getCause());
					}	
					break;
				case TOKEN_ECHO :
					VariableNode vn = new VariableNode((EchoToken) token, level);
					nodes.add(vn);
					break;
				case TOKEN_TAG :
					tag = (TagToken) token;
					if ( tag.getTagName().equals(endTagName) ) {
						return nodes;
					}
					try {
						TagNode tn = new TagNode((TagToken) token, parser, level);
						nodes.add(tn);
					} catch (CompilerException e) {
						JangodLogger.log(Level.WARNING, "Can't create node with token >>> " + token,e.getCause());
					}
					break;
				default :
					JangodLogger.warning("Unknown type token >>> " + token);
			}
		}
		return nodes;
	}
}
