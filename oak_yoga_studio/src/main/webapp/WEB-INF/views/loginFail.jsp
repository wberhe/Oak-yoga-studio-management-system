<%-- 
    Document   : login
    Created on : oct 17, 2014, 4:15:49 PM
    Author     : Weldino
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <h1>Login Page!</h1>
        <c:if test="${error eq true}">
            <p>${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</p>
        </c:if>
        <form method="post" action="<c:url value='j_spring_security_check' />">
            User: <input name="j_username" value='<c:if test="${not empty param.login_error}"><c:out value="${SPRING_SECURITY_LAST_USERNAME}"/></c:if>'/> <br />
            Pass: <input type="password" name='j_password' /> <br />
            Remember me: <input type="checkbox" name="_spring_security_remember_me" /> <br />
            <input type="hidden" name="<c:out value="${_csrf.parameterName}"/>" value="<c:out value="${_csrf.token}"/>"/>
            <input type="submit" />
            <a href="addCredential">sign up</a>
        </form>
            <div style="color:red">login Failure, please check username/password</div>
    </body>