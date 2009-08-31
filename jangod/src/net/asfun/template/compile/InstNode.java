package net.asfun.template.compile;

//import java.util.List;

import net.asfun.template.parse.InstToken;

public class InstNode implements Node {


//	private InstToken token;
//	private Instruction inst;
//	private List<Node> carries;
//	private String endInstName;
	
	public InstNode(InstToken tk) throws CompilerException {
//		token = tk;
//		inst = InstructionLibrary.getInstruction(token.getInstName());
//		endInstName = inst.getEndInstName();
//		if ( endInstName != null ) {
//			carries = NodeList.makeList(parser, endInstName, level + 1);
//		} else {
//			return
//		}
	}

	@Override
	public String render(JangodCompiler compiler) throws CompilerException {
		return "";
	}

}
