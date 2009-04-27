package org.openmrs.module.groovy.service;

import org.openmrs.module.groovy.GroovyScript;
import org.openmrs.api.OpenmrsService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface GroovyModuleService extends OpenmrsService {
    
    @Transactional(readOnly=true)
    List<GroovyScript> getAllScripts();

    @Transactional(readOnly=true)
    GroovyScript getScriptById(Integer id);

    @Transactional
    GroovyScript saveGroovyScript(GroovyScript script);

    @Transactional
    void deleteGroovyScript(GroovyScript script);
}
