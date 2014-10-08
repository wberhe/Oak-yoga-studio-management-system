<%-- 
    Document   : createPost
    Created on : Sep 20, 2014, 6:09:18 PM
    Author     : priya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create new post</title>
    </head>
    <body>
        <form:form commandName="post" action="../newpost/${Blog.id}" method="post" >

            
            <h2>You are posting to ${blog.name}</h2>
            <table>

                <tr>
                    <td>Title:</td>
                    <td><form:input  path="title" /> </td>
                </tr>
                <tr>
                    <td>Body:</td>
                    <td><form:textarea path="body" rows="5" cols="40"></form:textarea></td>

                    </tr>
                    <tr>
                        <td>Save Draft</td>
                        <td><form:checkbox path="draft"   /></td>
                </tr>
                <c:choose>
                    <c:when test="${postRating.rate>='1'}">
                        <td>Rating:</td>
                        <td>${postRating.rate}</td>
                    </c:when>

                </c:choose>
                <tr>
                    <td>Add category</td>
                    <td>
                        <form:select  path= "categories" size="10" multiple="true" >
                            <form:options items="${allCategories}" itemValue="id" itemLabel="name"/>
                            
                        </form:select>
                    </td>
                </tr>
                <tr>
                    <!--<td>Add category</td>-->
<!--                    <td>
                        <form:select  path= "selectedCat" >
                            <form:options items="${categories}" itemValue="id" itemLabel="name"></form:options>
                            <%--<c:forEach var="category" items="${categories}">--%>
                                <option value="${category.name}">${category.name}</option>
                            <%--</c:forEach>--%>
                        </form:select>
                    </td>-->
                </tr>
                <tr>
                    <td>Add tag</td>
                    <td>
                        <form:select  path= "postTags" size="10" multiple="true" >
                            <form:options items="${tags}" itemValue="name" itemLabel="name"/>
                        </form:select>
                      
                    </td>
                </tr>




            </table>
            <input type="submit" value="save"/>


        </form:form>
    </body>
</html>
