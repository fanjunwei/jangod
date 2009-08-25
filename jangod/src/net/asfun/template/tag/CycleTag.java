package net.asfun.template.tag;

import java.util.List;

import net.asfun.template.compile.CompilerException;
import net.asfun.template.compile.JangodCompiler;
import net.asfun.template.compile.Node;
import net.asfun.template.compile.Tag;
import net.asfun.template.util.HelperStringTokenizer;

/**
 * {% cycle a,b,c %}   {% cycle a,b,c as d %} {% cycle d %}
 * @author fangchq
 *
 */
public class CycleTag implements Tag{
	

	@Override
	public String compile(List<Node> carries, String helpers, JangodCompiler compiler)
			throws CompilerException {
		String[] values;
		String var = null;
		HelperStringTokenizer tk = new HelperStringTokenizer(helpers);
		//TODO 兼容 逗号和空格的分隔形式
		String[] helper = tk.allTokens();
		if (helper.length == 1) {
			HelperStringTokenizer items = new HelperStringTokenizer(helper[0]);
			items.splitComma(true);
			values = items.allTokens();
			if (values.length == 1) {
				var = values[0];
				values = (String[]) compiler.fetchRuntimeScope(var);
			}
			int forindex = (Integer) compiler.resolveVariable("loop.index");
			return values[forindex % values.length];
		} else if (helper.length == 3) {
			HelperStringTokenizer items = new HelperStringTokenizer(helper[0]);
			items.splitComma(true);
			values = items.allTokens();
			var = helper[2];
			compiler.assignRuntimeScope(var, values);
			return "";
		} else {
			throw new CompilerException("cycle tag expects 1 or 3 helper(s) >>> " + helper.length);
		}
	}

	@Override
	public String getEndTagName() {
		return null;
	}

	@Override
	public String getName() {
		return "cycle";
	}

}
