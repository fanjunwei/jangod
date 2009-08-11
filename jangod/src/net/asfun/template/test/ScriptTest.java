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

public class ScriptTest {

	@SuppressWarnings("unchecked")
	public static void test1() {
		ScriptEngineManager sem = new ScriptEngineManager();
		ScriptEngine engine = sem.getEngineByName("Jangod");
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
			reader = new FileReader("D:/workspace/jangod/tpl/test.html");
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
