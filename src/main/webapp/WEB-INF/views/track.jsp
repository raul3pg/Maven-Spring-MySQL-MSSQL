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

            function returnToTracks(){
                window.location = "/tracks";
            }

        </script>
    </head>

    <body>
        <form:form action="/index/addTrack" modelAttribute="bindableTrack">

            <h3><spring:message code="heading.title"/></h3>
            <form:hidden path="id"/>
            <form:input id="title" path="title"/>
            <form:errors path="title"/>

            <input id="submit" type="submit" value="<spring:message code="button.save"/>"/>
        </form:form>

        <c:if test="${not empty bindableTrack.id}">
            <button onclick="deleteTrack(${bindableTrack.id})">
                <spring:message code="button.delete"/>
            </button>

            <br />

            <button onclick="returnToTracks()">
                <spring:message code="button.return-tracks"/>
            </button>
        </c:if>

        <button onclick="returnToIndex()">
            <spring:message code="button.return-index"/>
        </button>
    </body>
</html>
