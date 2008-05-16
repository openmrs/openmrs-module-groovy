package org.openmrs.module.groovy.web;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import org.openmrs.module.ModuleFactory;
import org.openmrs.module.groovy.GroovyActivator;

public class DWRGroovyService {

	private GroovyActivator gma;

	public String eval(String script) {
		
		Object result;

		getGMA().clearBuffer();
		try {
			result = getGMA().getShell().evaluate(script);
		} catch (Exception e) {
		    final Writer trace = new StringWriter();
		    final PrintWriter printWriter = new PrintWriter(trace);
		    e.printStackTrace(printWriter);
			return "Exception: <b>" + e.getMessage() + "</b><br>" + trace.toString();
		}
		
		String output = getGMA().getBuffer();
		if (output != null && output.length() > 0)
			return output;
		else
			return (result == null ? "" : result.toString());
	}
	
	public void reset() {
		getGMA().resetShell();
	}
	
	private GroovyActivator getGMA() {
		if (gma == null)
			gma = (GroovyActivator) ModuleFactory.getModuleById("groovy")
					.getActivator();
		return gma;
	}
}
