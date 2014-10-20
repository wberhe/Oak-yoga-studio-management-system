<%-- 
    Document   : sectionDetail
    Created on : Sep 20, 2014, 4:13:03 PM
    Author     : Senai
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Faculty - Section</title>
        <link href="resources/style.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <h3>Section Information :</h3>
        <table>
            <tr>
                <td>course</td>
                <td>${sectionDetail.course.courseName}</td>
                <td> </td>
                <td>section Name</td>
                <td>${sectionDetail.sectionName}</td>
                <td> </td>
                <td>Capacity </td>
                <td>${sectionDetail.capacity}</td>
            </tr>
            <tr>                
                <td>Room Number</td>
                <td>${sectionDetail.roomNumber}</td>
             
              
                <td> </td>
                <td>status</td>  
                <td>${sectionDetail.status}</td>
            </tr>
        </table>
        <h3>List of all Students :</h3>
        <table>
            <tr>
                <td></td> 
                <td>First Name</td> 
                <td>Last Name</td>
                <td>Email</td>                    
            </tr>
           <c:forEach var="s" items="${sectionDetail.enrollements}">
                <tr>
                    <td><img src="../image/${s.customer.id}" width="80" height="80"/></td> 
                    <td>${s.customer.firstName}</td> 
                    <td>${s.customer.lastName}</td>
                    <td><a href="mailto:${s.customer.email}">${s.customer.email}</a></td>    
                    <%--<td>${s.status}</td>--%>
                </tr>
            </c:forEach>
        </table>
        <a href="../viewAdvisees"> Back </a>
    </body>
</html>
