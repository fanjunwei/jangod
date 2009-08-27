package net.asfun.template.filter;

import java.math.BigDecimal;
import java.math.BigInteger;

import net.asfun.template.compile.CompilerException;
import net.asfun.template.compile.Filter;
import net.asfun.template.compile.JangodCompiler;

public class AddFilter implements Filter{

	@Override
	public Object filter(Object object, JangodCompiler compiler, String... arg)
			throws CompilerException {
		if ( arg.length != 1) {
			throw new CompilerException("filter add expects 1 arg >>> " + arg.length);
		}
		Object toAdd = compiler.resolveObject(arg[0]);
		Number num;
		if ( String.class.isAssignableFrom(toAdd.getClass()) ) {
			try { 
				num = new BigDecimal(toAdd.toString());
			} catch (Exception e) {
				throw new CompilerException("filter add arg can't cast to number >>> " + toAdd);
			}
		} else if (Number.class.isAssignableFrom(toAdd.getClass()) ) {
			num = (Number) toAdd;
		} else {
			return object;
		}
		if ( object instanceof Integer ) {
			return 0L + num.intValue() + (Integer)object;
		}
		if ( object instanceof Float ) {
			return 0D + num.floatValue() + (Float)object;
		}
		if ( object instanceof Long ) {
			return num.longValue() + (Long)object;
		}
		if ( object instanceof Short ) {
			return 0 + num.shortValue() + (Short)object;
		}
		if ( object instanceof Double ) {
			return num.doubleValue() + (Double)object;
		}
		if ( object instanceof BigDecimal ) {
			return ((BigDecimal)object).add(BigDecimal.valueOf(num.doubleValue()));
		}
		if ( object instanceof BigInteger ) {
			return ((BigInteger)object).add(BigInteger.valueOf(num.longValue()));
		}
		if ( object instanceof Byte ) {
			return num.byteValue() + (Byte)object;
		}
		if ( object instanceof String ) {
			try {
				return num.doubleValue() + Double.valueOf(object.toString());
			} catch (Exception e) {
				throw new CompilerException(object + " can't be dealed with add filter");
			}
		}
		return object;
	}

	@Override
	public String getName() {
		return "add";
	}

}
