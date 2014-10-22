<%-- 
    Document   : productList
    Created on : Oct 17, 2014, 10:41:58 PM
    Author     : Weldino
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>oak products</title>
    </head>
    <body>
        <h2>Products currently in the oak yoga studio</h2>
        <table >
            <tr><td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            <c:forEach var="product" items="${products}">
                <tr>
                    <td>
                        <img src="/poductImage/${product.id}" width="200" height="200"/>
                    </td>
                    <td>${product.name} </td>

                    <td>USD :$ ${product.price}</td>
                    <sec:authorize access="hasRole('ROLE_CUSTOMER')" >
                        <td><form:form commandName="product" action="cart/${product.id}" method="post">
                                <input type="text" name="quantity" size="3"/>
                                <input style="background-color:lightgoldenrodyellow" type="submit" value="Add to cart"/>

                            </form:form>
                        </td>
                    </sec:authorize>

                    <sec:authorize access="hasRole('ROLE_ADMIN')" >
                        <td><a href="productEdit/${product.id}">  Edit Product</a></td>
                    </sec:authorize>
                </tr>
                <%--</form:form>--%> 
            </c:forEach>
        </table>

        <a style="color:blue" href="addProduct">Back</a>    

    </body>
</html>