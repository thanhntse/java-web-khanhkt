<%-- 
    Document   : viewCart
    Created on : Feb 26, 2024, 9:05:08 AM
    Author     : thinkpad
--%>

<%@page import="java.util.Map"%>
<%@page import="thanhnt.cart.CartObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart</title>
    </head>
    <body>
        <h1>Your Cart</h1>
        <%
            //1. Cust goes to cart place
            if (session != null) {
                //2. Cust take his/her cart (existes)
                CartObject cart = (CartObject)session.getAttribute("CART");
                if (cart != null) {
                    //3. Cust gets items
                    Map<String, Integer> items = cart.getItems();
                    if (items != null) {
                        //4. Show all item
                        %>
                        <table border="1">
                            <thead>
                                <tr>
                                    <th>No.</th>
                                    <th>Name</th>
                                    <th>Quantity</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    int count = 0;
                                    for (String key : items.keySet()) {
                                        %>
                                        <tr>
                                            <td>
                                                <%= ++count %>
                                            .</td>
                                            <td>
                                                <%= key %>
                                            </td>
                                            <td>
                                                <%= items.get(key) %>
                                            </td>
                                        </tr>
                                <%
                                    }// traveses items
                                %>
                            </tbody>
                        </table>

        <%
                        return;
                    }
                }//end cart has existed
            }//session has existed
        %>
        
        <h2 style="color:red">
            No cart is NOT EXISTED!!!
        </h2>
    </body>
</html>
