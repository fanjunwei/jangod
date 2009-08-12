package net.asfun.template.util;

import java.util.logging.Level;
import java.util.logging.Logger;

public class JangodLogger {

	private static final Logger logger = Logger.getLogger("asfun.jandog");
	
	public static void finer(String message) {
		logger.finer(message);
	}
	
	public static void finest(String message) {
		logger.finest(message);
	}
	
	public static void fine(String message) {
		logger.fine(message);
	}
	
	public static void info(String message) {
		logger.info(message);
	}
	
	public static void warning(String message) {
		logger.warning(message);
	}
	
	public static void severe(String message) {
		logger.severe(message);
	}
	
	public static void warning(String message, Throwable thrown) {
		logger.log(Level.WARNING, message, thrown);
	}
	
	public static void severe(String message, Throwable thrown) {
		logger.log(Level.SEVERE, message, thrown);
	}
}
