<%-- 
    Document   : FacultySections
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
        <h3>List of all my sections :</h3>
        <table>
            <tr>
                <td>course</td>
                <td>section Name</td>
                <td>Capacity </td>
                <td>Room Number</td>
                <td>Available Seat</td>
                <td>status</td>                
            </tr>
            <c:forEach var="s" items="${facultySections}">
                <tr>
                    <td>${s.course.courseName}</td> 
                    <td>${s.sectionName}</td> 
                    <td>${s.capacity}</td>
                    <td>${s.roomNumber}</td>
                    <td>${s.availableSeat}</td>
                    <td>${s.status}</td>
                    <td><a href="sectionDetail/${s.id}">Section details</a></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
