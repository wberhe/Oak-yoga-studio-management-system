<%-- 
    Document   : addCar
    Created on : Sep 16, 2014, 11:53:45 PM
    Author     : showaibshikdermohammad
--%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

    <h2>Create Blog</h2>
    <form:form commandName="blog" action="blog" method="post">
	<table>
		<tr>
			<td>Name:</td>
			<td><form:input type="text" path="name" /> </td>
                        <td><form:errors path="name" cssClass="error" /> </td>
		</tr>
		<tr>
			<td>Text:</td>
                        <td><form:textarea path="description" rows="5" cols="40"></form:textarea></td>
                        <td><form:errors path="description" cssClass="error" /> </td>
		</tr>
        </table>        
	<input type="submit"/>
	
	</form:form>
