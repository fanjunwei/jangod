package net.asfun.template.filter;

import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.logging.Level;

import net.asfun.template.compile.CompilerException;
import net.asfun.template.compile.Filter;
import net.asfun.template.compile.JangodCompiler;
import static net.asfun.template.util.logger.JangodLogger;

public class DatetimeFilter implements Filter{

	@Override
	public Object filter(Object object, JangodCompiler compiler, String... arg) throws CompilerException {
		if ( object == null ) {
			return object;
		}
		SimpleDateFormat sdf;
		if ( arg.length == 1 ) {
			sdf = new SimpleDateFormat(compiler.resolveString(arg[0]));
			sdf.setTimeZone(compiler.getConfig().getTimezone());
		} else if ( arg.length == 2 ) {
			sdf = new SimpleDateFormat(compiler.resolveString(arg[0]));
			sdf.setTimeZone(TimeZone.getTimeZone(compiler.resolveString(arg[1])));
		} else {
			throw new CompilerException("filter date expects 1 or 2 args >>> " + arg.length);
		}
		try { 
			return sdf.format(object);
		} catch (Exception e) {
			JangodLogger.log(Level.SEVERE, "filter date can't format a datetime >>> " + object, e.getCause());
		}
		return object;
	}

	@Override
	public String getName() {
		return "date";
	}

}
