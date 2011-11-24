<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <script type="text/javascript" src="../../js/js.js"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value="../../css/style.css"/>"/>
</head>

<body>
<button onclick="redirectToAddArtist()">
    <spring:message code="button.add-artist"/>
</button>

<button onclick="redirectToAddTrack()">
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
                <c:out value="${artist.firstName}"/>
            </td>
            <td>
                <c:out value="${artist.lastName}"/>
            </td>
            <td>
                <a href="<c:url value="/index/artist/${artist.id}" />">
                    <spring:message code="button.edit"/>
                </a>
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
                <c:out value="${track.title}"/>
            </td>
            <td>
                <a href="<c:url value="/index/track/${track.id}" />">
                    <spring:message code="button.edit"/>
                </a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>

