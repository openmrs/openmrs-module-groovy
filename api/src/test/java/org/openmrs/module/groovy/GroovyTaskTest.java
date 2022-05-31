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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openmrs.scheduler.TaskDefinition;
import org.openmrs.test.BaseModuleContextSensitiveTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
public class GroovyTaskTest extends BaseModuleContextSensitiveTest  {

	@Autowired
	GroovyTask task;

	@Test
	public void execute() {
		TaskDefinition def = new TaskDefinition();
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("script name", "hello world");
		def.setProperties(properties );
		task.initialize(def);
		task.execute();
	}
}