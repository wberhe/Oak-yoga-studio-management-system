<%-- 
    Document   : createSection
    Created on : Oct 17, 2014, 10:41:58 PM
    Author     : Weldino
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create new Section</title>
    </head>
    <body>
        <form:form commandName="section" action="newSection" method="post" >

            <h2>Add a new section: </h2>
            <table>
                <h4>Select course :</h4>
                <tr>
                    <form:select path="course" >
			<form:options items="${allCourses}"  itemLabel="courseName" itemValue="id"/>
		</form:select>
                </tr>
                <tr>
                    <td>Section name:</td>
                    <td><form:input type="text" path="sectionName" /> </td>
                    <td><form:errors path="sectionName" cssClass="error"/> </td>
                </tr>
                <tr>
                    <td>Section capacity: </td>
                    <td><form:input  type="text" path="capacity" /> </td>
                    <td><form:errors path="capacity" cssClass="error"/> </td>
                </tr>
                <tr>
                    <td>Section room number: </td>
                    <td><form:input type="text"  path="roomNumber" /> </td>
                    <td><form:errors path="roomNumber" cssClass="error"/> </td>
                </tr>
                <tr>

                <td>Select available faculty </td>
                <td> <form:select path="professor" >
			<form:options items="${allFaculities}"  itemLabel="firstName" itemValue="id"/>
                    </form:select></td>
           
                </tr>

            </table>
                <form:hidden path="status" />
            <input type="submit" value="add section"/>
            <a href="admin"> Back to home</a>
        </form:form>
            
    </body>
</html>
