<%-- 
    Document   : AddCourse
    Created on : Sep 21, 2014, 11:00:52 AM
    Author     : Weldino
--%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

    <h2>Add Course</h2>
    <form:form commandName="course" action="addCourse" method="post">
	<table>
		<tr>
			<td>Course Number</td>
			<td><form:input type="text" path="courseNumber" /> </td>
                        <td><form:errors path="courseNumber" cssClass="error" /> </td>
		</tr>
                <tr>
			<td>Course Name:</td>
			<td><form:input type="text" path="courseName" /> </td>
                        <td><form:errors path="courseName" cssClass="error" /> </td>
		</tr>
		<tr>
			<td>Description:</td>
                        <td><form:textarea path="description" rows="5" cols="40"></form:textarea></td>
                        <td><form:errors path="description" cssClass="error" /> </td>
		</tr>
                <tr>
                <form:select path="prerequisites" >
			<form:options items="${ALLCourses}"  itemLabel="courseName" itemValue="id"/>
		</form:select>
                </tr>
        </table> 
                <form:hidden path="active"  /> 
	<input type="submit"/>
	
	</form:form>
