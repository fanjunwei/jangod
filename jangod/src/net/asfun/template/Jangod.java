package net.asfun.template;

import java.util.Properties;

import net.asfun.template.engine.IEngine;

public class Jangod {

	private Context context;
	
	private EngineFactory factory;
	
	private Configuration config;
	
	private IEngine engine;

	public void setConfig(Properties properties) {
		config = new Configuration(properties);
	}

	public IEngine getEngine() {
		// TODO Auto-generated method stub
		return null;
	}
}
