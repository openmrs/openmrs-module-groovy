/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.groovy.web.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.groovy.GroovyScript;
import org.openmrs.module.groovy.GroovyUtil;
import org.openmrs.module.groovy.api.service.GroovyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/module/groovy/groovy.list")
public class GroovyScriptListController {

  /**
     * Logger for this class
     */
    protected final Log log = LogFactory.getLog(getClass());

    public GroovyScriptListController() {
    	
    }

    @ModelAttribute("scripts")
    List<GroovyScript> populateScripts() {
        return GroovyUtil.getService().getAllScripts();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String listScripts() {
    	log.info("get list groovy");
        return "/module/groovy/groovyScriptList";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String delete(@RequestParam(value="id",required=true) Integer id, final ModelMap map) {
        GroovyService svc = GroovyUtil.getService();
        GroovyScript script = svc.getScriptById(id);
        if(script != null) {
            svc.deleteGroovyScript(script);
        }
        // return the scripts remaining 
        List<GroovyScript> scripts = svc.getAllScripts();
        map.addAttribute("scripts",scripts);
        return "/module/groovy/groovyScriptList";
    }
}