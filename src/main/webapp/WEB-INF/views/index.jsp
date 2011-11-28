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

            function editSelectedTrack(){
                element = document.getElementById("trackSelect");
                window.location = "" + element.options[element.selectedIndex].value;
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
                                    <option>
                                        <c:out value="${track.title}" />
                                    </option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <button><spring:message code="button.edit-track"/></button>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>

