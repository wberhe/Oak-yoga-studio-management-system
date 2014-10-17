<%-- 
    Document   : navigation
    Created on : Sep 21, 2014, 11:45:19 PM
    Author     : showaibshikdermohammad
--%>
<div id="sidebar">
    <div class="navlist security" >
        <form method="post" action="j_spring_security_check">
            User:<input name="j_username"/><br/>
            Password:<input type="password" name="j_password"/><br/>
            <input type="submit"/>        <a href="addCredential">Sign Up</a>

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

