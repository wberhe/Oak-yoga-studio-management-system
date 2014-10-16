<%-- 
    Document   : CourseEnroll
    Created on : Oct 15, 2014, 6:47:43 PM
    Author     : Fetiya
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link href="resources/css/style.css" rel="stylesheet" type="text/css" />
        <title>Courses List</title>
    </head>
    <body>
        <h2> List of Courses </h2>
        <table>
            
            <tr>
                <td>Course Number</td>
                <td>Course Name </td>
                <td>Description</td>
            </tr>
                 
            <c:forEach var="c" items="${courses}">
                 <tr>
                     <td><a href="viewSections/${c.id}">${c.courseNumber}</a></td>
                    <td>${c.courseName }</td> 
                    <td>${c.description}</td>
                  
                </tr
            </li>
            </c:forEach>
        </table>
    </body>

</html>
