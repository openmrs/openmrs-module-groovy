<%@ include file="/WEB-INF/template/include.jsp"%>

<openmrs:require privilege="Groovy Scripting" otherwise="/login.htm" redirect="/module/groovy/groovy.form" />

<%@ include file="/WEB-INF/template/header.jsp"%>

<openmrs:htmlInclude file="/dwr/interface/DWRGroovyService.js" />
<openmrs:htmlInclude file="/dwr/engine.js" />
<openmrs:htmlInclude file="/dwr/util.js" />

<script type="text/javascript">
function groovyOutput(str) {
	document.getElementById("groovyOut").innerHTML = str;
}
function groovyError(message) {
	document.getElementById("groovyOut").innertHTML = message;
}
function callGroovy() {
	document.getElementById("groovyOut").innerHTML = "<em>Running...</em>";
	DWRGroovyService.eval(
		document.getElementById("groovyScript").value,
		{ 
			callback: groovyOutput,
			errorHandler: groovyError
		}
		);
}
function resetGroovy() {
	document.getElementById("groovyScript").value = "";
	DWRGroovyService.reset();
}
</script>

<p>
  <spring:message code="groovy.info"/>
</p>
<textarea id="groovyScript" cols="80" rows="10"></textarea> <br/>
<input type="button" value="<spring:message code="groovy.go-button"/>" onclick="callGroovy()" />
&nbsp;&nbsp;
<input type="button" value="<spring:message code="groovy.reset-button"/>" onclick="resetGroovy()" />
&nbsp;&nbsp;
<a href="http://groovy.codehaus.org/Documentation" target="_groovy_doc"><spring:message code="groovy.documentation-link" /></a>
<hr />
<div id="groovyOut"></div>

<%@ include file="/WEB-INF/template/footer.jsp"%>
