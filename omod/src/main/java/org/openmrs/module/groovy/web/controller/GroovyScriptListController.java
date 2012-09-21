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