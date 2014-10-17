<%-- 
    Document   : AdviseesOFAfaculty
    Created on : Sep 20, 2014, 8:02:03 PM
    Author     : Senai
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Faculty</title>
    </head>
    <body>
        <h3>List of all Advisees :</h3>
            <table>
                <c:forEach var="a" items="${advisees}">
                    <tr>
                        <td><input type="checkbox" name="ids" value="${a.id}"/></td> 
                        <td>${a.firstName}</td> 
                        <td>${a.lastName}</td>
                        <td>(Email: ${a.email})</td>
                        <td><a href="customerDetail/${a.id}">customer details</a></td>
                    </tr>
                </c:forEach>
            </table>
    </body>
</html>
