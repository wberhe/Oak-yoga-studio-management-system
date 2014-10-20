<%-- 
    Document   : createSection
    Created on : Oct 17, 2014, 10:41:58 PM
    Author     : Weldino
--%>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Details</title>
        <link href="../resources/style.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <form:form commandName="product" action="../products/${product.id}" method="post">
            <form:hidden path="id" />
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
            <input type="submit" value="update"/>
        </form:form>

        <form action="delete?productId=${product.id}" method="post">
            <button type="submit">Delete</button>
        </form>
    </body>
</html>