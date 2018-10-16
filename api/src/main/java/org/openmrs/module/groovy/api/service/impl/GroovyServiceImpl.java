/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.groovy.api.service.impl;

import org.openmrs.User;
import org.openmrs.api.context.Context;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.groovy.GroovyScript;
import org.openmrs.module.groovy.GroovyUtil;
import org.openmrs.module.groovy.api.db.GroovyDAO;
import org.openmrs.module.groovy.api.service.GroovyService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Transactional
public class GroovyServiceImpl extends BaseOpenmrsService implements GroovyService {

	private GroovyDAO dao;
	public void setDao(final GroovyDAO dao) {
		this.dao = dao;
	}

	public List<GroovyScript> getAllScripts() {
		return dao.getAllScripts();
	}

	@Override
	public GroovyScript getScriptById(final Integer id) {
		return dao.getScript(id);
	}

	public void deleteGroovyScript(final GroovyScript script) {
		dao.deleteGroovyScript(script);
	}

	public GroovyScript saveGroovyScript(final GroovyScript script) {
	    if (script == null) {
            throw new IllegalArgumentException("script cannot be null");
        }
		final Date now = new Date();
        final User authenticatedUser = Context.getAuthenticatedUser();

	    if (script.getCreated() == null) {
	        script.setCreated(now);
        }
        if (script.getCreator() == null) {
            script.setCreator(authenticatedUser);
        }
        if (script.getId() != null) {
            script.setModified(now);
            script.setModifiedBy(authenticatedUser);
        }
		dao.saveGroovyScript(script);
		return script;
	}

	/**
	 * Delegate method
	 * @param script
	 * @return an array containing: the result, output and stack trace (if applicable).
	 */
	public String[] evaluate(String script) {
		return GroovyUtil.evaluate(script);
	}

	@Override
	public GroovyScript getScript(String scriptName) {
		return dao.getScript(scriptName);
	}
}
