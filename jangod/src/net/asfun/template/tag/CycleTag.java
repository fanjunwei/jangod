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
	
	/**
	 * {% cycle a,b,c %}  type=0;
	 * {% cycle a,b,c as d %}  type=1;
	 * {% cycle d %}  type=2;
	 */
	private int type = 0;
	private String[] values;
	private String var;

	@Override
	public String compile(List<Node> carries, JangodCompiler compiler)
			throws CompilerException {
		if ( type == 2) {
			values = (String[]) compiler.fetchRuntimeScope(var);
			type = 0;
		}
		if ( type == 0 ) {
			int forindex = (Integer) compiler.resolveVariable("loop.index");
			return values[forindex % values.length];
		}
		else {
			compiler.assignRuntimeScope(var, values);
			return "";
		}
	}

	@Override
	public String getEndTagName() {
		return null;
	}

	@Override
	public void initialize(String helpers) throws CompilerException {
		HelperStringTokenizer tk = new HelperStringTokenizer(helpers);
		//TODO 兼容 逗号和空格的分隔形式
		String[] helper = tk.allTokens();
		if (helper.length == 1) {
			HelperStringTokenizer items = new HelperStringTokenizer(helper[0]);
			items.splitComma(true);
			values = items.allTokens();
			if (values.length == 1) {
				type = 2;
				var = values[0];
			} else {
				type = 0;
			}
		}
		if (helper.length == 3) {
			HelperStringTokenizer items = new HelperStringTokenizer(helper[0]);
			items.splitComma(true);
			values = items.allTokens();
			var = helper[2];
			type = 1;
		}
		throw new CompilerException("cycle tag expects 1 or 3 helper(s) >>> " + helper.length);
	}

	@Override
	public String getTagName() {
		return "cycle";
	}

}
