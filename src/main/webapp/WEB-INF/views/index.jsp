<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
    <head>
        <script type="text/javascript" src="../../js/js.js"></script>
        <link rel="stylesheet" type="text/css" href="<c:url value="../../css/style.css"/>" />
    </head>

    <body>
        <button onclick="redirectToAddArtist()">
            <spring:message code="button.add-artist"/>
        </button>

        <button onclick="redirectToAddArtist()">
            <spring:message code="button.add-track"/>
        </button>

        <table id="tableArtists">
            <thead>
                <tr>
                    <th><spring:message code="heading.firstName"/></th>
                    <th><spring:message code="heading.lastName"/></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${bindableArtistList}" var="artist">
                    <tr>
                        <td>
                            <a href="<c:url value="/index/artist/${artist.id}" />">
                                <c:out value="${artist.firstName}"/>
                            </a>
                        </td>
                        <td>
                            <c:out value="${artist.lastName}"/>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <table id="tableTracks">
            <thead>
                <tr>
                    <th><spring:message code="heading.title"/></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${bindableTrackList}" var="track">
                    <tr>
                        <td>
                            <a href="<c:url value="/index/track/${track.id}" />">
                                <c:out value="${track.title}"/>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </body>
</html>

