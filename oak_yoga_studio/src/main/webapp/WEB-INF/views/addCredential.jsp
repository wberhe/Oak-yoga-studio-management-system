<%-- 
    Document   : AddCredential
    Created on : Sep 21, 2014, 11:00:52 AM
    Author     : Weldino
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Credentials</title>
        <link href="resources/style.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <h1>Add Credentails :</h1>
        <form:form commandName="credential" action="addCredential" method="post">
            <table>

                <tr>
                    <td>User Name :</td>
                    <td><form:input type="text" path="username" /> </td>
                    <td><form:errors path="username" cssClass="error"/> </td>
                </tr>
                <tr>
                    <td>Password :</td>
                    <td><form:input type="password" path="password" /> </td>
                    <td><form:errors path="password" cssClass="error"/> </td>
                </tr>
                <tr>
                    <td>Confirm Password :</td>
                    <td><form:input type="password" path="confirmpassword" /> </td>
                    <td><form:errors path="confirmpassword" cssClass="error"/> </td>
                </tr>
            </table>
                    <form:hidden path="previledge"  /> 
            <form:button  >Submit</form:button>

        </form:form>
    </body>
</html>
