<%-- 
    Document   : adviseeDetail
    Created on : Sep 20, 2014, 4:13:03 PM
    Author     : Senai
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Faculty - Advisee</title>
        <link href="resources/style.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <h3>Advisee Information :</h3>
        <table>
                <tr>
                    <td>Picture:</td>
                    <td><img src="../image/${adviseeDetail.id}" width="80" height="80"/></td>
                </tr>
                <tr>
                    <td>First name:</td>
                    <td>${adviseeDetail.firstName}</td>
                </tr>
                <tr>
                    <td>Last name :</td>
                    <td>${adviseeDetail.lastName}</td>
                </tr>
                <tr>
                    <td> Email :</td>
                    <td>${adviseeDetail.email}</td>
                </tr>
                <tr>
                    <td> Date of birth :</td>
                    <td>${adviseeDetail.dateOfBirth}</td>
                </tr>
           
        </table>
        <a href="../viewAdvisees"> Back </a>

    </body>
</html>
