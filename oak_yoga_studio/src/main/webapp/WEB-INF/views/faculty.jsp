<%-- 
    Document   : adviseesList
    Created on : Oct 16, 2014, 1:49:07 PM
    Author     : Senai
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>oak</title>
        <link href="resources/style.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <h3>This is faculty Home page</h3>
        <ul>
            <li> <a href="facultyProfile">My Profile</a></li>
            <li>
                <!--                <form commandName="advisee" action="viewAdvisees" method="post" enctype="multipart/form-data">
                                    <input type="textfield" path="id"/>
                                    <input type="submit" value="submit"/>
                                    </form>-->
                <a href="viewAdvisees"> view Advisees</a>
            </li>
            <li> <a href="viewWaiverRequests">View Waiver Requests</a></li>
            <li> <a href="viewFacultySections">View Sections</a></li>


        </ul>
    </body>
</html>
