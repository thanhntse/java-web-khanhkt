/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thanhnt.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import thanhnt.registration.RegistrationDAO;
import thanhnt.registration.RegistrationDTO;
import thanhnt.util.ApplicationConstants;

/**
 *
 * @author thinkpad
 */
public class SearchLatstnameServlet extends HttpServlet {
//    private final String SEARCH_PAGE = "searchBegin.jsp";
//    private final String RESULT_PAGE = "search.jsp";

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

        //1. get all client information
        String searchValue = request.getParameter("txtSearchValue");
//        String url = SEARCH_PAGE;
        String url = siteMaps.getProperty(ApplicationConstants.SearchLatstnameFeature.SEARCH_PAGE);

        try {
            if (!searchValue.trim().isEmpty()) {
                //2. Call model
                //2.1. new DAO
                RegistrationDAO dao = new RegistrationDAO();
                //2.2 call method of DAO
                dao.searchLastname(searchValue);
                List<RegistrationDTO> result = dao.getAccounts();
                //3. process Result
//                    url = RESULT_PAGE;
                request.setAttribute("SEARCH_RESULT", result);
            }//user typed valid value
        } catch (SQLException ex) {
            log("SearchLatstnameServlet _ SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            log("SearchLatstnameServlet _ Naming: " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
