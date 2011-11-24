<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<script type="text/javascript" src="../../js/js.js"></script>


<c:url var="formUrl" value="/artist/"/>

<c:if test="${not empty bindableArtist.id}">
    <button onclick="deleteArtist(${bindableArtist.id})">
        <spring:message code="button.delete"/>
    </button>
</c:if>

<form:form action="${formUrl}" modelAttribute="bindableArtist">
    <input id="submit" type="submit" value="<spring:message code="button.save"/>"/>
    <form:hidden path="id"/>
    <ul>
        <li>
            <label for="firstName"><spring:message code="heading.firstName"/></label>
            <form:input id="firstName" path="firstName"/>
            <form:errors path="firstName"/>
        </li>
        <li>
            <label for="lastName"><spring:message code="heading.lastName"/></label>
            <form:input id="lastName" path="lastName"/>
            <form:errors path="lastName"/>
        </li>
    </ul>
</form:form>