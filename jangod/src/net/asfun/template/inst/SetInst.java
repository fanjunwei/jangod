package net.asfun.template.inst;

import net.asfun.template.compile.CompilerException;
import net.asfun.template.compile.Instruction;
import net.asfun.template.compile.JangodCompiler;
import net.asfun.template.compile.VariableFilter;
import net.asfun.template.util.HelperStringTokenizer;

/**
 * {! set var post.id|equal:'12' !}
 * @author fangchq
 *
 */
public class SetInst implements Instruction{


	@Override
	public String getName() {
		return "set";
	}

	@Override
	public void act(String helpers, JangodCompiler compiler) throws CompilerException {
		String[] helper = new HelperStringTokenizer(helpers).allTokens();
		if ( helper.length != 2 ) {
			throw new CompilerException("set inst expects 2 helper >>> " + helper.length);
		}
		Object value = VariableFilter.compute(helper[1], compiler);
		compiler.assignEngineScope(helper[0], value);
	}

}
