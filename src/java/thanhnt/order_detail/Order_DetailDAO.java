/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thanhnt.order_detail;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.NamingException;
import thanhnt.util.DBHelper;

/**
 *
 * @author thinkpad
 */
public class Order_DetailDAO implements Serializable {

    public boolean createOrderDetail(Order_DetailDTO od)
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
                String sql = "Insert Into Order_Detail("
                        + "product_id, unitPrice, quantity, total, order_id"
                        + ") Values("
                        + "?, ?, ?, ?, ?"
                        + ")";
                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, od.getProduct_id());
                stm.setDouble(2, od.getUnitPrice());
                stm.setInt(3, od.getQuantity());
                stm.setDouble(4, od.getTotal());
                stm.setString(5, od.getOrder_id());

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
}
