<%-- 
    Document   : AddFaculty
    Created on : Sep 21, 2014, 11:00:52 AM
    Author     : weldu
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Faculty</title>
        <link href="resources/style.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <h1>Register Faculty:</h1>
        <form:form modelAttribute="credential" action="addFacultyCredential" method="post" >
            <table>
                <tr>
                    <td>Username :</td>
                    <td><form:input type="text" path="userName" /> </td>
                    <td><form:errors path="userName" cssClass="error"/> </td>
                </tr>
                <tr>
                    <td>Password :</td>
                    <td><form:input type="password" path="password" /> </td>
                    <td><form:errors path="password" cssClass="error"/> </td>
                </tr>
                <tr>
                    <td>Confirm password :</td>
                    <td><form:input type="password" path="confirmPassword" /> </td>
                    <td><form:errors path="confirmPassword" cssClass="error"/> </td>
                </tr>
            </table>
                    <form:hidden path="role"  /> 
            <form:button>Register</form:button>

        </form:form>
    </body>
</html>
