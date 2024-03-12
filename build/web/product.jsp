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
        <title>Product</title>
    </head>
    <body>
        <h1>Convenience Store</h1>
        <%-- <%
            ProductDAO dao = new ProductDAO();
            dao.searchAllProduct();
            List<ProductDTO> result = dao.getProducts();
            if (result != null) {
                %> --%>

        <c:set var="result" value="${requestScope.PRODUCT_AVAILABLE}" />
        <c:if test="${not empty result}">
            <form action="DispatchServlet">
                Choose product <select name="cboProduct">
                    <c:forEach var="dto" items="${result}">
                        <option>
                            ${dto.name}
                        </option>
                    </c:forEach>
                </select> <br/>
                <input type="submit" value="Add to my Cart" name="btnAction" />
                <input type="submit" value="View my Cart" name="btnAction" />
            </form>
        </c:if>
        <c:if test="${empty result}">
            <h2>
                <font color="">
                No product available!!!
                </font>
            </h2>
        </c:if>
    </body>
</html>
