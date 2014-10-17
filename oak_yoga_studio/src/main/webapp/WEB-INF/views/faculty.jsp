<%-- 
    Document   : faculty
    Created on : Oct 16, 2014, 1:49:07 PM
    Author     : Senai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h3>This is faculty Home page</h3>
           <ul>
               <li>
                   <form commandName="advisee" action="viewAdvisees" method="post" enctype="multipart/form-data">
                    View Advisees of <input type="textfield" path="id"/>
                    <input type="submit" value="submit"/>
                   </form></li>
              <li>View Customer <a href="viewCustomers">View Customers</a></li>
            
            </ul>
    </body>
</html>