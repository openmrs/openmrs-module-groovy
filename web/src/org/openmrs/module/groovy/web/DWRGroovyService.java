package org.openmrs.module.groovy.web;

import org.openmrs.module.ModuleFactory;
import org.openmrs.module.groovy.GroovyActivator;

public class DWRGroovyService {

	private GroovyActivator gma = null;

	public String eval(String script) {

		if (gma == null)
			gma = (GroovyActivator) ModuleFactory.getModuleById("groovy")
					.getActivator();

		gma.clearBuffer();
		try {
			Object value = gma.getShell().evaluate(script);
		} catch (Exception e) {
			return "Exception: " + e.getMessage();
		}
		return gma.getBuffer();
	}
}
