package org.openmrs.module.groovy.web;

import org.openmrs.module.ModuleFactory;
import org.openmrs.module.groovy.GroovyActivator;

public class DWRGroovyService {

	private GroovyActivator gma = null;

	public String eval(String script) {

		getGMA().clearBuffer();
		try {
			Object value = getGMA().getShell().evaluate(script);
		} catch (Exception e) {
			return "Exception: " + e.getMessage();
		}
		return getGMA().getBuffer();
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
