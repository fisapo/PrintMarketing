<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Location"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Locations</title>
    </head>
    <body>
        <br/>
    <center>
        <h1>List of Locations</h1>
        <table cellpadding="5" border="1">
            <thead>
            <th>ID</th>
            <th>Location Name</th>
            <th>Distribution Capacity</th>
            <th>action</th>
            </thead>
            <tbody>

                <c:forEach var="location" items="${locationList}">
                    <tr>
                        <td><c:out value="${location.locationId}"/></td>
                        <td><c:out value="${location.locationName}"/></td>
                        <td><c:out value="${location.distributionCapacity}"/></td>
                        <td><a href="edit?id=<c:out value='${location.locationId}'/>">
                                Edit
                            </a>
                            &nbsp;&nbsp;&nbsp;
                            <a href="delete?id=<c:out value='${location.locationId}'/>">
                                Delete
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </center>
    <center>
        <a href="newLoc">Add New Location</a>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <a href="listLoc">View Locations</a>
        <br/>
        <a href = "AgentFrom">Go to Agent(Rename)</a>;
    </center>
</body>
</html>
