<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
    <head>
        <script type="text/javascript">

            function editSelectedTrack() {
                var comboBox = document.getElementById("trackSelect");
                var trackId = comboBox.options[comboBox.selectedIndex].value;
                window.location = "/index/track/" + trackId;
            }

            function returnToIndex(){
                window.location = "/index";
            }
        </script>
    </head>


    <body>
        <h3><spring:message code="heading.selectTrack"/></h3>

        <select id="trackSelect" name="choice">
            <c:forEach var="track" items="${bindableTrackList}">
                <option value="${track.id}">
                    ${track.title}
                </option>
            </c:forEach>
        </select>

        <button onclick="editSelectedTrack()">
            <spring:message code="button.edit"/>
        </button>

        <br />
        <button onclick="returnToIndex()">
            <spring:message code="button.return-index"/>
        </button>
    </body>
</html>