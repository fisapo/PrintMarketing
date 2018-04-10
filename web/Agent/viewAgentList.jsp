<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Location"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Agents</title>
    </head>
    <body>
        <br/>
    <center>
        <h1>List of Agents</h1>
        <table cellpadding="5" border="1">
            <thead>
            <th>ID</th>
            <th>Agent Name</th>
            <th>Phone Number</th>
            <th>Email</th>
            <th>Username</th>
            <th>Password</th>
            <th>action</th>
            </thead>
            <tbody>

                <c:forEach var="agent" items="${agentList}">
                    <tr>
                        <td><c:out value="${agent.agentId}"/></td>
                        <td><c:out value="${agent.firstName}"/> <c:out value="${agent.lastName}"/></td>
                        <td><c:out value="${agent.phoneNo}"/></td>
                        <td><c:out value="${agent.email}"/></td>
                        <td><c:out value="${agent.userName}"/></td>
                        <td><c:out value="${agent.password}"/></td>
                        <td><a href="edit?id=<c:out value='${agent.agentId}'/>">
                                Edit
                            </a>
                            &nbsp;&nbsp;&nbsp;
                            <a href="delete?id=<c:out value='${agent.agentId}'/>">
                                Delete
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </center>
    <center>
        <a href="NewAge">Add New Agent</a>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <a href="listAge">View Agents</a>
        <br/>
        <a href = "AgentTo">Go to Location(Rename)</a>;
    </center>
</body>
</html>
