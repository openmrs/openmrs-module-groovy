<%--

    This Source Code Form is subject to the terms of the Mozilla Public License,
    v. 2.0. If a copy of the MPL was not distributed with this file, You can
    obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
    the terms of the Healthcare Disclaimer located at http://openmrs.org/license.

    Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
    graphic logo is a trademark of OpenMRS Inc.

--%>
<%@ include file="/WEB-INF/template/include.jsp" %>
<openmrs:require privilege="List Groovy Scripts" otherwise="/login.htm" redirect="/module/groovy/groovy.list"/>
<%@ include file="/WEB-INF/template/header.jsp" %>
<link rel="stylesheet" type="text/css"
      href="${pageContext.request.contextPath}/moduleResources/groovy/css/main.css"/>

<%@ include file="localHeader.jsp" %>

<p>
    <spring:message code="groovy.list.info"/>
</p>

<c:choose>
    <c:when test="${ fn:length(scripts) > 0}">
        <table style="border: dashed 1px #000;" border="1px; " >
            <tr>
                <td>Name</td>                
                <td>Creator</td>
                <td>Date Created</td>
                <td>Last Modified By</td>
                <td>Date Last Modifed</td>
                <td>Action</td>

            </tr>
            <c:forEach var="script" items="${scripts}" varStatus="status">
                <form method="post">
                    <tr>
                        <td>
                            <a href="groovy.form?id=${script.id}">${script.name}</a>
                        </td>
                        <td>${script.creator}</td>
                        <td>${script.created}</td>
                        <td>${script.modifiedBy}</td>
                        <td>${script.modified}</td>
                        <input type="hidden" value="${script.id}" name="id"/>
                        <td>
                            <input type="submit" value="<spring:message code="groovy.delete"/>"/>
                        </td>
                    </tr>
                </form>              
            </c:forEach>          
        </table>
    </c:when>
    <c:otherwise>
        <h1 style="color:#fff;border:1px dashed #000"><a href="groovy.form"><spring:message code="groovy.list.noscripts"/></a></h1>
    </c:otherwise>
</c:choose>
<%@ include file="/WEB-INF/template/footer.jsp" %>