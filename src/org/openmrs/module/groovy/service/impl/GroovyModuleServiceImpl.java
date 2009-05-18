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
            script.setModified(now);
            script.setModifiedBy(Context.getAuthenticatedUser());
        }
        dao.saveGroovyScript(script);
        return script;
    }
}
