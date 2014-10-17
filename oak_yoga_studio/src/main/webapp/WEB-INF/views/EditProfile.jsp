<%-- 
    Document   : EditProfile
    Created on : Oct 15, 2014, 12:01:40 PM
    Author     : Fetiya
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> update</title>
    </head>
    <body>
        <h3>Customer Information :</h3>
    
    <form:form commandName="customer" action="../updateProfile/${customerDetail.id}" method="post" enctype="multipart/form-data">

        <table>
            <tr>
       
                
                
                <td>Picture:</td>
                <td><img src="../image/${customerDetail.id}" width="80" height="80"/></td>

                <td>Change Picture</td> 
                <td><input type="file" name="file"   /> </td>
            </tr>

            <tr>
                <td>First name:</td>
               <td><form:input type="text" path="firstName"  value="${customerDetail.firstName}"/> </td>
               <td><form:errors path="firstName" cssClass="error" /> </td>
            </tr>
            <tr>
                <td>Last name :</td>
                <td><form:input type="text" path="lastName" size="30" value="${customerDetail.lastName}" />  </td>
                <td><form:errors path="lastName" cssClass="error"/> </td>

            </tr>
            <tr>
                <td> Email :</td>
                <td><form:input type="text" path="email" size="30" value="${customerDetail.email}" /> </td>
                <td><form:errors path="email" cssClass="error"/> </td>
            </tr>
          
        </table>
            
        
                <form:hidden path="dateOfBirth" />
              
<input type="submit" value="Update"/>

    </form:form>

</body>

</html>

