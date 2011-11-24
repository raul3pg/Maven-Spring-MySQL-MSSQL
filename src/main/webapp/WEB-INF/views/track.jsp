<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<script type="text/javascript" src="../../js/js.js"></script>


<c:url var="formUrl" value="/track/"/>

<c:if test="${not empty bindableTrack.id}">
    <button onclick="deleteTrack(${bindableTrack.id})">
        <spring:message code="button.delete"/>
    </button>
</c:if>

<form:form action="${formUrl}" modelAttribute="bindableTrack">
    <input id="submit" type="submit" value="<spring:message code="button.save"/>"/>
    <form:hidden path="id"/>
    <ul>
        <li>
            <label for="title"><spring:message code="heading.title"/></label>
            <form:input id="title" path="title"/>
            <form:errors path="title"/>
        </li>
    </ul>
</form:form>