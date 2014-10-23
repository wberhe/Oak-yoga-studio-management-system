<%-- 
    Document   : viewWaivers
    Created on : Oct 21, 2014, 12:32:12 PM
    Author     : Fetiya
--%>


<%@taglib  prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="resources/css/style.css" rel="stylesheet" type="text/css" />
        <title>View Waivers</title>
    </head>
    <body>
        <h4>${msg}</h4>



        <div class="datagrid">
            <table >  <thead>

                    <tr>
                        <th>Course Number</th>
                        <th>Course Name </th>
                        <th>Status </th>
                        <th></td>
                        <td></td>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="w" items="${waivers}" varStatus="loopStatus" >

                        <tr class="${loopStatus.index % 2 == 0 ? 'alt2' :'alt'}" >

                            <td>${w.waiverCourse.courseNumber}</a></td>
                            <td>${w.waiverCourse.courseName }</td> 
                            <td>${w.status}</td>


                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>

</html>

