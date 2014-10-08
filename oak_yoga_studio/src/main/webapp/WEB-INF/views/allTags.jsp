<%-- 
    Document   : allTag
    Created on : Sep 21, 2014, 2:11:18 PM
    Author     : aalzamer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>All Tag</title>
    </head>
    <body>
        <h1>All Tags</h1>
        <table>
            <c:forEach items="${tags}" var="tag">
                <tr>
                    <td>${tag.name}</td><td> <a href="deleteTag/${tag.id}">delete</a></td>
                </tr>
            </c:forEach>
        </table>
        <ul
            <li><a href="addTag">Add Tag</a></li>
        </ul>

    </body>
</html>
