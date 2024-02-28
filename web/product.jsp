<%-- 
    Document   : product
    Created on : Feb 28, 2024, 9:38:06 PM
    Author     : thinkpad
--%>

<%@page import="java.util.List"%>
<%@page import="thanhnt.product.ProductDTO"%>
<%@page import="thanhnt.product.ProductDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product</title>
    </head>
    <body>
        <h1>Book Store</h1>
        <%
            ProductDAO dao = new ProductDAO();
            dao.searchAllProduct();
            List<ProductDTO> result = dao.getProducts();
            if (result != null) {
                %>
                <form action="DispatchServlet">
                    Choose product <select name="cboBook">
                        <%
                            for (ProductDTO dto : result) {
                                %>
                                <option>
                                    <%= dto.getName() %>
                                </option>
                        <%
                            }
                        %>
                    </select> <br/>
                    <input type="submit" value="Add to my Cart" name="btnAction" />
                    <input type="submit" value="View my Cart" name="btnAction" />
                </form>
        <%
            } else {
                %>
                <h2>
                    <font color="">
                        No product available!!!
                    </font>
                </h2>
        <%
            }
        %>
    </body>
</html>
