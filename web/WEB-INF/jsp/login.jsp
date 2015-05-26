<%-- 
    Document   : login
    Created on : Aug 29, 2014, 10:29:23 AM
    Author     : Surachai
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div class="form-box" id="login-box">
    <div class="header">
        <img src="${pageContext.request.contextPath}/img/smilogo.jpg" class="logo-img" />      
    </div>
    <form class="form-horizontal" role="form" name="formApplication" id="formApplication" method="post" action="login.smi"> 
        <div class="body bg-gray">
            
            <div class="form-group">
                <label for="inputEmail3" class="col-sm-3 control-label">Username</label>
                <div class="col-sm-9">
                    <input type="text" name="username" id="username" tabindex="1"  class="form-control"  placeholder="ID" required>
                </div>
            </div>
            <div class="form-group">
                <label for="inputPassword3" class="col-sm-3 control-label">Password</label>
                <div class="col-sm-9">
                    <input type="password" name="password" id="password" class="form-control" tabindex="2" placeholder="Password" required>
                </div>
            </div>
            <hr>
            <div class="footer">                                                               
                <button type="button" id="loginbutton" onclick="login()" style="width:100%"  tabindex="3" class="btn bg-olive btn-primary">Sign in</button>
            </div>
            <input type="hidden" name="action" id="action"/>
        </div>
    </form>
    <!--
    <form name="formApplication" id="formApplication" method="post" action="login.smi">
        <div class="body bg-gray">
            <div class="form-group">
                <input type="text" name="username"  class="form-control"  placeholder="ID" required>
            </div>
            <div class="form-group">
                <input type="password" name="password" class="form-control" tabindex="2" placeholder="Password" required>
            </div>
        </div>
        <div class="footer">                                                               
            <button type="button" onclick="login()" class="btn bg-olive btn-block">Sign me in</button>
        </div>
        <input type="hidden" name="action" id="action"/>
    </form>
    -->
</div>

<!--
<form class="box login" name="formApplication" id="formApplication" method="post" action="login.smi" >
    <img id="logo" src="${pageContext.request.contextPath}/img/logo.png" />
    <fieldset class="boxBody">
        <label>Username</label>
        <input type="text" name="username" tabindex="1" placeholder="ID" required>
        <label>Password</label>
        <input type="password" name="password" tabindex="2" placeholder="Password" required>
    </fieldset>
    <footer>
        <label><input type="checkbox" tabindex="3">Keep me logged in</label>
        <button class="btnLogin" onclick="login()" tabindex="4">Login</button>
       
    </footer>
    <input type="hidden" name="action" id="action"/>
</form>
-->
<script src="js/login.js"></script>
<c:if test="${! empty requestScope['ResultLogin']}">
    <script language="javascript">
                alert('<c:out value="${requestScope['ResultLogin']}" />');
    </script>
</c:if>
