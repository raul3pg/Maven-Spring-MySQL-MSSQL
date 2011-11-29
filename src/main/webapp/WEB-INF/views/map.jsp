<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
    <head>
        <script type="text/javascript">

            function doMapping(){
                var comboBoxArtists = document.getElementById("artistSelect");
                var artistId = comboBoxArtists.options[comboBoxArtists.selectedIndex].value;

                var comboBoxTracks = document.getElementById("trackSelect");
                var trackId = comboBoxTracks.options[comboBoxTracks.selectedIndex].value;

                window.location = "/map/" + artistId + "/" + trackId;
            }

            function returnToIndex(){
                window.location = "/index";
            }

        </script>
    </head>

    <body>
        <select id="artistSelect">
            <c:forEach var="tracksToArtist" items="${bindableTrackToArtistList}">
                <option value="${tracksToArtist.artist.id}">
                    ${tracksToArtist.artist.firstName} ${tracksToArtist.artist.lastName}
                </option>
            </c:forEach>
        </select>

        <select id="trackSelect">
            <c:forEach var="tracksToArtist" items="${bindableTrackToArtistList}">
                <c:forEach var="track" items="${tracksToArtist.tracks}">
                    <option value="${track.id}">
                        ${track.title}
                    </option>
                </c:forEach>
            </c:forEach>
        </select>

        <button onclick="doMapping()"><spring:message code="button.doMap"/></button>

        <br />
        <button onclick="returnToIndex()"><spring:message code="button.return-index"/></button>
    </body>
</html>