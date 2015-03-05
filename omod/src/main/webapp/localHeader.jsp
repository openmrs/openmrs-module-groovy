<%--

    This Source Code Form is subject to the terms of the Mozilla Public License,
    v. 2.0. If a copy of the MPL was not distributed with this file, You can
    obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
    the terms of the Healthcare Disclaimer located at http://openmrs.org/license.

    Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
    graphic logo is a trademark of OpenMRS Inc.

--%>
<ul id="menu">
	<li class="first">
		<a href="${pageContext.request.contextPath}/admin"><spring:message code="admin.title.short"/></a>
	</li>
	<li <c:if test='<%= request.getRequestURI().contains("groovyForm") %>'>class="active"</c:if>>
		<a href="groovy.form">
			<spring:message code="groovy.scripting-form"/>
		</a>
	</li>
	<li <c:if test='<%= request.getRequestURI().contains("groovyScriptList") %>'>class="active"</c:if>>
		<a href="groovy.list">
			<spring:message code="groovy.manage"/>
		</a>
	</li>
</ul>