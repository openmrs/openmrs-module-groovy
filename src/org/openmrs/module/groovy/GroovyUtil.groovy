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

import groovy.lang.Binding
import groovy.lang.GroovyShell
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.codehaus.groovy.control.CompilationFailedException
import org.codehaus.groovy.control.MultipleCompilationErrorsException
import org.openmrs.api.context.Context
import org.openmrs.module.groovy.service.GroovyModuleService

import java.io.PrintWriter
import java.io.StringWriter
import java.io.Writer

public class GroovyUtil {
  private Log log = LogFactory.getLog(getClass())
  private static StringWriter out


  private static GroovyShell getShell() {
    GroovyShell shell = new GroovyShell(setBindings())
    return shell
  }

  private static Binding setBindings() {
    Binding binding = new Binding()
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

  public static String getBuffer() {
    return out ? out.toString() : ""
  }

  public static void clearBuffer() {
    if (out != null && out.getBuffer() != null && out.getBuffer().length() > 0)
      out.getBuffer().delete(0, out.getBuffer().length() - 1)
  }

  static Object eval(final String script) throws CompilationFailedException {
    return getShell().parse(script).run()
  }

  public static String[] evaluate(final String script) {
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
    final def ret = new String[3]
    ret[0] = res
    ret[1] = output
    ret[2] =  stacktrace ? stacktrace.toString() : ""
    return ret
  }

  public static GroovyModuleService getService() {
    return Context.getService(GroovyModuleService.class)
  }

  static def sanitizeStacktrace(t) {
    def filtered = [
            'org.apache.', 'org.mortbay.',
            'java.', 'javax.', 'sun.',
            'groovy.', 'org.codehaus.groovy.',
            'org.springframework.','org.directwebremoting.'
    ]
    def trace = t.getStackTrace()
    def newTrace = []
    trace.each {stackTraceElement ->
      if (filtered.every { !stackTraceElement.className.startsWith(it) }) {
        newTrace << stackTraceElement
      }
    }
    def clean = newTrace.toArray(newTrace as StackTraceElement[])
    t.stackTrace = clean
  }
}
