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
package org.openmrs.module.groovy.api.service.impl;

import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.api.context.Context;
import org.openmrs.module.groovy.api.db.GroovyDAO;
import org.openmrs.module.groovy.api.service.GroovyService;
import org.openmrs.module.groovy.GroovyScript;
import org.openmrs.User;

import java.util.List;
import java.util.Date
import org.openmrs.module.groovy.GroovyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class GroovyServiceImpl extends BaseOpenmrsService implements GroovyService {
	@Autowired
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
		// let's prevent a lovely NPE from occuring.
		if (!script)
			throw new IllegalArgumentException("script cannot be null");
		final Date now = new Date();
		if (!script.created)
			script.setCreated(now);
		final User authenticatedUser = Context.getAuthenticatedUser();
		if (!script.creator)
			script.setCreator(authenticatedUser);
		if (script.id) {
			script.setModified(now);
			final User modifiedBy = Context.getAuthenticatedUser();
			script.setModifiedBy(modifiedBy);
		}
		dao.saveGroovyScript(script);
		return script;
	}

	/**
	 * Delegate method
	 * @param script
	 * @return an array containing: the result, output and stack trace (if applicable).
	 */
	String[] evaluate(String script) {
		return GroovyUtil.evaluate(script);
	}


	@Override
	public GroovyScript getScript(String scriptName) {
		return dao.getScript(scriptName);
	}


}
