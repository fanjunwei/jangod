package net.asfun.template.filter;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import net.asfun.template.Configuration;
import net.asfun.template.compile.CompilerException;
import net.asfun.template.compile.Filter;

public class DatetimeFilter implements Filter{

	@Override
	public Object filter(Object object, String... arg) throws CompilerException {
		if ( object instanceof String) {
			return object;
		}
		SimpleDateFormat sdf;
		if ( arg.length == 1 ) {
			sdf = new SimpleDateFormat(arg[0].trim());
			sdf.setTimeZone(Configuration.getDefaultConfig().getTimezone());
		} else if ( arg.length == 2 ) {
			sdf = new SimpleDateFormat(arg[0].trim());
			sdf.setTimeZone(TimeZone.getTimeZone(arg[1].trim()));
		} else {
			throw new CompilerException("filter datetime expects 1 or 2 args >>> " + arg.length);
		}
		try { 
			return sdf.format(object);
		} catch (Exception e) {
			throw new CompilerException(e.getMessage());
		}
//		return object;
	}

	@Override
	public String getName() {
		return "date";
	}

}
