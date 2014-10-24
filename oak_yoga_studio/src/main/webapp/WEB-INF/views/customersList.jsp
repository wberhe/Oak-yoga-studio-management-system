<%-- 
    Document   : allcustomers
    Created on : Sep 20, 2014, 8:02:03 PM
    Author     : weldu
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin</title>
    </head>
    <body>
        <h3>List of all customers :</h3>
        <!--<form  action="sendEmail" method="post">-->
        <div class="datagrid">
            <table>
                <thead>
                    <tr>
                        <th></th>
                        <th><strong>First Name </strong></th>
                        <th><strong>Last Name </strong></th>
                        <th><strong>Email </strong></th>
                        <th><strong> </strong></th>

                    </tr>
                </thead>
                <c:forEach var="c" items="${customers}" varStatus="loopStatus">
                    <tr class="${loopStatus.index % 2 == 0 ? 'alt2' :'alt'}">
                        <td><input type="checkbox" name="ids" value="${c.id}"/></td> 
                        <td>${c.firstName}</td> 
                        <td>${c.lastName}</td>
                        <td>${c.email}</td>
                        <td><a href="customerDetail/${c.id}">customer details</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <!--            Subject: <input type="text" /><br/>
                    Email :<br/><textarea name="text" rows="5" cols="30"></textarea><br/>
                    <input type="submit" value="Send Email" />
                </form>-->

    </body>
</html>
