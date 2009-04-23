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
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.groovy.control.CompilationFailedException;
import org.openmrs.api.context.Context;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

public class GroovyUtil {
    private Log log = LogFactory.getLog(getClass());


    private static GroovyShell getShell() {
        GroovyShell shell = new GroovyShell(setBindings());
        return shell;
    }

    private static Binding setBindings() {
        Binding binding = new Binding();
        binding.setVariable("admin", Context.getAdministrationService());
        binding.setVariable("cohort", Context.getCohortService());
        binding.setVariable("concept", Context.getConceptService());
        binding.setVariable("encounter", Context.getEncounterService());
        binding.setVariable("form", Context.getFormService());
        binding.setVariable("locale", Context.getLocale());
        binding.setVariable("logic", Context.getLogicService());
        binding.setVariable("obs", Context.getObsService());
        binding.setVariable("order", Context.getOrderService());
        binding.setVariable("patient", Context.getPatientService());
        binding.setVariable("patientSet", Context.getPatientSetService());
        binding.setVariable("person", Context.getPersonService());
        binding.setVariable("program", Context.getProgramWorkflowService());
        binding.setVariable("user", Context.getUserService());
        return binding;
    }

    private static Object eval(final String script) throws CompilationFailedException {
        return getShell().parse(script).run();
    }

    public static String evaluate(final String script) {
        final Object result;
        try {
            result = eval(script);
        } catch (CompilationFailedException e) {
            final Writer trace = new StringWriter();
            final PrintWriter printWriter = new PrintWriter(trace);
            e.printStackTrace(printWriter);
            return "<b>Exception:</b> " + e.getMessage() + "<br/>" + trace.toString();
        }
        return result == null ? "" : result.toString();
    }    
}
