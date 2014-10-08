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
        <title>All Categories</title>
    </head>
    <body>
        <h1>All Categories</h1>
        <table>
            <c:forEach items="${categories}" var="category">
                <tr>
                    <td>${category.name}</td><td> <a href="deleteCategory/${category.id}">delete</a></td>
                </tr>
            </c:forEach>
        </table>
        <ul
            <li><a href="addBlogCategory">Add Blog Category </a></li>
        </ul>

    </body>
</html>
