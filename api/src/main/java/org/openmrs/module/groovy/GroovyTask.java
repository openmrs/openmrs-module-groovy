/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.groovy;

import java.util.Date;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.groovy.GroovyScript;
import org.openmrs.module.groovy.GroovyUtil;
import org.openmrs.module.groovy.api.service.GroovyService;
import org.openmrs.scheduler.tasks.AbstractTask;
import org.springframework.stereotype.Component;

@Component
public class GroovyTask extends AbstractTask {
	protected final Log log = LogFactory.getLog(this.getClass());
	
	@Override
	public void execute() {
		GroovyService groovyService = GroovyUtil.getService();
		log.info("Executing groovy task at " + new Date());
		Map<String, String> properties = this.getTaskDefinition().getProperties();
		String script = properties.get("script name");
		
		log.info("Groovy Script to be execute: " + script);
		GroovyScript groovyScript = groovyService.getScript(script);
		
		if (groovyScript != null){
			groovyService.evaluate(groovyScript.getScript());
		} else {
			log.error("Groovy Script not found: " + script);
		}
	}
	
}
