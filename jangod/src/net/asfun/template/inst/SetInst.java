package net.asfun.template.inst;

import java.util.Collection;

import net.asfun.template.compile.CompilerException;
import net.asfun.template.compile.Instruction;
import net.asfun.template.compile.JangodCompiler;
import net.asfun.template.util.HelperStringTokenizer;

public class SetInst implements Instruction{


	@Override
	public String getInstName() {
		return "set";
	}

	@SuppressWarnings("unchecked")
	@Override
	public void act(int level, String helpers, JangodCompiler compiler) throws CompilerException {
		String[] helper = new HelperStringTokenizer(helpers).allTokens();
		if ( helper.length != 4 ) {
			throw new CompilerException("set inst expects 4 helper >>> " + helper.length);
		}
		String var = helper[0],
			left = helper[1],
			operate = helper[2],
			right = helper[3];
		compiler.setLevel(level);
		Object eleft = getValue(compiler, left);
		Object eright = getValue(compiler, right);
		if ( "==".equals(operate) ) {
			compiler.assignEngineScope(var, eleft.equals(eright));
		}
		if ( "!=".equals(operate) ) {
			compiler.assignEngineScope(var, ! eleft.equals(eright));
		}
		if ( "@".equals(operate) ) {
			Collection er = (Collection)eright;
			compiler.assignEngineScope(var, er.contains(eleft));
		}
	}
	
	private Object getValue(JangodCompiler compiler, String varName) {
		Object obj = compiler.resolveVariable(varName);
		if (obj == null) obj = varName;
		return obj;
	}
}
