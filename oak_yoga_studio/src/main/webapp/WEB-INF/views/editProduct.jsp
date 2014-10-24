<%-- 
    Document   : editProduct
    Created on : Oct 17, 2014, 10:41:58 PM
    Author     : weldu
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Details</title>
        <link href="../resources/style.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <form:form modelAttribute="product" action="../productEdit" method="post" enctype="multipart/form-data">
            <form:errors path="*" cssClass="errorblock" element="div" />
            <table>
                <tr>
                    <td>Name:</td>
                    <td><form:input path="name" /> </td>
                    <td><form:errors path="name" cssClass="error" /> </td>
                </tr>
                <tr>
                    <td>Price:</td>
                    <td><form:input path="price" /> </td>
                    <td><form:errors path="price" cssClass="error" /> </td>
                </tr>
                <tr>
                    <td>Quantity:</td>
                    <td><form:input path="quantity" /> </td>
                    <td><form:errors path="quantity" cssClass="error"/> </td>
                </tr>
                <tr>
                    <td>Image:</td>
                    <td><input type="file" name="file"/> </td>
                </tr>
            </table>
                <form:hidden path="status" />
            <input type="submit" value="update"/>
        </form:form>
    </body>
</html>