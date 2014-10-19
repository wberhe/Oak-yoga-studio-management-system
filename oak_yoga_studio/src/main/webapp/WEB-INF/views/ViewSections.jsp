<%-- 
    Document   : ViewSections
    Created on : Oct 15, 2014, 9:36:42 PM
    Author     : Fetiya
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Available Sections for .. course Name </title>
    </head>
    <body>
        <h2> List of Sections </h2>
        <table>
                 
            <c:forEach var="s" items="${sections}">
                 <tr>
                    
                    <td>${s.sectionName }</td> 
                    <td>${s.roomNumber}</td>
                   <td><a href="../enrollInCourse/${s.id}">Enroll For this section <a></td>
                               <h2> ${message}</h2>           
                 </tr>
            
            </c:forEach>
        </table>
    </body>
</html>
