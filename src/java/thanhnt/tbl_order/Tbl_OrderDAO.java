/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thanhnt.tbl_order;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import thanhnt.util.DBHelper;
import thanhnt.util.Tools;

/**
 *
 * @author thinkpad
 */
public class Tbl_OrderDAO implements Serializable {

    public boolean createOrder(Tbl_OrderDTO order)
            throws SQLException, /*ClassNotFoundException,*/ NamingException {
        Connection con = null;
        PreparedStatement stm = null;
//        boolean result = false; //output co 1 dau ra, su dung bien boolean
        boolean result = false;

        try {
            //1. get connection
            con = DBHelper.getConnection();
            if (con != null) {
                //2. create SQL string
                String sql = "Insert Into tbl_Order("
                        + "id, date, total"
                        + ") Values("
                        + "?, ?, ?"
                        + ")";
                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, order.getId());
                stm.setString(2, order.getDate());
                stm.setDouble(3, order.getTotal());

                //4. execute query
                int effectRows = stm.executeUpdate();
                //5. Process result
                if (effectRows > 0) {
                    result = true;
                }
            } //end connection has been available
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    public boolean updateTotalPrice(String id, double total)
            throws SQLException, /*ClassNotFoundException,*/ NamingException {
        Connection con = null;
        PreparedStatement stm = null;
//        boolean result = false; //output co 1 dau ra, su dung bien boolean
        boolean result = false;

        try {
            //1. get connection
            con = DBHelper.getConnection();
            if (con != null) {
                //2. create SQL string
                String sql = "Update tbl_Order "
                        + "Set total = " + total + " "
                        + "Where id = ?";
                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, id);
                //4. execute query
                int effectRows = stm.executeUpdate();
                //5. Process result
                if (effectRows > 0) {
                    result = true;
                }
            } //end connection has been available
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    public int getLastestId()
            throws SQLException, /*ClassNotFoundException,*/ NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
//        boolean result = false; //output co 1 dau ra, su dung bien boolean
        int result = 0;

        try {
            //1. get connection
            con = DBHelper.getConnection();
            if (con != null) {
                //2. create SQL string
                String sql = "SELECT TOP 1 id "
                        + "FROM tbl_Order "
                        + "ORDER BY id DESC";
                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                //4. execute query
                rs = stm.executeQuery();
                //5. Process result
                if (rs.next()) {
                    String id = rs.getString("id");
                    result = Tools.getNumberInCode(id, "HD");
                }
            } //end connection has been available
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }
}
