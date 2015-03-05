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

import java.util.Map;

import junit.framework.TestCase;

import org.openmrs.module.Extension.MEDIA_TYPE;
import org.openmrs.module.groovy.web.extension.html.AdminList;

/**
 * This test validates the AdminList extension class
 */
public class AdminListExtensionTest extends TestCase {

	/**
	 * Get the links for the extension class
	 */
	public void testValidatesLinks() {
		AdminList ext = new AdminList();
		
		Map<String, String> links = ext.getLinks();
		
		assertNotNull("Some links should be returned", links);
		
		assertTrue("There should be a positive number of links", links.values().size() > 0);
	}
	
	/**
	 * Check the media type of this extension class
	 */
	public void testMediaTypeIsHtml() {
		AdminList ext = new AdminList();
		
		assertTrue("The media type of this extension should be html", ext.getMediaType().equals(MEDIA_TYPE.html));
	}
	
}
