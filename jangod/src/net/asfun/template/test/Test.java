package net.asfun.template.test;

import java.util.Properties;

import net.asfun.template.Jangod;
import net.asfun.template.engine.IEngine;

public class Test {

	public static void main(String[] args) {
		Jangod god = new Jangod();
		god.setConfig(new Properties());
		IEngine engine = god.getEngine();
	}
}
