package net.asfun.template;

import java.util.Locale;
import java.util.TimeZone;

import net.asfun.template.compile.Filter;
import net.asfun.template.compile.FilterLibrary;
import net.asfun.template.compile.Importable;
import net.asfun.template.compile.Instruction;
import net.asfun.template.compile.InstructionLibrary;
import net.asfun.template.compile.Tag;
import net.asfun.template.compile.TagLibrary;
import net.asfun.template.util.JangodLogger;

public class Configuration {
	
	public static final String CONFIG_VAR = "'CFG\"CURRENT";
	
	private String encoding = "utf-8";
	private Locale locale = Locale.CHINESE;
	private TimeZone timezone = TimeZone.getDefault();
	private static Configuration defaultConfig;
	
	private String root;
	
	static {
		defaultConfig = new Configuration();
	}

	public static void addImport(Class<Importable> clazz) {
		try {
			if (clazz.isAssignableFrom(Tag.class)) {
				Tag tag = (Tag) clazz.newInstance();
				TagLibrary.addTag(tag);
			}
			if (clazz.isAssignableFrom(Filter.class)) {
				Filter filter = (Filter) clazz.newInstance();
				FilterLibrary.addFilter(filter);
			}
			if (clazz.isAssignableFrom(Instruction.class)) {
				Instruction inst = (Instruction) clazz.newInstance();
				InstructionLibrary.addInstruction(inst);
			}
		} catch (InstantiationException e) {
			JangodLogger.warning("Import library error >>> " + clazz.getName(), e.getCause());
		} catch (IllegalAccessException e) {
			JangodLogger.warning("Import library error >>> " + clazz.getName(), e.getCause());
		}
	}
	
	public void setEncoding(String encoding2) {
		encoding = encoding2;
	}
	
	public void setLocale(Locale locale2) {
		locale = locale2;
	}
	
	public String getEncoding() {
		return encoding;
	}
	
	public Locale getLocale() {
		return locale;
	}
	
	public TimeZone getTimezone() {
		return timezone;
	}

	public void setTimezone(TimeZone timezone2) {
		timezone = timezone2;
	}

	public static Configuration getDefaultConfig() {
		return defaultConfig;
	}

	public String getTemplateRoot() {
		return root;
	}
	
	public void setTemplateRoot(String rootPath) {
		root = rootPath;
	}
}
