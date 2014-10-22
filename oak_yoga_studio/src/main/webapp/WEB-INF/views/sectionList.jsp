<%-- 
    Document   : sectionList
    Created on : Oct 18, 2014, 4:31:59 PM
    Author     : Weldino
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib  prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>All sections</title>
        <script>
        function confirmComplete() {
alert("confirmComplete");
var answer=confirm("Admin are you sure you want to delete the selected section?");
if (answer==true)
  {
    return true;
  }
else
  {
    return false;
  }
}    
        </script>
    </head>
    <body>
        <h2> List of Sections </h2>
        <form:form comandName="section" action="deleteSection" method="post">
        <table><tr>
                <td></td>
                <td><strong>Name: </strong></td>
                <td><strong>Room number: </strong></td>
                <td><strong>Professor: </strong></td>
                <td><strong>Course: </strong></td>
                <td><strong>Status: </strong></td>
            </tr>
            <c:forEach var="s" items="${sections}">
                 <tr>
                    <td><input type="checkbox" name="ids" value="${s.id}"/></td> 
                    <td>${s.sectionName}</td> 
                    <td>${s.roomNumber}</td>
                    <td>${s.professor.firstName}</td>
                    <td>${s.course.courseName}</td>
                    <td>${s.status}</td>
                 </tr>
            </c:forEach>
        </table>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <a href="admin"> Back to home</a>
                <p> <a href="newSection">add a new section</a></p>
                <input type="submit" value="Delete" onclick="return confirmComplete();"/>
            </sec:authorize>
        </form:form>
            
    </body>
</html>

