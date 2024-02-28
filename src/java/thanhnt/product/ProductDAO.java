/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thanhnt.product;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import thanhnt.util.DBHelper;

/**
 *
 * @author thinkpad
 */
public class ProductDAO implements Serializable{
    private List<ProductDTO> products;

    public List<ProductDTO> getProducts() {
        return products;
    }
    
    public void searchAllProduct() 
            throws SQLException, /*ClassNotFoundException,*/ NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean result = false; //output co 1 dau ra, su dung bien boolean

        try {
            //1. get connection
            con = DBHelper.getConnection();
            if (con != null) {
                //2. create SQL string
                String sql = "Select sku, name, description, unitPrice, quantity, status "
                        + "From Product "
                        + "Where status = 1 and quantity > 0";
                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                //4. Execute query
                rs = stm.executeQuery();
                //5. Process result
                while (rs.next()) {
                    //5.1 get data from ResultSet
                    String sku = rs.getString("sku");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    double unitPrice = rs.getDouble("unitPrice");
                    boolean status = rs.getBoolean("status");
                    int quantity = rs.getInt("quantity");

                    //5.2 set data into DTO properties
                    ProductDTO dto = new ProductDTO(sku, name, description, 
                            unitPrice, quantity, status);
                    if (this.products == null) {
                        this.products = new ArrayList<>();
                    } //end accounts have NOT existed
                    this.products.add(dto);
                } // end account has existed
            } //end connection has been available
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
}
