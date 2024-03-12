/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thanhnt.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import thanhnt.registration.RegistrationDAO;
import thanhnt.registration.RegistrationDTO;
import thanhnt.util.ApplicationConstants;

/**
 *
 * @author thinkpad
 */
public class LoginServlet extends HttpServlet {

//    private final String INVALID_PAGE = "invalid.html";
//    private final String SEARCH_PAGE = "search.jsp";
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //0. Get Context Scope & get siteMaps
        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties) context.getAttribute("SITEMAPS");

        //1. Get all client information
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
//        String url = INVALID_PAGE;
        String url = siteMaps.getProperty(ApplicationConstants.LoginFeature.INVALID_PAGE);

        try {
            //2. Call model
            //2.1 new DAO
            RegistrationDAO dao = new RegistrationDAO();
            //2.2 call method of DAO
//            boolean result = dao.checkLogin(username, password);
            RegistrationDTO result = dao.checkLogin(username, password);
            //3. process
            if (result != null) {
                url = siteMaps.getProperty(ApplicationConstants.LoginFeature.SEARCH_PAGE);

                HttpSession session = request.getSession();
                session.setAttribute("USER", result);
                //sending cookie to client
//                Cookie cookie = new Cookie(username, password);
//                cookie.setMaxAge(3 * 60);
//                response.addCookie(cookie);
            }
        } catch (SQLException ex) {
            log("LoginServlet _ SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            log("LoginServlet _ Naming: " + ex.getMessage());
        } finally {
            response.sendRedirect(url);
            //dung forward se ra JsessionID
//            RequestDispatcher rd = request.getRequestDispatcher(url);
//            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
