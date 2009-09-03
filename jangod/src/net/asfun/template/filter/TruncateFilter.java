package net.asfun.template.filter;

import net.asfun.template.compile.CompilerException;
import net.asfun.template.compile.Filter;
import net.asfun.template.compile.JangodCompiler;
import static net.asfun.template.util.logger.JangodLogger;

public class TruncateFilter implements Filter{

	@Override
	public Object filter(Object object, JangodCompiler compiler, String... arg) throws CompilerException {
		if ( object instanceof String ) {
			int length = 100;
			String ends = "...";
			if ( arg.length > 0 ) {
				try {
					length = Integer.valueOf(compiler.resolveString(arg[0]));
				} catch (Exception e) {
					JangodLogger.warning("Filter truncate get length error use default >>> 100");
				}
			}
			if ( arg.length > 1 ) {
				ends = compiler.resolveString(arg[1]);
			}
			return object.toString().substring(0, length) + ends;
		}
		return object;
	}

	@Override
	public String getName() {
		return "truncate";
	}

}
