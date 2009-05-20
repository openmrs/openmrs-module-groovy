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
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "groovy.name.error");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "script", "groovy.script.error");
    
    GroovyScript script = (GroovyScript) obj;
    if (script.getScript() != null) {
      try {
        // we don't care about the return value; just whether an exception occured or not.
        GroovyUtil.eval(script.getScript());
      } catch (CompilationFailedException ex) {
        errors.rejectValue("script", null, ex.getMessage());
      }
    }
  }
}