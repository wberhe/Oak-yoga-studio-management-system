<%-- 
    Document   : editPost
    Created on : Sep 21, 2014, 3:44:25 PM
    Author     : priya
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Edit post</h1>

          <form:form commandName="post" action="../editPost/${post.id}" method="post" >
              
        

            <table>
                <tr> <a href="../deletePost/${post.id}">delete post</a></tr>
               
                <tr>
                    
                    <td>Title:</td>
                    <td><form:input  path="title"/> </td>
                </tr>
                <tr>
                    <td>Body:</td>
                    <td><form:textarea path="body" rows="5" cols="40"></form:textarea></td>
                    
                </tr>
                <tr>
                    <td>Save Draft</td>
                     <td><form:checkbox path="draft"   /></td>
                </tr>

                <tr>
                    <td>Categories</td>
 
                    <td>
                        <form:select  path= "categories" size="10" multiple="true" >
                            <form:options items="${allCategories}" itemValue="id" itemLabel="name"/>
                            
                        </form:select>
                    </td>
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
