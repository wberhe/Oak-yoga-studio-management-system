<%-- 
    Document   : withdrawRequest
    Created on : Oct 21, 2014, 5:37:53 AM
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
        <table border="1" cellpadding="5" width="100%">  <tr>
                <th>Enrollment Status</th>
                <th>Enrollment Date</th>
                <th>Section</th>
                <th>Course Name</th>
                <th>Course Number  </th>
                <th>Professor</td>
                <th>Section Status</th>
                <th></th>

            </tr>
            <c:forEach var="enrollment" items="${sectionsTaken}">


                <tr>


                    <td>${enrollment.status}</td> 
                    <td>${enrollment.enrollmentDate}</td> 
                    <td>${enrollment.section.sectionName}</td>
                    <td>${enrollment.section.course.courseName}</td>
                    <td>${enrollment.section.course.courseNumber}</td>
                    <td>${enrollment.section.professor.getFirstName()} ${enrollment.section.professor.getLastName()}</td>
                    <td>${enrollment.section.status} </td>

                    <td>
                        <form commandName="enrollment"  action="${pageContext.request.contextPath}/withdraw/${enrollment.id}" method="POST"> 

                            <c:choose>
                                <c:when test="${enrollment.status=='COMPLETED' || enrollment.status=='WITHDRAWAL'
                                        ||enrollment.section.status=='COMPLETED'|| enrollment.section.status=='CANCELED'}">
                                    <input type="submit" value="Withdraw" disabled="disabled"/>
                                </c:when>
                                <c:otherwise> 
                                    <input type="submit" value="Withdraw" width="100%"/>
                                </c:otherwise>
                            </c:choose>
                        </form>

                    </td>    


                </tr>
            </c:forEach>
        </table>
        </div>
        
        <div align="center">
        <a href="${pageContext.request.contextPath}/index">Back</a></br>
        </div>
        
        

    </body>
