<%-- 
    Document   : CourseEnroll
    Created on : Oct 15, 2014, 6:47:43 PM
    Author     : Fetiya
--%>
<%@taglib  prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
        <h4> Courses</h4>
        <div class="datagrid">
            <table >  <thead>

                    <tr>
                        <th>Course Number</th>
                        <th>Course Name </th>
                        <th>Description</th>
                        <th>Prerequisites</th>
                        <td></td>
                       <td></td>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="c" items="${courses}" varStatus="loopStatus" >



                        <tr class="${loopStatus.index % 2 == 0 ? 'alt2' :'alt'}">

                            <td>${c.courseNumber}</a></td>
                            <td>${c.courseName }</td> 
                            <td width="20%">${c.description}</td>

                            <td>
                                <c:forEach var="pre" items="${c.prerequisites}">
                        <li> ${pre.courseName}</li>
                        </c:forEach>
                    </td>
                    <td><a href="viewSections/${c.id}"> view sections </a></td>
                    <td>
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <a href="newSection">add a new section</a>
                        </sec:authorize>
                    </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </body>

</html>
