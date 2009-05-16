<%@ include file="/WEB-INF/template/include.jsp" %>

<openmrs:require privilege="Groovy Scripting" otherwise="/login.htm" redirect="/module/groovy/groovy.form"/>

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

<div id="textarea-container" class="border">
    <textarea id="groovyScript" name="script" cols="140" rows="40">${script.script}</textarea>
</div>
<div id="button-bar">
	<input id="executeButton" type="button" value="<spring:message code="groovy.execute"/>"/>&nbsp;
	<a href="http://groovy.codehaus.org/Documentation" target="_groovy_doc"><spring:message
        code="groovy.documentation-link"/></a>&nbsp;
    <input type="text" value="${script.name}"/> <input type="submit" value="<spring:message code="groovy.save"/>"/>
</div>
<div id="tabs">
    <ul>
    	<li><a href="#tabs-result">Result</a></li>
    	<li><a href="#tabs-output">Output</a></li>
    	<li><a href="#tabs-stacktrace">Stacktrace</a></li>
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
        }
    });
</script>
<%@ include file="/WEB-INF/template/footer.jsp" %>
