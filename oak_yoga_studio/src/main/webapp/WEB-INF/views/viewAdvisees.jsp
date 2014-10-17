<%-- 
    Document   : viewAdvisees
    Created on : Oct 16, 2014, 11:02:31 AM
    Author     : Senai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h2>View Advisees</h2>
    <form:form commandName="course" action="addCourse" method="post">
	<table>
		<tr>
			<td>Faculty Name:</td>
			<td><form:input type="text" path="" /> </td>
                        <td><form:errors path="courseNumber" cssClass="error" /> </td>
		</tr>
                
        </table> 
                <form:hidden path="active"  /> 
	<input type="submit"/>
	
	</form:form>
