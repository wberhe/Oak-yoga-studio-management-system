<%-- 
    Document   : AdviseesOFAfaculty
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
        <h3>List of all Advisees :</h3>
        <div class="datagrid">
            <table>
                <thead>
                    <tr>
                        <th></th>
                <h>First Name</th>
                    <th>Last Name </th>
                    <th>Email</th>
                    <th></th>
                    </tr>
                    </thead>
                    <c:forEach var="a" items="${advisees}">
                        <tr>
                            <td></td> 
                            <td><a href="adviseeDetail/${a.id}">${a.firstName}</a></td> 
                            <td><a href="adviseeDetail/${a.id}">${a.lastName}</a></td>
                            <td>${a.email}</td>
                            <td></td>                    
                        </tr>
                    </c:forEach>
            </table>
        </div>
    </body>
</html>
