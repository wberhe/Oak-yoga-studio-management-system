<%-- 
    Document   : ViewSections
    Created on : Oct 15, 2014, 9:36:42 PM
    Author     : weldino
--%>
<%@taglib  prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Available Sections for .. course Name </title>
    </head>
    <body>
        <h2>Sections for ${course.courseName} ${course.courseNumber}  </h2>
        <div class="datagrid">
            <table >  <thead>
                    <tr><th>Section Name</th>
                        <th>Room Number</th>
                        <th>Instructor</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="s" items="${sections}" varStatus="loopStatus">
                        <tr class="${loopStatus.index % 2 == 0 ? 'alt2' :'alt'}">

                            <td>${s.sectionName }</td> 
                            <td>${s.roomNumber}</td>
                            <td>${s.professor.firstName}  ${s.professor.lastName}</td>
                            <sec:authorize access="hasRole('ROLE_CUSTOMER')">               

                                <td><a href="../enrollInCourse/${s.id}">Enroll For this section </a></td>
                            </sec:authorize>
                        </tr>

                    </c:forEach>
                </tbody>

            </table>
        </div>
    </body>
</html>
