<%-- 
    Document   : addFaculty
    Created on : Oct 16, 2014, 6:34:53 PM
    Author     : weldu
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>add Faculty</title>
        <link href="resources/style.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <h2>Add Faculty Information :</h2>
        <form:form commandName="faculty" action="addFaculty" method="post" enctype="multipart/form-data">
            <table>
                <tr>
                    <td>First Name :</td>
                    <td><form:input type="text" path="firstName" /> </td>
                    <td><form:errors path="firstName" cssClass="error" /> </td>
                </tr>
                <tr>
                    <td>Last Name :</td>
                    <td><form:input type="text" path="lastName" /> </td>
                    <td><form:errors path="lastName" cssClass="error"/> </td>
                </tr>
                <tr>
                    <td>Date of Birth :</td>
                    <td><form:input type="date" path="dateOfBirth" /> </td>
                    <td><form:errors path="dateOfBirth" cssClass="error"/> </td>
                </tr>
                <tr>
                    <td>Picture :</td> 
                    <td><input type="file" name="file"/> </td>
                </tr>
                <tr>
                    <td>Email :</td>
                    <td><form:input type="text" path="email" /> </td>
                    <td><form:errors path="email" cssClass="error"/> </td>
                </tr>
            </table>
                <form:hidden path="active" /> 
                <form:hidden path="credential.userName" /> 
                <form:hidden path="credential.password" /> 
                <form:hidden path="credential.confirmPassword" /> 
                <form:hidden path="credential.role" /> 
           <form:button >register faculty</form:button>

        </form:form>
    </body>
</html>
