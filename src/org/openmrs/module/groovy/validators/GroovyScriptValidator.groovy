package org.openmrs.module.groovy.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.openmrs.module.groovy.GroovyScript;
import org.openmrs.module.groovy.GroovyUtil
import org.codehaus.groovy.control.CompilationFailedException;

public class GroovyScriptValidator implements Validator {
  public boolean supports(Class clazz) {
    return GroovyScript.class.equals(clazz);
  }

  public void validate(Object obj, Errors errors) {
    ValidationUtils.rejectIfEmpty(errors, "name", "error.null");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "script", "error.null");
    
    GroovyScript script = (GroovyScript) obj;
    if (script.getScript() != null) {
      try {
        Object o = GroovyUtil.eval(script.getScript());
      } catch (CompilationFailedException ex) {
        errors.rejectValue("script", null, ex.getMessage());
      }
    }
  }
}