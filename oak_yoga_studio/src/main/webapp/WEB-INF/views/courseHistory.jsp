<%-- 
    Document   : courseHistory
    Created on : Oct 19, 2014, 4:13:10 AM
    Author     : Fetiya
--%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Enrollment History</title>
        
        

    </head>
    <body>
        <div align="center">
            <h3>${msg}</h3>
            <div class="datagrid">
            <table >  <thead><tr>
                    <th>Enrollment Status</th>
                    <th>Enrollment Date</th>
                    <th>Section</th>
                    <th>Course Name</th>
                    <th>Course Number  </th>
                    <th>Professor</th>
                    <th>Section Status</th>
                   

                </tr>
                </thead>
                <tbody>
                <c:forEach var="enrollment" items="${sectionsTaken}" varStatus="loopStatus">

 
 
                    <tr class="${loopStatus.index % 2 == 0 ? 'alt2' :'alt'}">


                        <td>${enrollment.status}</td> 
                        <td>${enrollment.enrollmentDate}</td> 
                        <td>${enrollment.section.sectionName}</td>
                        <td>${enrollment.section.course.courseName}</td>
                        <td>${enrollment.section.course.courseNumber}</td>
                        <td>${enrollment.section.professor.getFirstName()} ${enrollment.section.professor.getLastName()}</td>
                        <td>${enrollment.section.status} </td>

                       


                    </tr>
                </c:forEach>
                    </tbody>
                    
            </table>
        </div>

        <div align="center">
            <a href="${pageContext.request.contextPath}/index">Back</a></br>
        </div>

    </body>