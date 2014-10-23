<%-- 
    Document   : EditProfile
    Created on : Oct 15, 2014, 12:01:40 PM
    Author     : Fetiya
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Customer Profile </title>
    </head>
    <body>
        <h3>My Profile :</h3>
        <%--<form:form commandName="customer" action="updateProfile" method="post" enctype="multipart/form-data">--%>
        <form:form commandName="customerUpdate" action="updateProfile" method="post" enctype="multipart/form-data">
            <%--<form:hidden path="id" value="${customerDetail.id}"   />--%>
            <table width="100%" class="profileTable">
                <tr>
                    <td width="20%"> &nbsp; </td>
                    <td><img src="profileImage/${customerDetail.id}" class="profilePic"/></td>

                    <td>Change Picture</td> 
                    <td><input type="file" name="file" /> </td>
                </tr>

                <tr> 
                    <td>  First name:<br/><br/></td>
                    <td><form:input type="text" path="firstName"  value="${customerDetail.firstName}"/> </td>
                    <td><form:errors path="firstName" cssClass="error" /> </td>
                </tr>

                <tr>
                    <td> Last name :<br/>                        <br/></td>

                    <td><form:input type="text" path="lastName" size="30" value="${customerDetail.lastName}" />  </td>
                    <td><form:errors path="lastName" cssClass="error"/> </td>
                </tr>

                <form:hidden path="dateOfBirth" />

                <tr>               
                    <td> Email :<br/>  <br/></td>
                    <td><form:input type="text" path="email" size="30" value="${customerDetail.email}" /> </td>
                    <td><form:errors path="email" cssClass="error"/> </td>
                </tr>
            </table>            
            <input type="submit" value="Update User Info"/>

        </form:form>
        <%-- <form:form commandName="addressUpdate" action="updateAddress" method="post" >
             <form:hidden path="id" value="${addressDetail.id}"   />
             <table>
                 <tr>
                     <td>Street :</td>
                     <td><form:input type="text" path="street"  value="${addressDetail.street}"/> </td>
                     <td><form:errors path="street" cssClass="error" /> </td>
                 </tr>
                 <tr>
                     <td>City :</td>
                     <td><form:input type="text" path="city"  value="${addressDetail.city}"/> </td>
                     <td><form:errors path="firstName" cssClass="error" /> </td>
                 </tr>
                 <tr>
                     <td>State :</td>
                     <td><form:input type="text" path="state" size="30" value="${addressDetail.state}" />  </td>
                     <td><form:errors path="lastName" cssClass="error"/> </td>
                 </tr>
                 <tr>
                     <td> Zip Code :</td>
                     <td><form:input type="text" path="zipCode" size="30" value="${addressDetail.zipCode}" /> </td>
                     <td><form:errors path="email" cssClass="error"/> </td>
                 </tr>
             </table>

            <input type="submit" value="Update Address"/>
        </form:form>--%>
        <%--<form:form commandName="credential" action="editCredential" method="post">
            <table>
                <tr>
                    <td>Old Password :</td>
                    <td><form:input type="password" path="password" /> </td>
                    <td><form:errors path="password" cssClass="error"/> </td>
                </tr>
                <tr>
                    <td>New Password :</td>
                    <td><form:input type="password" path="password" /> </td>
                    <td><form:errors path="password" cssClass="error"/> </td>
                </tr>
                <tr>
                    <td>Confirm New Password :</td>
                    <td><form:input type="password" path="confirmPassword" /> </td>
                    <td><form:errors path="confirmPassword" cssClass="error"/> </td>
                </tr>
            </table>
            <input type="submit" value="Update Password"/>--%>
        <%--</form:form>--%>
        <!--<input type="submit" value="Update Profile"/>-->
        <%--</form:form>--%>
    </body>
</html>

