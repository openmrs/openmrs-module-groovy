/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 * <p>
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.groovy;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import org.codehaus.groovy.control.CompilationFailedException;
import org.codehaus.groovy.control.MultipleCompilationErrorsException;
import org.openmrs.api.APIAuthenticationException;
import org.openmrs.api.context.Context;
import org.openmrs.module.ModuleFactory;
import org.openmrs.module.groovy.api.service.GroovyService;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class GroovyUtil {

    private static StringWriter out;

    private static GroovyShell getShell() {
        return new GroovyShell(setBindings());
    }

    //TODO: add a better version of this which is driven by a config file.
    private static Binding setBindings() {
        Binding binding = new Binding();
        out = new StringWriter();
        binding.setVariable("out", new PrintWriter(out));
        binding.setVariable("admin", Context.getAdministrationService());
        binding.setVariable("cohort", Context.getCohortService());
        binding.setVariable("concept", Context.getConceptService());
        binding.setVariable("encounter", Context.getEncounterService());
        binding.setVariable("form", Context.getFormService());
        binding.setVariable("locale", Context.getLocale());
        binding.setVariable("location", Context.getLocationService());
        if (ModuleFactory.isModuleStarted("logic")) {
            binding.setVariable("logic", Context.getLogicService());
        }
        binding.setVariable("obs", Context.getObsService());
        binding.setVariable("order", Context.getOrderService());
        binding.setVariable("patient", Context.getPatientService());
        binding.setVariable("person", Context.getPersonService());
        binding.setVariable("program", Context.getProgramWorkflowService());
        binding.setVariable("user", Context.getUserService());
        binding.setVariable("fn", new GroovyScriptFunctions());
        return binding;
    }

    private static class GroovyScriptFunctions {
        public static Object sql(final String s) {
            return sql(s, true); // default to read-only queries
        }

        public static Object sql(final String s, final boolean readOnly) {
            return Context.getAdministrationService().executeSQL(s, readOnly);
        }
    }

    public static String getBuffer() {
        return out != null ? out.toString() : "";
    }

    public static void clearBuffer() {
        if (out != null && out.getBuffer() != null && out.getBuffer().length() > 0) {
            out.getBuffer().delete(0, out.getBuffer().length() - 1);
        }
    }

    /**
     * Delegate method that does the actual parsing of the script.
     * @see GroovyUtil#evaluate(String)
     */
    public static Object eval(final String script) throws CompilationFailedException {
        // this should never happen as the DWR method does the same check; but this is an important safety net that
        // must be in place.
        if (!Context.hasPrivilege("Run Groovy Scripts")) {
            throw new APIAuthenticationException("You do not have sufficient privileges to run Groovy Scripts");
        }
        return getShell().parse(script).run();
    }


    /**
     * This method mostly delegates to GroovyUtil#eval(String)
     * @see GroovyUtil#eval(String)
     */
    public static String[] evaluate(final String script) {
        Object result = null;
        StringWriter stacktrace = new StringWriter();
        PrintWriter errWriter = new PrintWriter(stacktrace);
        try {
            clearBuffer();
            result = eval(script);
        }
        catch (MultipleCompilationErrorsException e) {
            stacktrace.append(e.getMessage().replaceFirst("startup failed, Script1.groovy: ", ""));
        }
        catch (Throwable t) {
            sanitizeStacktrace(t);
            Throwable cause = t;
            while (cause != null) {
                cause = cause.getCause();
                if (cause != null) {
                    sanitizeStacktrace(cause);
                }
            }
            t.printStackTrace(errWriter);
        }
        final String output = getBuffer();
        final String res = result != null ? result.toString() : "null";
        final String trace = stacktrace != null ? stacktrace.toString() : "";
        final String[] ret = {res, output, trace};
        return ret;
    }

    public static GroovyService getService() {
        return Context.getService(GroovyService.class);
    }

    /**
     * This method contains code based on groovywebconsole.
     * Code available @
     * http://github.com/glaforge/groovywebconsole/tree/master
     * Special thanks to Guillaume Laforge for granting the
     * permission to use this as I wished.
     */
    public static void sanitizeStacktrace(Throwable t) {
        //TODO: add a better version driven by a configuration page
        final String[] filtered = {
                "org.apache.", "org.mortbay.",
                "java.", "javax.", "sun.",
                "groovy.", "org.codehaus.groovy.",
                "org.springframework.", "org.directwebremoting.",
                "org.openmrs."
        };
        List<StackTraceElement> l = new ArrayList<StackTraceElement>();
        for (StackTraceElement stackTraceElement : t.getStackTrace()) {
            String className = stackTraceElement.getClassName();
            boolean include = true;
            for (String f : filtered) {
                if (className.startsWith(f)) {
                    include = false;
                }
            }
            if (include) {
                l.add(stackTraceElement);
            }
        }
        t.setStackTrace(l.toArray(new StackTraceElement[]{}));
    }
}