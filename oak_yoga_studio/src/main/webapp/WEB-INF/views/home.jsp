<%-- 
    Document   : home
    Created on : Sep 22, 2014, 8:03:21 PM
    Author     : priya
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib  prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home page</title>
    </head>
    <body>
        <h1>See what's trending today</h1>
        <table>

            <tr bgcolor="#ABB2BA">
                <td width="20%">Posts</td>
                <td width="50%"></td>


            </tr>




            <c:forEach var="post" items="${posts}">
                <tr bgcolor="#D1D5DA">

                    <td> <a href="viewPost/${post.id}">${post.title}</a></td>
                        <c:choose>
                            <c:when test="${fn:length(post.body) > 15}" >
                            <td>${fn:substring(post.body, 0, 15)}...</td>
                        </c:when>
                        <c:otherwise>
                            <td>${post.body}</td>
                        </c:otherwise>         
                    </c:choose>

                </tr>
            </c:forEach>




        </table>
    </body>
</html>
