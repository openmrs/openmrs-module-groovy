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
    <spring:message code="groovy.info"/><br/>
</p>
<p></p>
<form:form id="scriptForm" commandName="script">
    <form:errors path="script" cssClass="error"/>
    <div id="textarea-container" class="border">
        <form:textarea path="script" cols="140" rows="40" id="groovyScript"/>
    </div>

    <div id="button-bar">
	    <input id="executeButton" type="button" value="<spring:message code="groovy.execute"/>"/>&nbsp;&nbsp;        
        <label for="name">Script Name:</label>
        <form:input path="name" autocomplete="off" id="name"/>
        <form:errors cssClass="error" path="name"/>
        <input type="submit" id="save" value="<spring:message code="groovy.save"/>"/>
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
    </div>
</div>
<div id="running-html" style="display:none"><h1><spring:message code="groovy.running"/></h1></div>
<div id="noPrivileges" style="display:none"><h1><spring:message code="groovy.insufficentPrivileges"/></h1></div>
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
            $("#scriptForm").submit();
        }
    });
</script>
<%@ include file="/WEB-INF/template/footer.jsp" %>