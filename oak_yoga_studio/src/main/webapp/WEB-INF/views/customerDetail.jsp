<%-- 
    Document   : customerDetail
    Created on : Sep 20, 2014, 4:13:03 PM
    Author     : weldu
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>admin</title>
        <link href="resources/style.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <h3>User Information :</h3>
        <table>
                <tr>
                    <td>Picture:</td>
                    <td><img src="../image/${customerDetail.id}" width="100" height="100"/></td>
                </tr>
                <tr>
                    <td>First name:</td>
                    <td>${customerDetail.firstName}</td>
                </tr>
                <tr>
                    <td>Last name :</td>
                    <td>${customerDetail.lastName}</td>
                </tr>
                <tr>
                    <td> Email :</td>
                    <td>${customerDetail.email}</td>
                </tr>
                <tr>
                    <td> Date of birth :</td>
                    <td>${customerDetail.dateOfBirth}</td>
                </tr>
           
        </table>
        <a href="../admin"> Back </a>

    </body>
</html>
