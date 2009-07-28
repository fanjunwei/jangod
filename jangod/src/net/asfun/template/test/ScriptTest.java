package net.asfun.template.test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ScriptTest {

	public static void test1() {
		ScriptEngineManager sem = new ScriptEngineManager();
		ScriptEngine engine = sem.getEngineByName("Jangod");
		Reader reader;
		try {
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
