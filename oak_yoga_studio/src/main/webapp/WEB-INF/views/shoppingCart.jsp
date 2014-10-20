<%-- 
    Document   : productList
    Created on : Oct 19, 2014, 10:41:58 PM
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
        <title>Yoga Products currently in the studio</title>
        <script>
        function confirmComplete() {
alert("confirmComplete");
var answer=confirm("Are you sure you want to delete the item from the shopping cart?");
if (answer==true)
  {
    return true;
  }
else
  {
    return false;
  }
}    
        </script>
        
    </head>
    <body>
        <h1>Products</h1>
        <table border='1'>
            <c:forEach var="item" items="${shoppingCartItems}">
                <tr>
                    <td>${item.product.name}</td>
                    <td>${item.quantity}</td>
                    <td>${item.product.price}</td>
                    <td>
                        <form:form action="${pageContext.request.contextPath}/cart/addOne/${item.id}" method="post">
                            <input type="submit" value="+"/>
                        </form:form>
                    </td>
                    <td>
                        <form:form action="${pageContext.request.contextPath}/cart/subtractOne/${item.id}" method="post">
                            <input type="submit" value="-"/>
                        </form:form>
                    </td>
                    <td>
                        <form:form action="${pageContext.request.contextPath}/cart/delete/${item.id}" method="post">
                            <input type="submit" value="Delete" onclick="return confirmComplete();"/>
                        </form:form>
                    </td>
                </tr>

            </c:forEach>
        </table>
        Total Price is:
        <c:out value="${totalPrice}"/></br>

        <form:form commandName="product" action="${pageContext.request.contextPath}/checkout" method="post">
<!--            <input type="hidden" value="${totalPrice}"/>-->
            <input type="submit" value="Checkout"/>
        </form:form>

    

    </body>
</html>