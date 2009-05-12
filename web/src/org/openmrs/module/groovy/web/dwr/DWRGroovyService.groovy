package org.openmrs.module.groovy.web.dwr;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.codehaus.groovy.control.MultipleCompilationErrorsException;
import org.openmrs.module.ModuleFactory;
import org.openmrs.module.groovy.GroovyActivator
import org.openmrs.module.groovy.GroovyUtil;

public class DWRGroovyService {

  public String[] eval(String script) {
    println Arrays.toString(GroovyUtil.evaluate(script)) 
    return GroovyUtil.evaluate(script)
  }
}