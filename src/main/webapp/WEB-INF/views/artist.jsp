<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
    <head>
        <script type="text/javascript" >
            function deleteArtist(id){
                window.location = "/index/artist/" + id + "/delete";
            }
            function returnToIndex(){
                window.location = "/index";
            }
        </script>
    </head>

    <body>
        <form:form action="/index/addArtist" modelAttribute="bindableArtist" >
            <form:hidden path="id"/>

                <h3><spring:message code="heading.firstName"/></h3>
                <form:input id="firstName" path="firstName"/>
                <form:errors path="firstName"/>

                <h3><spring:message code="heading.lastName"/></h3>
                <form:input id="lastName" path="lastName"/>
                <form:errors path="lastName"/>

            <input id="submit" type="submit" value="<spring:message code="button.save"/>"/>
        </form:form>

        <c:if test="${not empty bindableArtist.id}">
            <button onclick="deleteArtist(${bindableArtist.id})">
                <spring:message code="button.delete"/>
            </button>
        </c:if>

        <button onclick="returnToIndex()">
            <spring:message code="button.return-index"/>
        </button>
    </body>
</html>


