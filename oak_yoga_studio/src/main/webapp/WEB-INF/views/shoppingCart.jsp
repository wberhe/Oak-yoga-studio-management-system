<%-- 
    Document   : productList
    Created on : Oct 19, 2014, 10:41:58 PM
    Author     : Weldu
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
        <p style="color:green">${message}</p>
        <h1>shopping cart Details</h1>
        <table >
            <tr><td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            <c:forEach var="item" items="${shoppingCartItems}">
                <tr>
                    <td>
                       <img src="productImage/${item.product.id}" width="200" height="200"/>
                    </td>
                    <td>${item.product.name}</td>
                    <td>(quantity: ${item.quantity})</td>
                    <td>USD $ ${item.product.price}</td>
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

        <form:form commandName="product" action="checkout" method="get">
            <input type="submit" value="Checkout"/>
           
        </form:form>
        
    

    </body>
</html>