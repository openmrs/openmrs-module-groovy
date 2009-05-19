<%@ include file="/WEB-INF/template/include.jsp" %>
<openmrs:require privilege="List Groovy Scripts" otherwise="/login.htm" redirect="/module/groovy/groovy.list"/>
<%@ include file="/WEB-INF/template/header.jsp" %>
<link rel="stylesheet" type="text/css"
      href="${pageContext.request.contextPath}/moduleResources/groovy/css/main.css"/>

<p/>
<spring:message code="groovy.list.info"/>
<c:choose>
    <c:when test="${ fn:length(scripts) > 0}">
        <table style="border: dashed 1px #000" border="1px" :>
            <tbody>

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
                <br/>
            </c:forEach>
            </tbody>
        </table>
    </c:when>
    <c:otherwise>
        <h1 style="color:#fff;border:1px dashed #000"><spring:message code="groovy.list.noscripts"/></a></h1>
    </c:otherwise>
</c:choose>
<%@ include file="/WEB-INF/template/footer.jsp" %>