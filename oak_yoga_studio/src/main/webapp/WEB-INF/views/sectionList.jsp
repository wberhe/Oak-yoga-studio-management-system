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
                var answer = confirm("Admin are you sure you want to delete the selected section?");
                if (answer == true)
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
            <div class="datagrid">
            <table>
                <thead>
                <tr>
                    <td></td>
                    <th><strong>Name </strong></th>
                    <th><strong>Room number </strong></th>
                    <th><strong>Professor </strong></th>
                    <th><strong>Course </strong></th>
                    <th><strong>Status </strong></th>
                </tr>
                </thead>
                <c:forEach var="s" items="${sections}" varStatus="loopStatus">
                    <tr class="${loopStatus.index % 2 == 0 ? 'alt2' :'alt'}">
                        <td><input type="checkbox" name="ids" value="${s.id}"/></td> 
                        <td>${s.sectionName}</td> 
                        <td>${s.roomNumber}</td>
                        <td>${s.professor.firstName}</td>
                        <td>${s.course.courseName}</td>
                        <td>${s.status}</td>                        
                    </tr>
                </c:forEach>
            </table>
                <br/><br/>
            </div>
                
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <input type="submit" value="Delete Section" onclick="return confirmComplete();"/>
                 <a href="newSection"><button>Add a new section</button></a>
            </sec:authorize>
        </form:form>

    </body>
</html>

