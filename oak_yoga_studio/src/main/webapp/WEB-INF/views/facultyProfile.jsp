<%-- 
    Document   : EditProfile
    Created on : Oct 15, 2014, 12:01:40 PM
    Author     : Senai
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Faculty Profile</title>
    </head>
    <body>
        <h3>My Profile :</h3><br/><br/><br/>
        <%--<form:form commandName="customer" action="updateProfile" method="post" enctype="multipart/form-data">--%>
        <form:form commandName="facultyDetail" action="updateFacultyProfile" method="post" enctype="multipart/form-data">
            <%--<form:hidden path="id" value="${customerDetail.id}"   />--%>
            <table class="profileTable">
                <tr>
                    <td colspan="2"><img  src="facultyProfileImage/${facultyDetail.id}" width="80" height="80"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td>Change Picture</td> 
                    <td><input type="file" name="file" /> </td>
                </tr>
                <tr>
                    <td><br/>First name:<br/></td>
                    <td><br/><form:input type="text" path="firstName"  value="${facultyDetail.firstName}"/> <br/></td>
                    <td><br/><form:errors path="firstName" cssClass="error" /> <br/></td>
                </tr>
                <tr>
                    <td><br/>Last name :<br/></td>
                    <td><br/><form:input type="text" path="lastName" size="30" value="${facultyDetail.lastName}" /> <br/> </td>
                    <td><br/><form:errors path="lastName" cssClass="error"/> <br/></td>
                </tr><form:hidden path="dateOfBirth" />
                <%--                <tr>
                                    <td> Birth Date :</td>
                                    <td><fmt:formatDate value="${customerDetail.dateOfBirth}" pattern="yyyy-MM-dd" var="birthDate"/>
                                    <form:input type="date" path="dateOfBirth" />
                                <td><form:errors path="dateOfBirth" cssClass="error"/> </td>
                            </tr>--%>
                <tr>
                    <td><br/> Email :<br/></td>
                    <td><br/><form:input type="text" path="email" size="30" value="${facultyDetail.email}" /><br/> </td>
                    <td><br/><form:errors path="email" cssClass="error"/> <br/></td>
                </tr>
            </table>   <br/>         
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

