/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thanhnt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import thanhnt.registration.RegistrationDAO;

/**
 *
 * @author thinkpad
 */
public class UpdateAccountServlet extends HttpServlet {
    private final String ERRORS_PAGE = "errors.html";
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
        //1. get all parameters
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        boolean isAdmin = false;
        if ("ON".equals(request.getParameter("chkAdmin"))) {
            isAdmin = true;
        }
        String searchValue= request.getParameter("lastSearchValue");
        
        String url = ERRORS_PAGE;
        try {
               //2. Call model
               //2.1 New DAO
               RegistrationDAO dao = new RegistrationDAO();
               //2.2 Call method of DAO
               boolean result = dao.updateAccount(username, password, isAdmin);
               //3. process result
               if (result) {
                   //call the previous features again using URL rewriting technique
                   url = "DispatchServlet"
                           + "?btnAction=Search"
                           + "&txtSearchValue=" + searchValue;
               } //end delete action is success     
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (NamingException ex) {
            ex.printStackTrace();
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
