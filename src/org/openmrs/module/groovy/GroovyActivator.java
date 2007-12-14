/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.groovy;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.Activator;

/**
 * This class contains the logic that is run every time this module
 * is either started or shutdown
 */
public class GroovyActivator implements Activator {

	private Log log = LogFactory.getLog(this.getClass());
	private GroovyShell shell = null;
	private StringWriter out = null;

	/**
	 * @see org.openmrs.module.Activator#startup()
	 */
	public void startup() {
		log.info("Starting Groovy Module");
	}
	
	public GroovyShell getShell() {
		if (shell == null)
			resetShell();
		return shell;
	}
	
	public void resetShell() {
		Binding binding = new Binding();
		out = new StringWriter();
		PrintWriter output = new PrintWriter(out);
		binding.setVariable("out", output);
		binding.setVariable("cohort", Context.getCohortService());
		binding.setVariable("concept", Context.getConceptService());
		binding.setVariable("encounter", Context.getEncounterService());
		binding.setVariable("form", Context.getFormService());
		binding.setVariable("locale", Context.getLocale());
		binding.setVariable("logic", Context.getLogicService());
		binding.setVariable("obs", Context.getObsService());
		binding.setVariable("patient", Context.getPatientService());
		binding.setVariable("person", Context.getPersonService());
		binding.setVariable("user", Context.getUserService());
		shell = new GroovyShell(binding);
	}
	
	public String getBuffer() {
		return out.toString();
	}
	
	public void clearBuffer() {
		if (out != null && out.getBuffer() != null && out.getBuffer().length() > 0)
			out.getBuffer().delete(0, out.getBuffer().length()-1);
	}
	
	/**
	 *  @see org.openmrs.module.Activator#shutdown()
	 */
	public void shutdown() {
		log.info("Shutting down Groovy Module");
		shell = null;
	}
	
}
