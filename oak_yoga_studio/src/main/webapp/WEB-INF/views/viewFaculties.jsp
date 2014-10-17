<%-- 
    Document   : viewFaculties
    Created on : Oct 17, 2014, 11:26:52 AM
    Author     : Weldino
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>oak</title>
        <link href="resources/style.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <h3>Here are the list of all faculties :</h3>
        <table>
            <c:forEach var="faculty" items="${faculties}">
                <tr>
                    <td>${faculty.firstName}</td>
                    <td>${faculty.lastName}</td>
                    <td>${faculty.email}</td>
                    
                    <c:choose>
                        <c:when test="${faculty.credential.active==true}">
                            <td><a href="faculties/${faculty.id}/disable"> Deactivate </a></td>
                        </c:when>
                        <c:otherwise>
                           <td><a href="faculties/${faculty.id}/enable"> Activate </a></td>
                        </c:otherwise>
                    </c:choose>
                </tr>
            </c:forEach>
        </table>
        <a href="addFacultyCredential"> Add Faculty</a>

    </body>
</html>
