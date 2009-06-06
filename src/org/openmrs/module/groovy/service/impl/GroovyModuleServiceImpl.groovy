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
import org.openmrs.User;

import java.util.List;
import java.util.Date;


public class GroovyModuleServiceImpl extends BaseOpenmrsService implements GroovyModuleService {

    GroovyModuleDAO dao;

    public void setDao(final GroovyModuleDAO dao) {
        this.dao = dao;
    }

    
    public List<GroovyScript> getAllScripts() {
        return dao.getAllScripts();
    }

    
    public GroovyScript getScriptById(final Integer id) {
        return dao.getScriptById(id);
    }

    
    public void deleteGroovyScript(final GroovyScript script) {
        dao.deleteGroovyScript(script);
    }

    
    public GroovyScript saveGroovyScript(final GroovyScript script) {
        final Date now = new Date();
        if(!script.created)
          script.setCreated(now);
        final User authenticatedUser = Context.getAuthenticatedUser();
        if(!script.creator)
          script.setCreator(authenticatedUser);
        if (script.getId() != null) {
            script.setModified(now);
            final User modifiedBy = Context.getAuthenticatedUser();
            script.setModifiedBy(modifiedBy);
        }
        dao.saveGroovyScript(script);
        return script;
    }
}
