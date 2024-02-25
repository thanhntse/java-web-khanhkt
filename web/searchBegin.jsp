<%-- 
    Document   : searchBegin
    Created on : Feb 25, 2024, 11:07:31 AM
    Author     : thinkpad
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        <% 
            Cookie[] cookies = request.getCookies();
            if (cookies != null)  {
                Cookie lastCookie = cookies[cookies.length -1];
                String username = lastCookie.getName();
                %>
                <font color="red">
                    Welcome, <%= username %>
                </font>
                <br/>
                <a href="login.html">Log out</a>
        <%
            }//end cookies are existed
        %>
        <h1>Search Page</h1>
        <form action="DispatchServlet">
            Search Value <input type="text" name="txtSearchValue" value="" /> <br/>
            <input type="submit" value="Search" name="btnAction" />
        </form>      
    </body>
</html>

