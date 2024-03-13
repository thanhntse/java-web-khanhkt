/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thanhnt.cart;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import thanhnt.product.ProductDAO;
import thanhnt.product.ProductDTO;

/**
 *
 * @author thinkpad
 */
public class CartObject implements Serializable {

//    private Map<String, Integer> items;
    private List<ProductDTO> items;

//    public Map<String, Integer> getItems() {
//        return items;
//    }
    public List<ProductDTO> getItems() {
        return items;
    }

    public boolean addItemToCart(String id)
            throws SQLException, NamingException {
        boolean result = false;
        //1. check existed id
        if (id == null) {
            return result;
        }
        if (id.trim().isEmpty()) {
            return result;
        }
        //2. check existed items
        if (this.items == null) {
//            this.items = new HashMap<>();
            this.items = new ArrayList<>();
        }
        //3. check existed item -> existed --> inscrease quantity, otherwise, drop item
        boolean hasExisted = false;
//        if (this.items.containsKey(id)) {
//            quantity = this.items.get(id) + 1;
//        }//item has existed in items
        for (ProductDTO product : this.items) {
            if (product.getSku().equals(id)) {
                product.setQuantity(product.getQuantity() + 1);
                hasExisted = true;
            } //item has existed in items
        }
        //item not existed in items --> drop item by id (sku)
        if (!hasExisted) {
            ProductDAO dao = new ProductDAO();
            dao.searchAllProduct();
            List<ProductDTO> productList = dao.getProducts();
        
            for (ProductDTO product : productList) {
                if (product.getSku().equals(id)) {
                    product.setQuantity(1);
                    this.items.add(product);
                }
            }
        }
        //4. Update items
//        this.items.put(id, quantity);
        result = true;
        return result;
    }

    public boolean removeItemFromCart(String id) {
        boolean result = false;
        //1. check existed items
        if (this.items != null) {
            //2. check exised item -> existed --> remove
//            if (this.items.containsKey(id)) {
//                this.items.remove(id);
//                if (this.items.isEmpty()) {
//                    this.items = null;
//                }
//                result = true;
//            }//end item has existed
            int removePosition = -1;
            for (ProductDTO product : this.items) {
                if (product.getSku().equals(id)) {
                    removePosition = this.items.indexOf(product);
                    break;
                } //item has existed in items
            }
            if (removePosition >= 0) {
                this.items.remove(removePosition);
                if (this.items.isEmpty()) {
                    this.items = null;
                }
                result = true;
            }
        }// end items have existed
        return result;
    }
}
