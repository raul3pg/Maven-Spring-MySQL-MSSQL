<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>



<html>
    <head>
        <script type="text/javascript">
            function deleteTrack(id){
                window.location = "/index/track/" + id + "/delete";
            }
            function returnToIndex(){
                window.location = "/index";
            }
        </script>
    </head>

    <body>
        <form:form action="/index/addTrack" modelAttribute="bindableTrack">
            <form:hidden path="id"/>
            <ul>
                <li>
                    <label for="title"><spring:message code="heading.title"/></label>
                    <form:input id="title" path="title"/>
                    <form:errors path="title"/>
                </li>
            </ul>
        <input id="submit" type="submit" value="<spring:message code="button.save"/>"/>
        </form:form>

        <c:if test="${not empty bindableTrack.id}">
            <button onclick="deleteTrack(${bindableTrack.id})">
                <spring:message code="button.delete"/>
            </button>
        </c:if>

        <button onclick="returnToIndex()">
            <spring:message code="button.return"/>
        </button>
    </body>
</html>
