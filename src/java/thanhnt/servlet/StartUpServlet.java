/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thanhnt.servlet;

import java.io.IOException;
import java.util.Properties;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import thanhnt.registration.RegistrationDTO;
import thanhnt.util.ApplicationConstants;

/**
 *
 * @author thinkpad
 */
public class StartUpServlet extends HttpServlet {
//    private final String LOGIN_PAGE = "login.html";
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

        String url = siteMaps.getProperty(ApplicationConstants.StartUpFeature.LOGIN_PAGE);
        try {
//            //1. check existed cookies?
//            Cookie[] cookies = request.getCookies();
//            if (cookies != null) {
//                //2. get Name and Value (username and password)
//                Cookie lastCookie = cookies[cookies.length -1];
//                String username = lastCookie.getName();
//                String password = lastCookie.getValue();
//                //3. check authenticate of username and password (call DAO)
//                RegistrationDAO dao = new RegistrationDAO();
//                boolean result = dao.checkLogin(username, password);
//                //4. process result
//                if (result) {
//                    url = SEARCH_PAGE;
//                } //username and password are authenticated
//            } //end cookies are existed

            HttpSession session = request.getSession(false);
            if (session != null) {
                RegistrationDTO user = (RegistrationDTO) session.getAttribute("USER");
                if (user != null) {
                    url = siteMaps.getProperty(ApplicationConstants.StartUpFeature.SEARCH_PAGE);
                }
            }
        } finally {
            response.sendRedirect(url);
            //RequestDispather hay sendRedirect cung duoc
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
