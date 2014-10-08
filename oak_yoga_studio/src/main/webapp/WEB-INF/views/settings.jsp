<%-- 
    Document   : settings
    Created on : Sep 20, 2014, 8:02:03 PM
    Author     : weldino
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin profile</title>
    </head>
    <body>
        <h3>Admin Setting</h3>
        <ul>
            <li><a href="allCategories">All Categories</a></li>
            <li><a href="allTags">All Tags</a></li>
            <li><a href="addCredential"> Add User</a></li>
        </ul>

        <h3>Notify users :</h3>
       
        <form  action="sendEmail" method="post">
             <table>
                 
            <c:forEach var="user" items="${allusers}">
                <tr>
                    <td><input type="checkbox" name="ids" value="${user.id}"/></td> 
                    <td>${user.firstname}</td> 
                    <td>${user.lastname}</td>
                    <td>(email: ${user.email})</td>
                    <td><a href="userDetail/${user.id}">user details</a></td>
                </tr>
            </c:forEach>
        </table>
            Subject: <input type="text" /><br/>
            Email :<br/><textarea name="text" rows="5" cols="30"></textarea><br/>
            <input type="submit" value="Send Email" />
        </form>

    </body>
</html>
