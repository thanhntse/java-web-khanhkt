/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thanhnt.order_detail;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thinkpad
 */
public class Order_DetailCreateError implements Serializable {

    private List<String> notEnoughQuantity;

    public Order_DetailCreateError() {
        notEnoughQuantity = new ArrayList<>();
    }

    /**
     * @return the notEnoughQuantity
     */
    public List<String> getNotEnoughQuantity() {
        return notEnoughQuantity;
    }

    /**
     * @param notEnoughQuantity the notEnoughQuantity to set
     */
    public void setNotEnoughQuantity(List<String> notEnoughQuantity) {
        this.notEnoughQuantity = notEnoughQuantity;
    }

    public void appendNotEnoughQuantity(String notEnoughQuantity) {
        this.notEnoughQuantity.add(notEnoughQuantity);
    }

}
