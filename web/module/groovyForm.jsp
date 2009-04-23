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
<script type="text/javascript"
        src="${pageContext.request.contextPath}/moduleResources/groovy/js/groovy.js"></script>

<link rel="stylesheet" type="text/css"
      href="${pageContext.request.contextPath}/moduleResources/groovy/css/redmond/jquery-ui-1.7.1.custom.css"/>

<script type="text/javascript">
    function groovyOutput(str) {
        $("#groovyOut").html(str);
    }
    function callGroovy() {
        $("#groovyOut").html("<em>Running...</em>")
        var script = editor.getCode();
        DWRGroovyService.eval(script,groovyOutput);

    }
    function resetGroovy() {
        document.getElementById("groovyScript").value = "";
    }
</script>
<p>
    <spring:message code="groovy.info"/>
</p>

<div id="textarea-container" class="border">
    <textarea id="groovyScript" name="groovyScript" cols="140" rows="40"></textarea>
</div>
<input type="button" value="<spring:message code="groovy.go-button"/>" onclick="callGroovy()"/>
&nbsp;&nbsp;
<input type="button" value="<spring:message code="groovy.reset-button"/>" onclick="resetGroovy()"/>
&nbsp;&nbsp;
<a href="http://groovy.codehaus.org/Documentation" target="_groovy_doc"><spring:message
        code="groovy.documentation-link"/></a>
<hr/>
<div id="groovyOut"></div>
<script language="javascript">
    var editor = CodeMirror.fromTextArea('groovyScript', {
        height: "300px",
        parserfile: ["tokenizejavascript.js", "parsejavascript.js"],
        stylesheet: "${pageContext.request.contextPath}/moduleResources/groovy/css/jscolors.css",
        path: "${pageContext.request.contextPath}/moduleResources/groovy/js/",
        continuousScanning: 500,
        lineNumbers: true,
        textWrapping: false,
        tabMode: "spaces"
    });
</script>
<%@ include file="/WEB-INF/template/footer.jsp" %>
