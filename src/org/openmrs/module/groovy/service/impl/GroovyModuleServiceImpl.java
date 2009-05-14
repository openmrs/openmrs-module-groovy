package org.openmrs.module.groovy.service.impl;

import java.util.Date;
import java.util.List;

import org.openmrs.api.context.Context;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.groovy.GroovyScript;
import org.openmrs.module.groovy.db.hibernate.GroovyModuleDAO;
import org.openmrs.module.groovy.service.GroovyModuleService;

public class GroovyModuleServiceImpl extends BaseOpenmrsService implements GroovyModuleService {

    GroovyModuleDAO dao;

    public void setDao(GroovyModuleDAO dao) {
        this.dao = dao;
    }

    public List<GroovyScript> getAllScripts() {
        return dao.getAllScripts();
    }

    public GroovyScript getScriptById(Integer id) {
        return dao.getScriptById(id);
    }

    public void deleteGroovyScript(GroovyScript script) {
        dao.deleteGroovyScript(script);
    }

    public GroovyScript saveGroovyScript(GroovyScript script) {
        Date now = new Date();
        script.setCreated(now);
        script.setCreator(Context.getAuthenticatedUser());
        if (script.getId() != null) {
            System.out.println("This is not a new script");
            script.setModified(now);
            script.setModifiedBy(Context.getAuthenticatedUser());
        }
        for(GroovyScript s : getAllScripts()) {

        }
        dao.saveGroovyScript(script);
        return script;
    }
}
