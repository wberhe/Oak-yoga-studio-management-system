<%-- 
    Document   : login
    Created on : Oct 19, 2014, 11:41:58 PM
    Author     : weldu
--%><%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>All Categories</title>
    </head>
    <body>
<div id="sidebar">
    <div class="navlist security">
        <div class="signupSuccess">${successfulSignup}</div>
        <h1>Login Page</h1>
        <c:if test="${error eq true}">
            <p>${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</p>
        </c:if>
        <form method="post" action="j_spring_security_check" />
            User: <input name="j_username" /> <br />
            Pass: <input type="password" name='j_password' /> <br />
            Remember me: <input type="checkbox" name="_spring_security_remember_me" /> <br />
            <input type="submit" value="Login" />
            
        </form>
    </div>
    <!--    <p> sample text.</p>
        <div class="navlist">
          <ul>
            <li><a href="#">Link one</a></li>
            <li><a href="#">Link two</a></li>
          </ul>
        </div>
        <p>Sample text </p>-->
</div>
         </body>
</html>