<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Location Page</title>
    </head>
    <body>
    <center>
        <h2>Edit location</h2>
        <form action="updateLocation" method="post" name="editForm">
            <input type="hidden" name="id" id="locationId" value="${location.locationId}"/>
            <table cellpadding="5" border="1">
                <tr>
                    <th>Location Name</th>
                    <td>
                        <input type="text" name="locationName" id="locationName" value="${location.locationName}">
                    </td>
                </tr>
                <tr>
                    <th>Distribution Capacity</th>
                    <td><input type="text" name="distributionCapacity" id="email" value="${location.distributionCapacity}"></td>
                </tr>
                <tr>
                    <td colspan="2"><input type="submit" name="submit" value="Save"></td>
                <c:if test="${not empty error}">
                    <p><span style="color:red;">${error}</span></p><!--Shows up when there was a NumberException in the servlet
                    in simplicity, when user input is not a number.-->
                </c:if>
                </tr>
            </table>
            <!-- <input type="submit" name="submit" value="view">-->
        </form>
    </center>
</body>
</html>
