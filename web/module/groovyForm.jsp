<%--
  The contents of this file are subject to the OpenMRS Public License
  Version 1.0 (the "License"); you may not use this file except in
  compliance with the License. You may obtain a copy of the License at
  http://license.openmrs.org

  Software distributed under the License is distributed on an "AS IS"
  basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
  License for the specific language governing rights and limitations
  under the License.

  Copyright (C) OpenMRS, LLC.  All Rights Reserved.

--%>
<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<openmrs:require privilege="Run Groovy Scripts" otherwise="/login.htm" redirect="/module/groovy/groovy.form"/>

<%@ include file="/WEB-INF/template/header.jsp" %>
<openmrs:htmlInclude file="/dwr/interface/DWRGroovyService.js"/>
<openmrs:htmlInclude file="/dwr/engine.js"/>
<openmrs:htmlInclude file="/dwr/util.js"/>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/moduleResources/groovy/js/jquery-1.3.2.min.js"></script>
<script src="${pageContext.request.contextPath}/moduleResources/groovy/js/jquery-ui-1.7.1.custom.min.js"
        type="text/javascript"></script>

<script type="text/javascript"
        src="${pageContext.request.contextPath}/moduleResources/groovy/js/codemirror.js"></script>

<script type="text/javascript"
        src="${pageContext.request.contextPath}/moduleResources/groovy/js/mirrorframe.js"></script>

<link rel="stylesheet" type="text/css"
      href="${pageContext.request.contextPath}/moduleResources/groovy/css/redmond/jquery-ui-1.7.1.custom.css"/>

<link rel="stylesheet" type="text/css"
      href="${pageContext.request.contextPath}/moduleResources/groovy/css/main.css"/>

<script type="text/javascript"
        src="${pageContext.request.contextPath}/moduleResources/groovy/js/main.js"></script>
<p>
    <a href="groovy.list"><spring:message code="groovy.manage"/></a> |
    <a href="groovy.form"><spring:message code="groovy.new"/></a>
</p>

<p>
    <spring:message code="groovy.info"/><br/>
    <br/>
    <spring:message code="groovy.info2"/><br/>
</p>
<form:form id="scriptForm" commandName="script" name="scriptForm" onsubmit="$('#dialog').appendTo('form[@name=scriptForm]');">
    <form:errors path="*" cssClass="error"/>
    <c:if test="${ fn:length(script.name) > 0}">
        <span id="header"><h1 align="center">${script.name}</h1></span></h1>
    </c:if>
    <div id="textarea-container" class="border">
        <form:textarea path="script" cols="140" rows="40" id="groovyScript"/>
    </div>
    <label for="name">Script Name:</label>
    <input id="name" name="name" value="${script.name}"/>
    <%-- this is request specific --%>
    <spring:message code="groovy.saveAsNew"/> <input type="checkbox" name="saveAsNew" value="yes"/>

    <div id="button-bar">
	    <input id="executeButton" type="button" value="<spring:message code="groovy.execute"/>"/>&nbsp;
        <input type="submit" value="<spring:message code="groovy.save"/>"/>
        <br/>
        <a href="http://groovy.codehaus.org/Documentation" target="_groovy_doc"><spring:message
        code="groovy.documentation-link"/></a>&nbsp;
    </div>    
</form:form>

<div id="tabs">
    <ul>
    	<li><a href="#tabs-result"><spring:message code="groovy.result-tab"/></a></li>
    	<li><a href="#tabs-output"><spring:message code="groovy.output-tab"/></a></li>
    	<li><a href="#tabs-stacktrace"><spring:message code="groovy.stacktrace-tab"/></a></li>
    </ul>

    <div id="tabs-result">
        <pre id="result" class="border hidden"></pre>
    </div>

    <div id="tabs-output">
        <pre id="output" class="border hidden"></pre>
    </div>                                                                              
    

    <div id="tabs-stacktrace">
        <pre id="stacktrace" class="border hidden"></pre>
    </div>                                      </div>
<div id="running-html" class="hidden"><h1><spring:message code="groovy.running"/></h1></div>
<div id="noPrivileges" class="hidden"><h1><spring:message code="groovy.insufficentPrivileges"/></h1></div>
<script language="javascript">                                                                                                                 
    var editor = CodeMirror.fromTextArea('groovyScript', {
        height: "300px",
        parserfile: ["tokenizejavascript.js", "parsejavascript.js"],
        stylesheet: "${pageContext.request.contextPath}/moduleResources/groovy/css/jscolors.css",
        path: "${pageContext.request.contextPath}/moduleResources/groovy/js/",
        continuousScanning: 500,
        lineNumbers: true,
        textWrapping: false,
        autoMatchParens: true,
        tabMode: "spaces",
        submitFunction: function() {
            $("#executeButton").click();
        },
        saveFunction:function() {
            document.forms["scriptForm"].submit();
        }
    });
</script>
<%@ include file="/WEB-INF/template/footer.jsp" %>