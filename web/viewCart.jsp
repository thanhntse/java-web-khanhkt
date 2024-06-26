<%-- 
    Document   : viewCart
    Created on : Feb 26, 2024, 9:05:08 AM
    Author     : thinkpad
--%>

<%@page import="java.util.Map"%>
<%@page import="thanhnt.cart.CartObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart</title>
        <link rel="stylesheet" href="./css/viewCart.css">
    </head>
    <body>
        <h1 style="text-align: center">Your Cart</h1>
        <%--    <%
            //1. Cust goes to cart place
            if (session != null) {
                //2. Cust take his/her cart (existes)
                CartObject cart = (CartObject)session.getAttribute("CART");
                if (cart != null) {
                    //3. Cust gets items
                    Map<String, Integer> items = cart.getItems();
                    if (items != null) {
                        //4. Show all item
                        %>      --%>
        <c:set var="result" value="${sessionScope.CART}" />
        <c:if test="${not empty result}">
            <c:set var="items" value="${result.items}" />
            <c:if test="${not empty items}">
                <form action="DispatchServlet">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Name</th>
                                <th>Unit Price</th>
                                <th>Quantity</th>
                                <th>Total Price</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${items}" varStatus="counter">
                                <tr>
                                    <td>
                                        ${counter.count}
                                        .</td>
                                    <td>
                                        ${item.name}
                                    </td>
                                    <td>
                                        $${item.unitPrice}
                                    </td>
                                    <td>
                                        ${item.quantity}
                                    </td>
                                    <td>
                                        $${item.quantity * item.unitPrice}
                                    </td>
                                    <td>
                                        <input type="checkbox" name="chkItem" 
                                               value="${item.sku}" />
                                    </td>
                                </tr>
                            </c:forEach>

                            <tr>
                                <td colspan="5">
                                    <c:url var="addMoreToCartLink" value="DispatchServlet">
                                        <c:param name="btnAction" value="Go to Shopping" />
                                    </c:url>
                                    <a href="${addMoreToCartLink}">
                                        Add more to my Cart
                                    </a>
                                </td>
                                <td>
                                    <input type="submit" value="Remove" name="btnAction" />
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </form>

                <form action="DispatchServlet">
                    <input type="submit" value="Checkout" name="btnAction" />
                </form>
                <c:set var="errors" value="${requestScope.ORDER_DETAIL_ERRORS}" />     
                <c:if test="${not empty errors.notEnoughQuantity}">
                    <font color="red">
                    <c:forEach var="error" items="${errors.notEnoughQuantity}">
                        ${error} <br/>
                    </c:forEach>
                    </font> <br/>
                </c:if>
            </c:if>
            <c:if test="${empty items}">
                <h2 style="color:red;text-align: center">
                    <img src="images/cartempty.png" alt="cartempty"/>
                    <div>EMPTY Cart!!!</div>
                </h2>
            </c:if>
        </c:if>
        <c:if test="${empty result}">
            <h2 style="color:red;text-align: center">
                <img src="images/cartempty.png" alt="cartempty"/>
                <div>Cart is NOT EXISTED!!!</div>
            </h2>
        </c:if>
    </body>
</html>
