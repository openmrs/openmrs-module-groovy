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

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;
import org.openmrs.module.groovy.service.GroovyModuleService;
import org.openmrs.module.groovy.GroovyScript;
import org.openmrs.module.groovy.GroovyUtil;
import org.openmrs.api.context.Context;

/**
 * This controller backs and saves the Groovy module settings
 */
public class GroovyFormController extends SimpleFormController {

    /**
     * Logger for this class and subclasses
     */
    protected final Log log = LogFactory.getLog(getClass());

    @Override
    protected Map<String, Object> referenceData(HttpServletRequest request, Object obj, Errors err) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("scripts", GroovyUtil.getService().getAllScripts());
        return map;
    }


    @Override
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object object, BindException exceptions) throws Exception {
        GroovyModuleService svc = GroovyUtil.getService();
        GroovyScript script = (GroovyScript) object;
        System.out.println(script.getId());
        script = svc.saveGroovyScript(script);
        return new ModelAndView(new RedirectView(getSuccessView() + "?id=" + script.getId()));
    }

    /**
     * This class returns the form backing object.  This can be a string, a boolean, or a normal
     * java pojo.
     *
     * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
     */
    @Override
    protected GroovyScript formBackingObject(HttpServletRequest request) throws Exception {
        GroovyModuleService svc = GroovyUtil.getService();
        if (request.getParameter("id") != null) {
            try {
                final Integer id = Integer.valueOf(request.getParameter("id"));
                return svc.getScriptById(id);
            } catch (NumberFormatException e) {
                return new GroovyScript();
            }
        }
        return new GroovyScript();

    }
}
