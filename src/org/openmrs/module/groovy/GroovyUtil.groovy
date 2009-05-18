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
package org.openmrs.module.groovy

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.codehaus.groovy.control.CompilationFailedException
import org.codehaus.groovy.control.MultipleCompilationErrorsException
import org.openmrs.api.APIAuthenticationException
import org.openmrs.api.context.Context
import org.openmrs.module.groovy.service.GroovyModuleService

class GroovyUtil {
  private Log log = LogFactory.getLog(getClass())
  private static StringWriter out


  private static GroovyShell getShell() {
    final GroovyShell shell = new GroovyShell(setBindings())
    return shell
  }

  //TODO: add a better version of this which is driven by a config file.
  private static Binding setBindings() {
    final Binding binding = new Binding()
    out = new StringWriter()
    binding.setVariable("out", new PrintWriter(out))
    binding.setVariable("admin", Context.getAdministrationService())
    binding.setVariable("cohort", Context.getCohortService())
    binding.setVariable("concept", Context.getConceptService())
    binding.setVariable("encounter", Context.getEncounterService())
    binding.setVariable("form", Context.getFormService())
    binding.setVariable("locale", Context.getLocale())
    binding.setVariable("logic", Context.getLogicService())
    binding.setVariable("obs", Context.getObsService())
    binding.setVariable("order", Context.getOrderService())
    binding.setVariable("patient", Context.getPatientService())
    binding.setVariable("patientSet", Context.getPatientSetService())
    binding.setVariable("person", Context.getPersonService())
    binding.setVariable("program", Context.getProgramWorkflowService())
    binding.setVariable("user", Context.getUserService())
    return binding
  }

 static String getBuffer() {
    return out ? out.toString() : ""
  }

 static void clearBuffer() {
    if (out != null && out.getBuffer() != null && out.getBuffer().length() > 0)
      out.getBuffer().delete(0, out.getBuffer().length() - 1)
  }

  /**
   * Delegate method that does the actual parsing of the script.
   * @see GroovyUtil#evaluate(String)
   */
  static Object eval(final String script) throws CompilationFailedException {
    // this should never happen as the DWR method does the same check; but this is an important safety net that
    // must be in place.
    if(!Context.hasPrivilege("Run Groovy Scripts")) {
      throw new APIAuthenticationException("You do not have sufficient privileges to run Groovy Scripts")
    }
    return getShell().parse(script).run()
  }


  /**
   * This method mostly delegates to GroovyUtil#eval(String)
   * @see GroovyUtil#eval(String)
   */
  static String[] evaluate(final String script) {
    final Object result
    StringWriter stacktrace = new StringWriter()
    PrintWriter errWriter = new PrintWriter(stacktrace)
    try {
      clearBuffer()
      result = eval(script)
    } catch (MultipleCompilationErrorsException e) {
      stacktrace << (e.getMessage().replaceFirst("startup failed, Script1.groovy: ", ""))
    } catch (Throwable t) {
      sanitizeStacktrace(t)
      Throwable cause = t
      while (cause != null) {
        cause = cause.getCause()
        if (cause != null)
          sanitizeStacktrace(cause)
      }
      t.printStackTrace(errWriter)
    } catch (Throwable e) {
      final Writer trace = new StringWriter()
      final PrintWriter printWriter = new PrintWriter(trace)
      stacktrace << (e.getMessage().replaceFirst("startup failed, Script1.groovy: ", ""))
    }
    final String output = getBuffer()
    final String res = result ? result.toString() : "null"
    final String trace = stacktrace ? stacktrace.toString() : ""
    final def ret = [res, output, trace] as String[]
    return ret
  }

  public static GroovyModuleService getService() {
    return Context.getService(GroovyModuleService.class)
  }

  /**
   * This method contains code based on groovywebconsole.
   * Code available @
   * http://github.com/glaforge/groovywebconsole/tree/master
   * Special thanks to Guillaume Laforge for granting the
   * permission to use this as I wished.
   */
  static def sanitizeStacktrace(final def t) {
    //TODO: add a better version driven by a configuration page
    final def filtered = [
            'org.apache.', 'org.mortbay.',
            'java.', 'javax.', 'sun.',
            'groovy.', 'org.codehaus.groovy.',
            'org.springframework.', 'org.directwebremoting.',
            'org.openmrs.'
    ]
    final def trace = t.getStackTrace()
    final def newTrace = []
    trace.each {stackTraceElement ->
      if (filtered.every { !stackTraceElement.className.startsWith(it) }) {
        newTrace << stackTraceElement
      }
    }
    final def clean = newTrace.toArray(newTrace as StackTraceElement[])
    t.stackTrace = clean
  }
}