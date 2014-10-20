<%-- 
    Document   : searchProduct
    Created on : Oct 17, 2014, 10:41:58 PM
    Author     : Weldino
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>search Course</title>
    </head>
    <body>
        <h1>search Product</h1>
        
        <form action="searchProduct" method="POST">  
            <input type="text" name="productName" placeholder="search Product by name"/>              
            <input type="submit"/>
        </form>   
    </body>
</html>
