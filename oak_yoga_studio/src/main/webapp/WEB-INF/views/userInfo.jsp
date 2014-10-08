<%-- 
    Document   : userList
    Created on : Sep 20, 2014, 4:13:03 PM
    Author     : Weldino
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Blog engine</title>
        <link href="resources/style.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <h3>User Information :</h3>
        <table>
                <tr>
                    <td>Picture:</td>
                    <td><img src="../image/${userdetail.id}" width="80" height="80"/></td>
<!--                String imageId = request.getParameter("imageId");
                    byte[] imageData = getImageFromDatabase(imageId);
                    response.setContentType("image/jpeg");
                    response.getOutputStream().write(imageData);-->
                </tr>
                <tr>
                    <td>First name:</td>
                    <td>${userdetail.firstname}</td>
                </tr>
                <tr>
                    <td>Last name :</td>
                    <td>${userdetail.lastname}</td>
                </tr>
                <tr>
                    <td> Email :</td>
                    <td>${userdetail.email}</td>
                </tr>
                <tr>
                    <td> Date of birth :</td>
                    <td>${userdetail.dob}</td>
                </tr>
           
        </table>
        <a href="../settings"> Back </a>

    </body>
</html>
