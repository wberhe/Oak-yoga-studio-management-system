<%-- 
    Document   : addBlogCategory
    Created on : Sep 20, 2014, 8:08:59 PM
    Author     : aalzamer
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Tag</title>

        <link rel="stylesheet" type="text/css" href="/resources/style.css">
    </head>
    <body>
        <h1>Add Tag</h1>
        <form:form modelAttribute="tag" action="addTag">
            <table>
                <tr>
                    <td>Tag</td>
                    <td><form:input path="name" /></td>
                    <td><form:errors path="name" cssClass="errorBlock" element="div"/></td>
                </tr>
            </table>
            <form:button name="submit" >Submit</form:button>
        </form:form>
    </body>
</html>
