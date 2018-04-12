<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Client"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Clients</title>
    </head>
    <body>
        <center>
            <h1>List of Clients</h1>
            <table cellpadding="5" border="1">
                <thead>
                    <th>id</th>
                    <th>userId</th>
                    <th>firstName</th>
                    <th>lastName</th>
                    <th>streetNumber</th>
                    <th>streetName</th>
                    <th>city</th>
                    <th>province</th>
                    <th>postalCode</th>
                    <th>telOffice</th>
                    <th>telCell</th>
                    <th>email</th>
                    <th>company</th>
                    <th>companyType</th>
                </thead>
                <tbody>
                <c:forEach var="client" items="${clientList}">
                    <tr>
                        <td><c:out value="${client.id}"/></td>
                        <td><c:out value="${client.userId}"/></td>
                        <td><c:out value="${client.firstName}"/></td>
                        <td><c:out value="${client.lastName}"/></td>
                        <td><c:out value="${client.streetName}"/></td>
                        <td><c:out value="${client.streetNumber}"/></td>
                        <td><c:out value="${client.city}"/></td>
                        <td><c:out value="${client.province}"/></td>
                        <td><c:out value="${client.postalCode}"/></td>
                        <td><c:out value="${client.telOffice}"/></td>
                        <td><c:out value="${client.telCell}"/></td>
                        <td><c:out value="${client.email}"/></td>
                        <td><c:out value="${client.company}"/></td>
                        <td><c:out value="${client.companyType}"/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </center>
    </body>
</html>
