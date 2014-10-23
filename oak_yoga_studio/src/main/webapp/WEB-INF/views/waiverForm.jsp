<%-- 
    Document   : wavierForm
    Created on : Oct 19, 2014, 2:42:39 AM
    Author     : Fetiya
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
       

    </head>
    <body>
        <div align="center">
            <h1>Enter reason for waiver</h1>



            <form  action="${pageContext.request.contextPath}/waiverResult/${course.id}" method="post">
                <table>


                    <tr>
                        <td> Course Number   :   </td>
                        <td> ${course.courseNumber}</td>
                    </tr>
                    <tr>
                        <td>Course Name      :</td>
                        <td> ${course.courseName}</td>
                    </tr>

                    <tr>
                        <td> Reason</td>
                        <td><br/> <textarea name="text" rows="5" cols="50"></textarea><br/>
                        </td>
                    </tr>
                    <tr>
                        
                        <td colspan="2" align="right">   <input type="submit" value="Submit Request " /></td>
                    </tr>
                </table>

            </form>      


        </div>  

        <div align="center">
            <a href="${pageContext.request.contextPath}/index">Back</a></br>
        </div>
    </body>
</html>