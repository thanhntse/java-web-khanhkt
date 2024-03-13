<%-- 
    Document   : success
    Created on : Mar 13, 2024, 6:26:07 PM
    Author     : thinkpad
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Success</title>
        <link rel="stylesheet" href="./css/success.css">
    </head>
    <body>
        <h1>Checkout successfully!!!</h1>
        <c:set var="total" value="${requestScope.TOTAL_PRICE}" />
        <h3>Your total price is: $${total}</h3>
        <br/>
        <a href="DispatchServlet?btnAction=Go to Shopping">Go to Shopping</a> <br/>
    </body>
</html>
