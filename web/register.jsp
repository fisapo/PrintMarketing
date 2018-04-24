<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Client"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <!-- Head -->
    <%@include file="./shared/head.jsp" %>  
    <body>
    <!-- Navigation bar -->
    <%@include file="./shared/navbar.jsp" %>
        <center>
            <h2>Register new Client</h2>
            <form action="insert" method="post" name="registerForm">
                <table cellpadding="5" border="1">
                    <tr>
                        <th>User Id</th>
                        <td><input type="number" name="userId" id="userId" required></td>
<!--                        <td>
                            <select>-->
                                <%--<c:forEach var="client" items="${clientList}">--%>
                                    <!--<option value='<c:out value="${client.userId}"/>'>-->
                                        <%--<c:out value="${client.userId}"/>--%>
                                    <!--</option>-->
                                <%--</c:forEach>--%>
<!--                            </select>
                        </td>-->
                    </tr>
                    <tr>
                        <th>First Name</th>
                        <td><input type="text" name="firstName" id="firstName" required></td>
                    </tr>
                    <tr>
                        <th>Last Name</th>
                        <td><input type="text" name="lastName" id="lastName" required></td>
                    </tr>
                    <tr>
                        <th>Street Number</th>
                        <td><input type="number" name="streetNumber" id="streetNumber" required></td>
                    </tr>
                    <tr>
                        <th>Street Name</th>
                        <td><input type="text" name="streetName" id="streetName" required></td>
                    </tr>
                    <tr>
                        <th>City</th>
                        <td><input type="text" name="city" id="city" required></td>
                    </tr>
                    <tr>
                        <th>Province</th>
                        <td><input type="text" name="province" id="province" required></td>
                    </tr>
                    <tr>
                        <th>Postal Code</th>
                        <td><input type="text" name="postalCode" id="postalCode" required></td>
                    </tr>
                    <tr>
                        <th>Tel Office</th>
                        <td><input type="number" name="telOffice" id="telOffice" required></td>
                    </tr>
                    <tr>
                        <th>Tel Cell</th>
                        <td><input type="number" name="telCell" id="telCell" required></td>
                    </tr>
                    <tr>
                        <th>Email</th>
                        <td><input type="email" name="email" id="email" required></td>
                    </tr>
                    <tr>
                        <th>Company</th>
                        <td><input type="text" name="company" id="company" required></td>
                    </tr>
                    <tr>
                        <th>Company Type</th>
                        <td><input type="text" name="companyType" id="companyType" required></td>
                    </tr>
                    <tr>
                        <td colspan="2"><input type="submit" name="submit" value="Save"></td>
                    </tr>
                </table>
            </form>
        </center>
    <!-- Footer -->
    <%@include file="./shared/footer.jsp" %>
    </body>
</html>
