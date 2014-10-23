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
        <div class="datagrid">
            <table>
                <thead>
                    <tr >

                        <th>First Name</th>
                        <th>Last Name </th>                
                        <th>Email</t>
                        <th>Course </th>
                        <th>Reason</th>
                        <th>Accept/Reject Waiver</th>
                    </tr>
                </thead>
                <c:forEach var="w" items="${waiverRequests}" varStatus="loopStatus">

                       <tr class="${loopStatus.index % 2 == 0 ? 'alt2' :'alt'}">
                     
                        <td>${w.customer.firstName}</td> 
                        <td>${w.customer.lastName}</td>
                        <td>${w.customer.email}</td>
                        <td>${w.waiverCourse.courseName}</td>
                        <td>${w.requestReason}</td>
                        <td><form action="decideOnWaiver/${w.id}" method="post"> <hidden name="" value="${w.id}"/> <input type="radio" name="waiverDecision" value="accepted" /> ACCEPT / <input type="radio" name="waiverDecision" value="rejected"/>REJECT <input type="Submit" /></form></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <h3>List of Waivers :</h3>
        <div class="datagrid">
            <table>
                <thead>
                    <tr >
                        <th>First Name</th>
                        <th>Last Name </th>                
                        <th>Email</th>
                        <th>Course </th>
                        <th>Reason</th>
                        <th>Accept/Reject Waiver</th>
                    </tr>
                </thead>
                <c:forEach var="w" items="${waivers}" varStatus="loopStatus">

                       <tr class="${loopStatus.index % 2 == 0 ? 'alt2' :'alt'}">
                        <td>${w.customer.firstName}</td> 
                        <td>${w.customer.lastName}</td>
                        <td>(Email: ${w.customer.email})</td>
                        <td>${w.waiverCourse.courseName}</td>
                        <td>${w.requestReason}</td>
                        <td>${w.status}</td>                                           
                    </tr>
                </c:forEach>
            </table>
        </div>

    </body>
</html>
