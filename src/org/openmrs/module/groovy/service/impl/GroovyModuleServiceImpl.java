package org.openmrs.module.groovy.service.impl;

import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.api.context.Context;
import org.openmrs.module.groovy.service.GroovyModuleService;
import org.openmrs.module.groovy.GroovyScript;
import org.openmrs.module.groovy.db.hibernate.GroovyModuleDAO;

import java.util.List;
import java.util.Date;

public class GroovyModuleServiceImpl extends BaseOpenmrsService implements GroovyModuleService {

    GroovyModuleDAO dao;

    public void setDao(GroovyModuleDAO dao) {
        this.dao = dao;
    }

    @Override
    public List<GroovyScript> getAllScripts() {
        return dao.getAllScripts();
    }

    @Override
    public GroovyScript getScriptById(Integer id) {
        return dao.getScriptById(id);
    }

    @Override
    public void deleteGroovyScript(GroovyScript script) {
        dao.deleteGroovyScript(script);
    }

    @Override
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
