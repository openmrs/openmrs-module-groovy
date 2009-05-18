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
package org.openmrs.module.groovy.web.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.validation.BindingResult;
import org.openmrs.module.groovy.GroovyScript;
import org.openmrs.module.groovy.GroovyUtil;
import org.openmrs.module.groovy.validators.GroovyScriptValidator;
import org.openmrs.module.groovy.service.GroovyModuleService;
import org.openmrs.api.context.Context;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;

/**
 * This controller backs and saves the Groovy module settings
 */
@Controller
@RequestMapping("/module/groovy/groovy.form")
public class GroovyFormController {

    /**
     * Logger for this class
     */
    protected final Log log = LogFactory.getLog(getClass());


    @RequestMapping(method = RequestMethod.GET )    
    public String setupForm(@RequestParam(value="id",required=false) Integer id, ModelMap model, final HttpServletRequest request) {
        GroovyScript script = id != null ? Context.getService(GroovyModuleService.class).getScriptById(id) : new GroovyScript();
        model.addAttribute("script", script);
        return "/module/groovy/groovyForm";
    }
    @RequestMapping(method = RequestMethod.POST)
        public String processSubmit(@ModelAttribute("script") GroovyScript script, BindingResult result, SessionStatus status) {
        new GroovyScriptValidator().validate(script, result);
        if(result.hasErrors()) {
            return "/module/groovy/groovyForm";
        } else {
            GroovyUtil.getService().saveGroovyScript(script);
            return "redirect:/module/groovy/groovy.form"+
                    "?id="+script.getId();
        }
    }
}




