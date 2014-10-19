<%-- 
    Document   : FacultyWaiverRequest
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
        <h3>List of pending Waiver Requests :</h3>
        <table >
            <tr style="">
                <td></td>
                <td>First Name</td>
                <td>Last Name </td>                
                <td>Email</td>
                <td>Course </td>
                <td>Reason</td>
                <td>Accept/Reject Waiver</td>
            </tr>
            <c:forEach var="w" items="${waiverRequests}">
                <tr>
                    <td></td> 
                    <td>${w.customer.firstName}</td> 
                    <td>${w.customer.lastName}</td>
                    <td>(Email: ${w.customer.email})</td>
                    <td>${w.waiverCourse.courseName}</td>
                    <td>${w.requestReason}</td>
                    <td><form action="decideOnWaiver/${w.id}" method="post"> <hidden name="" value="${w.id}"/> <input type="radio" name="waiverDecision" value="accepted" /> ACCEPT / <input type="radio" name="waiverDecision" value="rejected"/>REJECT <input type="Submit" /></form></td>
                </tr>
            </c:forEach>
        </table>
        
        <h3>List of Waivers :</h3>
        <table >
            <tr style="">
                <td></td>
                <td>First Name</td>
                <td>Last Name </td>                
                <td>Email</td>
                <td>Course </td>
                <td>Reason</td>
                <td>Accept/Reject Waiver</td>
            </tr>
            <c:forEach var="w" items="${waivers}">
                <tr>
                    <td></td> 
                    <td>${w.customer.firstName}</td> 
                    <td>${w.customer.lastName}</td>
                    <td>(Email: ${w.customer.email})</td>
                    <td>${w.waiverCourse.courseName}</td>
                    <td>${w.requestReason}</td>
                    :if
                    <td><form action="decideOnWaiver/${w.id}" method="post"> <hidden name="" value="${w.id}"/> <input type="radio" name="waiverDecision" value="accepted" /> ACCEPT / <input type="radio" name="waiverDecision" value="rejected"/>REJECT <input type="Submit" /></form></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
