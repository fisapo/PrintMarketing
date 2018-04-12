<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Agent Page</title>
    </head>
    <body>
    <center>
        <h2>Edit agent</h2>
        <form action="updateAgent" method="post" name="editForm">
            <input type="hidden" name="id" id="agentId" value="${agent.agentId}"/>
            <table cellpadding="5" border="1">
                <tr>
                    <th>Agent First Name</th>
                    <td>
                        <input type="text" name="firstName" id="firstName" value="${agent.firstName}" required>
                    </td>
                </tr>
                <tr>
                    <th>Agent Last Name</th>
                    <td>
                        <input type="text" name="lastName" id="lastName" value="${agent.lastName}" required>
                    </td>
                </tr>
                <tr>
                    <th>Phone Number</th>
                    <td>
                        <input type="text" name="phoneNo" id="phoneNo" value="${agent.phoneNo}" required>
                    </td>
                </tr>
                <tr>
                    <th>Email</th>
                    <td>
                        <input type="Email" name="Email" id="Email" value="${agent.email}" required>
                    </td>
                </tr>
                <tr>
                    <th>Username</th>
                    <td>
                        <input type="text" name="userName" id="userName" value="${agent.userName}" required>
                    </td>
                </tr>
                <tr>
                    <th>Password</th>
                    <td>
                        <input type="text" name="password" id="password" value="${agent.password}" required>
                    </td>
                </tr>
                <tr>
                    <td colspan="2"><input type="submit" name="submit" value="Save"></td>
                </tr>
            </table>
            <!-- <input type="submit" name="submit" value="view">-->
        </form>
    </center>
</body>
</html>
