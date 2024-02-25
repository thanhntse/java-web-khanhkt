<%-- 
    Document   : search
    Created on : Feb 1, 2024, 7:12:32 AM
    Author     : thinkpad
--%>

<%@page import="thanhnt.registration.RegistrationDTO"%>
<%@page import="java.util.List"%>
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
        <%
            }//end cookies are existed
        %>
        <h1>Search Page</h1>
        <form action="DispatchServlet">
            Search Value <input type="text" name="txtSearchValue" 
                                value="<%= request.getParameter("txtSearchValue") %>" /> <br/>
            <input type="submit" value="Search" name="btnAction" />
        </form>
        <br />
        <% 
            String searchValue = request.getParameter("txtSearchValue");
            if (searchValue != null) {
                List<RegistrationDTO> result = 
                        (List<RegistrationDTO>)request.getAttribute("SEARCH_RESULT");
                if (result != null) { //search if found
                    %>
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Username</th>
                                <th>Password</th>
                                <th>Full name</th>
                                <th>Role</th>
                                <th>Delete</th>                               
                                <th>Update</th> 
                            </tr>
                        </thead>
                        <tbody>
                            <% 
                                int count = 0;
                                for (RegistrationDTO dto : result) {
                                    String urlRewriting = "DispatchServlet"
                                            + "?btnAction=delete"
                                            + "&pk=" + dto.getUsername()
                                            + "&lastSearchValue=" + searchValue;
                                    %>
                        <form action="DispatchServlet" method="POST">
                                    <tr>
                                <td>
                                    <%= ++count %>
                                .</td>
                                <td>
                                    <%= dto.getUsername() %>
                                    <input type="hidden" name="txtUsername" 
                                           value="<%= dto.getUsername() %>" />
                                </td>
                                <td>
                                    <input type="text" name="txtPassword" 
                                           value="<%= dto.getPassword() %>" />
                                </td>
                                <td>
                                    <%= dto.getFullname() %>
                                </td>
                                <td>
                                    <input type="checkbox" name="chkAdmin" 
                                           value="ON" 
                                           <% 
                                                if (dto.isRole()) {
                                                    %>
                                                    checked="checked"
                                           <%
                                                } //end role is an admin
                                           %>
                                           />
                                </td>
                                <td>
                                    <a href="<%= urlRewriting %>">Delete</a>
                                </td>
                                <td>
                                    <input type="submit" value="Update" name="btnAction" />
                                    <input type="hidden" name="lastSearchValue" 
                                           value="<%= searchValue%>" />
                                </td>
                            </tr>
                        </form>
                            <%
                                } //traverse result to get each account
                            %>
                        </tbody>
                    </table>

        <%
                } else {
                    %>
                    <h2>
                        <font color="">
                            No record is matched!!!
                        </font>
                    </h2>
        <%
                } //end search is not found
            } //Search Value is null if user access directly
        %>
        
    </body>
</html>
