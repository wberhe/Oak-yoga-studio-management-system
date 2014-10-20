<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Oak</title>
    </head>
    <body>
        <h1>Welcome to Oak Yoga Studio!!</h1>
        
        <sec:authorize access="hasRole('ROLE_CUSTOMER')">
          <p>Customer Profile Update <a href="editProfile">Edit Profile</a>
          <p>Please <a href="addCredential">sign up</a>
          <p>Enrollment <a href="enrollInCourse">enroll</a>
          <p>Product <a href="products">go to product list</a>
          <p>Search Product <a href="searchProduct">click here</a>
          <p>Shopping cart Product <a href="viewCart">view my cart</a>
             
        </sec:authorize>  
                
         <sec:authorize access="hasRole('ROLE_ADMIN')">
              <p>Administrator <a href="admin">go to admin home page</a>  
          </sec:authorize>       
       
       <sec:authorize access="hasRole('ROLE_FACULTY')">
        <p>Faculty <a href="faculty">go to faculty home page</a>
      </sec:authorize>    
  <p/><a href='${pageContext.request.contextPath}/j_spring_security_logout'><span>LOGOUT</span></a>

            
            
    </body>
</html>
