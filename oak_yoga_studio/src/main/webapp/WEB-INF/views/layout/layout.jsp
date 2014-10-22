<%-- 
    Document   : layout
    Created on : Sep 21, 2014, 11:25:10 PM
    Author     : showaibshikdermohammad
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>

<html>
    <head>
        <title>OAK Yoga Studio</title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <link href="/oak_yoga_studio/resources/common_style.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <div id="container">
            <div id="header"> 
                <tiles:insertAttribute name="header" />
            </div>           
            <div class="clear"></div>
            <div id="sidebar">
                <c:if test="${empty loggedUser }">
                    <tiles:insertAttribute name="login" />
                </c:if>
                <c:if test="${not empty loggedUser }">
                    <tiles:insertAttribute name="navigation" />
                </c:if>
            </div>
            <div id="content">
                <!--<h2>Header</h2>-->
                <tiles:insertAttribute name="body" />
            </div>

            <div id="bottom">
                <!--
              <h2>Services</h2>
              <p><img class="imgleft" src="../resources/images/orb.png" alt="" /> text.. </p>
                --> 
            </div>

            <div class="clear"></div>
            <div id="footer">
                <tiles:insertAttribute name="footer" />
            </div>
        </div>
    </body>
</html>         