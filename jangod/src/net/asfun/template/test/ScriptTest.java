package net.asfun.template.test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import net.asfun.template.engine.JangodContext;

public class ScriptTest {

	@SuppressWarnings("unchecked")
	public static void test1() {
		ScriptEngineManager sem = new ScriptEngineManager();
		ScriptEngine engine = sem.getEngineByName("Jangod");
		ScriptContext global = new JangodContext();
		global.setBindings(engine.getBindings(ScriptContext.GLOBAL_SCOPE), ScriptContext.GLOBAL_SCOPE);
		global.setAttribute("TPL_ROOT_DIR", "D:/workspace/jvalog/war/themes/default/", ScriptContext.GLOBAL_SCOPE);
		engine.setContext(global);
		engine.setBindings(engine.createBindings(), ScriptContext.ENGINE_SCOPE);
		Reader reader;
		try {
			Map page = new HashMap();
			page.put("title", "pagetitles");
			page.put("tagNames", "page tag name");
			Map site = new HashMap();
			site.put("title", "web site title");
			engine.put("page", page);
			engine.put("site", site);
			String[] relateposts = {"abc", "def", "gih"};
			engine.put("relateposts", relateposts);
			reader = new FileReader("D:/workspace/jvalog/war/themes/default/post.html");
			Object obj = engine.eval(reader);
			System.out.println(obj);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ScriptException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		test1();
	}
}
