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
        <div class="datagrid">
            <table>
                <thead>

                    <tr>
                        <th>course</th>
                        <th>section Name</th>
                        <th>Capacity </th>
                        <th>Room Number</th>
                        <th>status</th>                
                    </tr>
                </thead>
                <c:forEach var="s" items="${facultySections}"  varStatus="loopStatus">
                        <tr class="${loopStatus.index % 2 == 0 ? 'alt2' :'alt'}">
                        <td>${s.course.courseName}</td> 
                        <td>${s.sectionName}</td> 
                        <td>${s.capacity}</td>
                        <td>${s.roomNumber}</td>
                        <td>${s.status}</td>
                        <td><a href="sectionDetail/${s.id}">Section details</a></td>
                    </tr>
                </c:forEach>

            </table>
        </div>
    </body>
</html>
