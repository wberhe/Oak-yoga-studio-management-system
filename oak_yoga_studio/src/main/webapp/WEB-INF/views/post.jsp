<%-- 
    Document   : post
    Created on : Sep 20, 2014, 6:14:40 PM
    Author     : priya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Post</title>
    </head>
    <body>
        <form:form commandName="post" action="../viewPost/${post.id}" method="post" >
            <form:errors path="*" element="div"/>

            <table>
                <tr>
                    <td> ${author.firstname} ${author.lastname} wrote....</td>
                </tr>
                <tr>
                    <td> view Blog
                        <a href="../postList/${blog.id}"> ${blog.name} </a></td>
            </tr>   
            <tr>
                <td>Title:</td>
                <td>${post.title}</td>

            </tr>
            <tr>
                <c:choose>
                    <c:when test="${postRating.rate>='1'}">
                        <td>Rating:</td>
                        <td>${postRating.rate}</td>
                    </c:when>

                </c:choose>

            </tr>
            <tr>
                <td>Body:</td>
                <td>${post.body}</td>
            </tr>

            <tr>
                <td>Categories</td>
                <td>

                    <c:forEach var="category" items="${postCategories}">
                <tr>
              
                    <td>${category.name}</td>
                </tr>
                <option value="${category.name}">${category.name}</option>
            </c:forEach>

        </td>

    </tr>

    <tr>
        <td>Tags</td>
        <td>

            <c:forEach var="tag" items="${post.postTags}">
        <option value="${tag.name}">${tag.name}</option>
    </c:forEach>

</td>


</tr>

<tr>
    <c:choose>
        <c:when test="${blogger.ratedPost==false && blogger.id != author.id}">
            <!--<td>Rating: try when not rated</td>-->

            <td>
                <form:radiobutton path="tempRating" value="1"  />
                <form:radiobutton path="tempRating" value="2"  />
                <form:radiobutton path="tempRating" value="3"  />
                <form:radiobutton path="tempRating" value="4"  />
                <form:radiobutton path="tempRating" value="5"  />
            <td><input type="submit" value="rate"/> </td>

            </td>
        </c:when>
    </c:choose> 
</tr>


<td>Comments</td>


<c:forEach var="comment" items="${comments}">
    <tr>
         <!--<option value="${comment.comment}">${comment.comment}</option>-->
        <td>${comment.commentAuthor.firstname}</td>
        <td>${comment.comment}</td>

    </tr>

</c:forEach>




<!--</tr>-->    



<tr>

    <td><form:input  path="tempComment" /> </td>
    <td><input type="submit" value="post"/> </td>

</tr>


</table>

</form:form>
</body>
</html>
