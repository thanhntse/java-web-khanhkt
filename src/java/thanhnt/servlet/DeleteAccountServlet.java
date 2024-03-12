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
import thanhnt.registration.RegistrationDAO;
import thanhnt.util.ApplicationConstants;

/**
 *
 * @author thinkpad
 */
public class DeleteAccountServlet extends HttpServlet {
//    private final String ERRORS_PAGE = "errors.html";

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

        //1. get all parameters
        String username = request.getParameter("pk");
        String searchValue = request.getParameter("lastSearchValue");
        String url = siteMaps.getProperty(ApplicationConstants.DeleteAccountFeature.ERROR_PAGE);
        try {
            //2. Call model
            //2.1 New DAO
            RegistrationDAO dao = new RegistrationDAO();
            //2.2 Call method of DAO
            boolean result = dao.deleteAccount(username);
            //3. process result
            if (result) {
                //call the previous features again using URL rewriting technique
                url = "DispatchServlet"
                        + "?btnAction=Search"
                        + "&txtSearchValue=" + searchValue;
            } //end delete action is success     
        } catch (SQLException ex) {
            log("DeleteAccountServlet _ SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            log("DeleteAccountServlet _ Naming: " + ex.getMessage());
        } finally {
            //forward has issues because btnAction has duplicate
            response.sendRedirect(url);
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
