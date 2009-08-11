package net.asfun.template.tag;

import java.util.List;

import net.asfun.template.compile.CompilerException;
import net.asfun.template.compile.JangodCompiler;
import net.asfun.template.compile.Node;

public class IfchangeTag implements Tag{
	
	private String var;

	@Override
	public String compile(List<Node> carries, JangodCompiler compiler)
			throws CompilerException {
		Object test = compiler.resolveVariable(var);
		//compare with what? TODO save last value
		return null;
	}

	@Override
	public String getEndTagName() {
		return "endif";
	}

	@Override
	public void initialize(String helpers) throws CompilerException {
		var = helpers;
	}

}
