/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.groovy.api;

import org.junit.Test;
import org.openmrs.api.context.Context;
import org.openmrs.module.groovy.GroovyScript;
import org.openmrs.module.groovy.api.service.GroovyService;
import org.openmrs.test.BaseModuleContextSensitiveTest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;

/**
 * Tests {@link {GroovyModuleService}}.
 */
public class GroovyModuleServiceTest extends BaseModuleContextSensitiveTest {
	
	@Test
	public void shouldSetupContext() {
		assertNotNull(Context.getService(GroovyService.class));
	}

	@Test
	public void shouldSaveGroovyScript() {
		GroovyScript groovyScript = new GroovyScript();
		groovyScript.setName("Test Script");
		groovyScript.setScript(getScript());
		Context.getService(GroovyService.class).saveGroovyScript(groovyScript);
		assertThat(groovyScript.getId(), notNullValue());
	}

	@Test
	public void shouldExecuteGroovyScript() {
		String[] eval = Context.getService(GroovyService.class).evaluate(getScript());
		assertThat(eval.length, equalTo(3));
		assertThat(eval[1].trim(), equalTo("2"));
	}

	public String getScript() {
		StringBuilder sb = new StringBuilder();
		sb.append("p = patient.getPatientByUuid(\"da7f524f-27ce-4bb2-86d6-6d1d05312bd5\");");
		sb.append("println p.getId();");
		return sb.toString();
	}
}
