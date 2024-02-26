/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thanhnt.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author thinkpad
 */
public class CartObject implements Serializable {

    private Map<String, Integer> items;

    public Map<String, Integer> getItems() {
        return items;
    }

    public boolean addItemToCart(String id) {
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
            this.items = new HashMap<>();
        }
        //3. check existed item -> existed --> inscrease quantity, otherwise, drop item
        int quantity = 1;
        if (this.items.containsKey(id)) {
            quantity = this.items.get(id) + 1;
        }//item has existed in items
        //4. Update items
        this.items.put(id, quantity);
        result = true;
        return result;
    }

    public boolean removeItemFromCart(String id) {
        boolean result = false;
        //1. check existed items
        if (this.items != null) {
            //2. check exised item -> existed --> remove
            if (this.items.containsKey(id)) {
                this.items.remove(id);
                if (this.items.isEmpty()) {
                    this.items = null;
                }
                result = true;
            }//end item has existed
        }// end items have existed
        return result;
    }
}
