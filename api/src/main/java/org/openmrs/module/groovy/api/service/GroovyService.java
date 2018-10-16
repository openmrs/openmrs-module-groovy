/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.groovy.api.service;

import org.openmrs.api.OpenmrsService;
import org.openmrs.module.groovy.GroovyScript;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * This service exposes module's core functionality. It is a Spring managed bean which is configured in moduleApplicationContext.xml.
 * <p>
 * It can be accessed only via Context:<br>
 * <code>
 * Context.getService(GroovyModuleService.class).someMethod();
 * </code>
 * 
 * @see org.openmrs.api.context.Context
 */
@Transactional
public interface GroovyService extends OpenmrsService {
     
	@Transactional(readOnly=true)
    List<GroovyScript> getAllScripts();

    @Transactional(readOnly=true)
    GroovyScript getScriptById(Integer id);
    @Transactional(readOnly=true)
    GroovyScript getScript(String scriptName);
    @Transactional
    GroovyScript saveGroovyScript(GroovyScript script);

    @Transactional
    void deleteGroovyScript(GroovyScript script);

    /**
     * Delegates to the GroovyUtil.evaluate(String) method.
     * @param script
     * @return
     */
    String[] evaluate(final String script);

	
}