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
import javax.servlet.http.HttpSession;
import thanhnt.cart.CartObject;
import thanhnt.order_detail.Order_DetailCreateError;
import thanhnt.order_detail.Order_DetailDAO;
import thanhnt.order_detail.Order_DetailDTO;
import thanhnt.product.ProductDAO;
import thanhnt.product.ProductDTO;
import thanhnt.tbl_order.Tbl_OrderDAO;
import thanhnt.tbl_order.Tbl_OrderDTO;
import thanhnt.util.ApplicationConstants;

/**
 *
 * @author thinkpad
 */
public class CheckoutServlet extends HttpServlet {

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

        String url = siteMaps.getProperty(ApplicationConstants.CheckoutFeature.ERROR_PAGE);
        boolean foundErr = false;
        Order_DetailCreateError errors = new Order_DetailCreateError();

        try {
            //1. Cust goes to his/her cart
            HttpSession session = request.getSession(false);
            if (session != null) {
                //2. Cust takes his/her cart
                CartObject cart = (CartObject) session.getAttribute("CART");
                if (cart != null) {
                    //3. Cust gets items
//                    Map<String, Integer> items = cart.getItems();
                    List<ProductDTO> items = cart.getItems();
                    if (items != null) {

                        //Check quantity errors
                        for (ProductDTO item : items) {
                            ProductDAO productDao = new ProductDAO();
                            int maxQuantity = productDao.getQuantity(item.getSku());
                            if (item.getQuantity() > maxQuantity) {
                                errors.appendNotEnoughQuantity(item.getName()
                                        + " products do not have enough quantity,"
                                        + " we will take the largest quantity in stock");
                                item.setQuantity(maxQuantity);
                                foundErr = true;
                            }
                        }

                        if (foundErr) {
                            //catching to attribute and transfer to error page
                            request.setAttribute("ORDER_DETAIL_ERRORS", errors);
                            url = siteMaps.getProperty(ApplicationConstants.CheckoutFeature.VIEW_CART_PAGE);
                        } else {//no error --> insert
                            //4a. Create order
                            Tbl_OrderDAO orderDao = new Tbl_OrderDAO();
                            int lastestID = orderDao.getLastestId();
                            Tbl_OrderDTO orderDto = new Tbl_OrderDTO(lastestID);
                            orderDao.createOrder(orderDto);
                            double total = 0;
                            //4b. Forearch item --> insert to OrderDetail table
                            for (ProductDTO item : items) {
                                Order_DetailDAO itemDao = new Order_DetailDAO();

                                Order_DetailDTO itemDto = new Order_DetailDTO(
                                        item.getSku(), item.getUnitPrice(), item.getQuantity(),
                                        item.getQuantity() * item.getUnitPrice(), orderDto.getId());
                                boolean result = itemDao.createOrderDetail(itemDto);
                                if (result) {
                                    total += item.getQuantity() * item.getUnitPrice();
                                    ProductDAO productDao = new ProductDAO();
                                    productDao.updateQuantity(item.getSku(), item.getQuantity());
                                }
                            }
                            boolean result = orderDao.updateTotalPrice(orderDto.getId(), total);
                            if (result) {
                                //5. remove cart
                                session.removeAttribute("CART");
                                //6. Cust continue goes to shopping --> transfer to product page (if any)
                                request.setAttribute("TOTAL_PRICE", total);
                                url = siteMaps.getProperty(ApplicationConstants.CheckoutFeature.SUCCESS_PAGE);
                            }
                        }

                    }// items have existed
                }// cart has existed
            }// session has existed
        } catch (SQLException ex) {
            log("CheckoutServlet _ SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            log("CheckoutServlet _ Naming: " + ex.getMessage());
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
