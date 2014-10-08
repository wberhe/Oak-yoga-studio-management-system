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
        <h3>Here are the current users :</h3>
        <table>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td>${user.firstname}</td>
                    <td>${user.lastname}</td>
                    <td>${user.email}</td>
                    <td><a href="users/${user.id}">Update </a></td>
                    
                    <c:choose>
                        <c:when test="${user.userCredential.blocked==false}">
                            <td><a href="users/${user.id}/disable">Disable </a></td>
                        </c:when>
                        <c:otherwise>
                           <td><a href="users/${user.id}/enable">Enable </a></td>
                        </c:otherwise>
                    </c:choose>
                </tr>
            </c:forEach>
        </table>
        <a href="addCredential"> Add User</a>

    </body>
</html>
