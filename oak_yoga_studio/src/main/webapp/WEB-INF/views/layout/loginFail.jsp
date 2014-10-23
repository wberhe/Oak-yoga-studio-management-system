<%-- 
    Document   : navigation
    Created on : Sep 21, 2014, 11:45:19 PM
    Author     : showaibshikdermohammad
--%>
<div id="sidebar">
    <div class="navlist security" >
        <h1>Login Page!</h1>
        <c:if test="${error eq true}">
            <p>${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</p>
        </c:if>
        <form method="post" action="/oak_yoga_studio/j_spring_security_check"
            Pass: <input type="password" name='j_password' /> <br />
            Remember me: <input type="checkbox" name="_spring_security_remember_me" /> <br />
            <input type="submit" value="Sign in"/>
            <a href="addCredential">sign up</a>
        </form>
            <div style="color:red">login Failure, please check username/password</div>
    </div>
    <!--    <p> sample text.</p>
        <div class="navlist">
          <ul>
            <li><a href="#">Link one</a></li>
            <li><a href="#">Link two</a></li>
          </ul>
        </div>
        <p>Sample text </p>-->
</div>

