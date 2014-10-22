<%-- 
    Document   : header
    Created on : Oct 19, 2014, 7:43:22 AM
    Author     : Somayeh
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page session="true"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Pragma" content="no-cache">
        <meta http-equiv="Expires" content="-1">
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
        <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <c:url var="cssUrl" value='/css/style-slider.css'></c:url>
        <link rel="stylesheet" type="text/css" media="screen" href="${cssUrl}"/>
        <link href='http://fonts.googleapis.com/css?family=Open+Sans:300,300italic,400italic,400,600,600italic,700,700italic,800,800italic' rel='stylesheet' type='text/css'>

        <link href='http://fonts.googleapis.com/css?family=Droid+Sans' rel='stylesheet' type='text/css'>
        <meta charset="utf-8">
        <c:url var="cssMenuUrl" value='/css/style-menu.css'></c:url>
        <link href="${cssMenuUrl}" media="screen" rel="stylesheet" type="text/css" />
        <c:url var="cssIconUrl" value='/css/iconic.css'></c:url>
        <link href="${cssIconUrl}" media="screen" rel="stylesheet" type="text/css" />
        <c:url var="jsMenu" value='/js/prefix-free.js'></c:url>
        <script src="${jsMenu}"></script>

        <script type="text/javascript">
            jQuery(document).ready(function() {
                $("#slideshow > div:gt(0)").hide();

                setInterval(function() {
                    $('#slideshow > div:first')
                            .fadeOut(1000)
                            .next()
                            .fadeIn(1000)
                            .end()
                            .appendTo('#slideshow');
                }, 3000);
            });
        </script>
    </head>
    <div id="header">
        <div class="container2">
            <div id="content-slider">
                <div id="slider">
                    <div id="mask">
                        <ul>
                            <li id="first" class="firstanimation">
                                <a href="#">
                                    <c:url var="imgUrl1" value='/images/img_1.jpg'></c:url>
                                    <img src="${imgUrl1}" alt="Candles"/>
                                </a>
                                <div class="tooltip">
                                    <h1>Team</h1>
                                </div>
                            </li>

                            <li id="second" class="secondanimation">
                                <a href="#">
                                    <c:url var="imgUrl2" value='/images/img_2.jpg'></c:url>
                                    <img src="${imgUrl2}" alt="Yoga"/>
                                </a>
                                <div class="tooltip">
                                    <h1>Yoga</h1>
                                </div>
                            </li>

                            <li id="third" class="thirdanimation">
                                <a href="#">
                                    <c:url var="imgUrl3" value='/images/img_3.jpg'></c:url>
                                    <img src="${imgUrl3}" alt="Relax"/>
                                </a>
                                <div class="tooltip">
                                    <h1>Relax</h1>
                                </div>
                            </li>

                            <li id="fourth" class="fourthanimation">
                                <a href="#">
                                    <c:url var="imgUrl4" value='/images/img_4.jpg'></c:url>
                                    <img src="${imgUrl4}" alt="Oak"/>
                                </a>
                                <div class="tooltip">
                                    <h1>Oak</h1>
                                </div>
                            </li>

                            <li id="fifth" class="fifthanimation">
                                <a href="#">
                                    <c:url var="imgUrl5" value='/images/img_5.jpg'></c:url>
                                    <img src="${imgUrl5}" alt="Health"/>
                                </a>
                                <div class="tooltip">
                                    <h1>Health</h1>
                                </div>
                            </li>
                        </ul>
                    </div>
                    <div class="progress-bar"></div>
                </div>
            </div>
        </div>
    </div>

    <c:url value="/j_spring_security_logout" var="logoutUrl" />
    <!-- csrt for log out-->
    <form action="${logoutUrl}" method="post" id="logoutForm">
        <input type="hidden" name="${_csrf.parameterName}"
               value="${_csrf.token}" />
    </form>

    <script>
        function formSubmit() {
            document.getElementById("logoutForm").submit();
        }
    </script>

    <div class="wrap">
        <nav>
            <ul class="menu">
                <li><a href="<c:url value='/main' />"><span class="iconic"></span> Home</a></li>
                <li><a href="<c:url value='/about' />"><span class="iconic"></span> About</a></li>

                <li><a href="<c:url value='/contact-us' />"><span class="iconic"></span> Contact</a></li>

                <li><a href="#"><span class="iconic"></span> Services</a>
                    <ul>
                        <c:if test="${pageContext.request.userPrincipal.name != null}">
                            <li><a href="<c:url value='/view-products' />">View Products</a></li>
                            </c:if>
                        <li><a href="<c:url value='/view-classes' />">View Yoga Classes</a></li>
                    </ul>                
                </li>
                <li> <a href="javascript:formSubmit()"><span class="iconic"></span> Logout</a> </li>

                <c:if test="${pageContext.request.userPrincipal.name == null}">
                    <li> <a href="<c:url value='/login' />"> <span class="iconic"></span>Login</a> </li>
                    </c:if>
            </ul>
            <div class="clearfix"></div>
        </nav>
    </div>
</html>
