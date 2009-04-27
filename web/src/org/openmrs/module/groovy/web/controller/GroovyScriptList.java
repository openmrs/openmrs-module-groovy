package org.openmrs.module.groovy.web.controller;

import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.validation.Errors;
import org.openmrs.module.groovy.GroovyScript;
import org.openmrs.module.groovy.GroovyUtil;
import org.openmrs.module.groovy.service.GroovyModuleService;
import org.openmrs.api.context.Context;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class GroovyScriptList extends SimpleFormController {
    @Override
    protected Map<String,Object> referenceData(HttpServletRequest httpServletRequest, Object o, Errors errors) throws Exception {
        Map<String,Object> map = new HashMap<String,Object>();
        GroovyModuleService svc = GroovyUtil.getService();
        map.put("scripts",svc.getAllScripts());
        return map;
    }
}
