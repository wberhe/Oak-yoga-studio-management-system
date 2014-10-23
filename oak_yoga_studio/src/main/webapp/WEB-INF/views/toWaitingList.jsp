<%-- 
    Document   : toWaitingList
    Created on : Oct 18, 2014, 3:11:58 PM
    Author     : Fetiya
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       <h1>${message} ${section.id}</h1>
    </body>
 
    <form:form commandName="section" action="../waitingListResult/${section.id}" method="post">
	
            
        
                <input type="submit" value="Add me To WaitingList"/> 
                
                <input type="cancel" value="Cancel" /> 
                
	
	</form:form>

</html>
