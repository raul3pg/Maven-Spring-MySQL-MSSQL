<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
    <head>
        <script type="text/javascript">

            function redirectToAddArtist() {
                window.location = "/index/artist/new";
            }

            function redirectToAddTrack() {
                window.location = "/index/track/new";
            }

            function redirectToEditTracks(){
                window.location = "/tracks";
            }

            function redirectToMap(){
                window.location = "/map";
            }

        </script>
        <link rel="stylesheet" type="text/css" href="<c:url value="../../css/style.css"/>"/>
    </head>



    <body>
        <button onclick="redirectToAddArtist()">
            <spring:message code="button.add-artist"/>
        </button>

        <button onclick="redirectToAddTrack()">
            <spring:message code="button.add-track"/>
        </button>

        <button onclick="redirectToEditTracks()">
            <spring:message code="button.edit-tracks"/>
        </button>

        <button onclick="redirectToMap()">
            <spring:message code="button.map"/>
        </button>

        <table>
            <thead>
            <tr>
                <th><spring:message code="heading.fullName"/></th>
                <th><spring:message code="heading.allTracks"/></th>
            </tr>
            </thead>
            <tbody>
                <c:forEach var="tracksToArtist" items="${bindableTrackToArtistList}">

                    <tr>
                        <td>
                            <a href="<c:url value="/index/artist/${tracksToArtist.artist.id}" />">
                                <c:out value="${tracksToArtist.artist.firstName}"/>
                                <c:out value="${tracksToArtist.artist.lastName}"/>
                            </a>
                        </td>
                        <td>
                            <select name="choice" style="width: 150px">
                                <c:forEach var="track" items="${tracksToArtist.tracks}">
                                    <option value="${track.id}">
                                        ${track.title}
                                    </option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>

