package net.asfun.template.engine;

import java.util.ArrayList;
import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;


public class JangodEngineFactory implements ScriptEngineFactory{

	@Override
	public String getEngineName() {
		return "ASFUN Jangod template engine";
	}

	@Override
	public String getEngineVersion() {
		return "0.28";
	}

	@Override
	public List<String> getExtensions() {
		List<String> ext = new ArrayList<String>();
		ext.add("god");
		ext.add("tpl");
		ext.add("html");
		ext.add("jangod");
		return ext;
	}

	@Override
	public String getLanguageName() {
		return "Jangod";
	}

	@Override
	public String getLanguageVersion() {
		return "1.0";
	}

	@Override
	public String getMethodCallSyntax(String obj, String m, String... args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getMimeTypes() {
		return new ArrayList<String>();
	}

	@Override
	public List<String> getNames() {
		List<String> names = new ArrayList<String>();
		names.add("Jangod");
		names.add("Django");
		names.add("Jinja");
		return names;
	}

	@Override
	public String getOutputStatement(String toDisplay) {
		// TODO Auto-generated method stub
		// display the toDisplay script in script file without parse it.
		return null;
	}

	@Override
	public Object getParameter(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getProgram(String... statements) {
		StringBuilder buff = new StringBuilder();
		for(String statement : statements) {
			buff.append(statement).append("\n");
		}
		return buff.toString();
	}

	@Override
	public ScriptEngine getScriptEngine() {
		return new JangodEngine(this);
	}
	
	@Override
	public String toString() {
		return getEngineName() + " v" + getEngineVersion();
	}

}
