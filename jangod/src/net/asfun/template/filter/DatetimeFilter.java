package net.asfun.template.filter;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import net.asfun.template.Configuration;
import net.asfun.template.compile.CompilerException;
import net.asfun.template.compile.Filter;
import net.asfun.template.compile.JangodCompiler;
import net.asfun.template.util.JangodLogger;

public class DatetimeFilter implements Filter{

	@Override
	public Object filter(Object object, JangodCompiler compiler, String... arg) throws CompilerException {
		if ( object == null ) {
			return object;
		}
		SimpleDateFormat sdf;
		if ( arg.length == 1 ) {
			sdf = new SimpleDateFormat(compiler.resolveString(arg[0]));
			//TODO compiler.getConfig();
			sdf.setTimeZone(Configuration.getDefaultConfig().getTimezone());
		} else if ( arg.length == 2 ) {
			sdf = new SimpleDateFormat(compiler.resolveString(arg[0]));
			sdf.setTimeZone(TimeZone.getTimeZone(compiler.resolveString(arg[1])));
		} else {
			throw new CompilerException("filter date expects 1 or 2 args >>> " + arg.length);
		}
		try { 
			return sdf.format(object);
		} catch (Exception e) {
			JangodLogger.severe("filter date can't format a datetime >>> " + object, e.getCause());
		}
		return object;
	}

	@Override
	public String getName() {
		return "date";
	}

}
