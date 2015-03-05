/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.groovy.web.controller

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.groovy.GroovyScript;
import org.openmrs.module.groovy.GroovyUtil;
import org.openmrs.module.groovy.web.validators.GroovyScriptValidator 
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute 
import org.springframework.web.bind.annotation.RequestMapping 
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam 
import org.springframework.web.bind.support.SessionStatus;

import org.springframework.web.bind.*;

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

    @RequestMapping(method = RequestMethod.GET)    
    public String setupForm(@RequestParam(value="id",required=false) Integer id, ModelMap model, final HttpServletRequest request) {
		log.info("get form groovy");
        GroovyScript script = id != null ? GroovyUtil.getService().getScriptById(id) : new GroovyScript();
        model.addAttribute("script", script);
        return "/module/groovy/groovyForm";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String processSubmit(@ModelAttribute("script") GroovyScript script, BindingResult result, SessionStatus status, HttpServletRequest request) throws ServletRequestBindingException {
        new GroovyScriptValidator().validate(script, result);
        if (result.hasErrors()) {
            return "/module/groovy/groovyForm";
        } else if (ServletRequestUtils.getStringParameter(request, "saveAsNew")) {
            script.setId(null);
            GroovyUtil.getService().saveGroovyScript(script);
        } else {
            GroovyUtil.getService().saveGroovyScript(script);

        }
        return "redirect:/module/groovy/groovy.form?id=${script.id}"; 
    }
}