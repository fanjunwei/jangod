package net.asfun.template.compile;

import java.util.ArrayList;
import java.util.List;

import net.asfun.template.parse.EchoToken;
import net.asfun.template.parse.InstToken;
import net.asfun.template.parse.JangodParser;
import net.asfun.template.parse.TagToken;
import net.asfun.template.parse.Token;
import net.asfun.template.util.JangodLogger;

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
				case Token.TOKEN_FIXED :
				case Token.TOKEN_NOTE :		
					TextNode xn = new TextNode(token);
					nodes.add(xn);
					break;
				case Token.TOKEN_INST :
					try {
						InstNode in = new InstNode((InstToken) token);
						nodes.add(in);
					} catch (CompilerException e) {
						JangodLogger.warning("Can't create node with token >>> " + token,e.getCause());
					}	
					break;
				case Token.TOKEN_ECHO :
					VariableNode vn = new VariableNode((EchoToken) token, level);
					nodes.add(vn);
					break;
				case Token.TOKEN_TAG :
					tag = (TagToken) token;
					if ( tag.getTagName().equals(endTagName) ) {
						return nodes;
					}
					try {
						TagNode tn = new TagNode((TagToken) token, parser, level);
						nodes.add(tn);
					} catch (CompilerException e) {
						JangodLogger.warning("Can't create node with token >>> " + token,e.getCause());
					}
					break;
				default :
					JangodLogger.warning("Unknown type token >>> " + token);
			}
		}
		return nodes;
	}
}
