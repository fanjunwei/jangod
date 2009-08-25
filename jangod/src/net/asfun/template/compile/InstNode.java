package net.asfun.template.compile;

import net.asfun.template.parse.InstToken;

public class InstNode implements Node {

	private int level;
	private InstToken token;
	private Instruction inst;
	
	public InstNode(InstToken tk, int lvl) throws CompilerException {
		token = tk;
		level = lvl;
		inst = InstructionLibrary.getInstruction(token.getInstName());
	}

	@Override
	public String render(JangodCompiler compiler) throws CompilerException {
		compiler.setLevel(level);
		inst.act(token.getHelpers(), compiler);
		return "";
	}

}
