<%-- 
    Document   : product
    Created on : Feb 28, 2024, 9:38:06 PM
    Author     : thinkpad
--%>

<%@page import="java.util.List"%>
<%@page import="thanhnt.product.ProductDTO"%>
<%@page import="thanhnt.product.ProductDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Store</title>
        <link rel="stylesheet" href="./css/product.css">
    </head>
    <body>
        <h1>Book Store</h1>
        <%-- <%
            ProductDAO dao = new ProductDAO();
            dao.searchAllProduct();
            List<ProductDTO> result = dao.getProducts();
            if (result != null) {
                %> --%>

        <c:set var="result" value="${requestScope.PRODUCT_AVAILABLE}" />
        <c:if test="${not empty result}">
            <form action="DispatchServlet">
                <input type="submit" value="View my Cart" name="btnAction" />
            </form>
            <div class="product-card-container">
                <c:forEach var="dto" items="${result}">
                    <form action="DispatchServlet">
                        <div class="product-card">
                            <img src="./images/${dto.sku}.jpg" alt="Product 1">
                            <h3>${dto.name}</h3>
                            <input type="hidden" value="${dto.sku}" name="cboProduct" />
                            <p class="price">$${dto.unitPrice}</p>
                            <p>${dto.description}</p>
                            <input type="submit" value="Add to my Cart" name="btnAction" />
                        </div>
                    </form>
                </c:forEach>
            </div>
        </c:if>
        <c:if test="${empty result}">
            <h2>
                <font color="">
                No Book available!!!
                </font>
            </h2>
        </c:if>
    </body>
</html>
