<%-- 
    Document   : createAccount
    Created on : Mar 7, 2024, 7:53:10 AM
    Author     : thinkpad
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create</title>
        <link rel="stylesheet" href="./css/login.css">
    </head>
    <body>
        <div class="login-container">
            <h2>Registration</h2>
            <form action="DispatchServlet" method="POST">
                <c:set var="errors" value="${requestScope.CREATE_ERRORS}" />
                <div>
                    <label for="username">Username*:</label> ( 6 - 20 chars ) <br/>
                    <input type="text" id="username" name="txtUsername" 
                           value="${param.txtUsername}">
                    <c:if test="${not empty errors.usernameLengthError}">
                        <font color="red">
                            ${errors.usernameLengthError}
                        </font> <br/><br/>
                    </c:if>
                    <c:if test="${not empty errors.usernameIsExisted}">
                        <font color="red">
                            ${errors.usernameIsExisted}
                        </font> <br/><br/>
                    </c:if>
                </div>
                <div>
                    <label for="password">Password*:</label> ( 6 - 30 chars )  <br/>
                    <input type="password" id="password" name="txtPassword"
                           value="">
                    <c:if test="${not empty errors.passwordLengthError}">
                        <font color="red">
                            ${errors.passwordLengthError}
                        </font> <br/><br/>
                    </c:if>
                </div>
                <div>
                    <label for="confirm">Confirm*:</label> <br/>
                    <input type="password" id="confirm" name="txtConfirm"
                           value="">
                    <c:if test="${not empty errors.confirmNotMatched}">
                        <font color="red">
                            ${errors.confirmNotMatched}
                        </font> <br/><br/>
                    </c:if>
                </div>
                <div>
                    <label for="fullname">Full name*:</label> ( 2 - 50 chars ) <br/>
                    <input type="text" id="fullname" name="txtFullname"
                           value="${param.txtFullname}">
                    <c:if test="${not empty errors.fullnameLengthError}">
                        <font color="red">
                            ${errors.fullnameLengthError}
                        </font> <br/><br/>
                    </c:if>
                </div>
                <div>
                    <input type="submit" value="Create new account" name="btnAction">
                </div>
            </form>
            <br/>
            <div class="sign-up">
                <p style="color:#6b7280;margin: 0px">You already a member?</p>
                <a href="login.html">Login</a>
            </div>
        </div>
    </body>
</html>
