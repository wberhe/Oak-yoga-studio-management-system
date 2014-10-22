<%-- 
    Document   : navigation
    Created on : Sep 21, 2014, 11:45:19 PM
    Author     : showaibshikdermohammad
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib  prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:if test="${not empty loggedUser }">
    Welcome,<br/>
    ${loggedUser.firstName} ${loggedUser.lastName}
    <a href="/oak_yoga_studio/j_spring_security_logout">Logout</a>
</c:if>
<div class="navlist">
    <ul>
        <li> <form action="searchProduct" method="POST">  
                <input type="text" name="productName" placeholder="search Product by name"/>              
                <input type="submit"/>
            </form>
        </li>
        <sec:authorize access="hasRole('ROLE_CUSTOMER')">
            <h2>Navigate</h2>
            <li>Customer Profile Update <a href="/oak_yoga_studio/editProfile">Edit Profile</a></li>
            <li>Enrollment <a href="/oak_yoga_studio/viewCourses">enroll</a></li>
            <li>Course Waiver Request  <a href="/oak_yoga_studio/requestWaiver">Request Waiver</a></li>
            <li>Enrollment History  <a href="/oak_yoga_studio/enrolled">Enrollment History</a></li>
            <li>Product <a href="/oak_yoga_studio/products">go to product list</a></li>
            <li>Shopping cart Product <a href="/oak_yoga_studio/viewCart">view my cart</a></li>
            </sec:authorize>

        <sec:authorize access="hasRole('ROLE_FACULTY')">
            <h2>Navigate</h2>
            <li> <a href="/oak_yoga_studio/viewAdvisees"> view Advisees</a></li>
            <li> <a href="/oak_yoga_studio/viewWaiverRequests">View Waiver Requests</a></li>
            <li> <a href="/oak_yoga_studio/viewFacultySections">View Sections</a></li>
            </sec:authorize>

        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <h2>Navigate</h2>
             <li>View Courses <a href="/oak_yoga_studio/viewCourses">click here to view</a></li>
              <li>Add Course <a href="/oak_yoga_studio/addCourse">Add Course</a></li>
              <li>Add Section <a href="/oak_yoga_studio/newSection">add section</a></li> 
              <li>view Sections <a href="/oak_yoga_studio/sectionList">view sections</a></li> 
              <li>View Customer <a href="/oak_yoga_studio/viewCustomers">View Customers</a></li>
              <li>Add Faculty <a href="/oak_yoga_studio/addFacultyCredential">click here to add</a></li>
              <li>View Faculty <a href="/oak_yoga_studio/viewFaculties">click here to view</a></li>  
              <li>Add Product <a href="/oak_yoga_studio/addProduct">add product</a></li> 
            </sec:authorize>
    </ul>
</div>


