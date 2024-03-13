/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thanhnt.tbl_order;

import java.io.Serializable;
import java.util.Date;
import thanhnt.util.Tools;

/**
 *
 * @author thinkpad
 */
public class Tbl_OrderDTO implements Serializable {

    private String id;
    private String date;
    private double total;

    public Tbl_OrderDTO() {
    }

    public Tbl_OrderDTO(int lastestId) {
        this.id = Tools.generateCode("HD", 3, ++lastestId);
        this.date = Tools.getCurrentDate();
    }

    public Tbl_OrderDTO(int lastestId, double total) {
        this.id = Tools.generateCode("HD", 3, ++lastestId);
        this.date = Tools.getCurrentDate();
        this.total = total;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the total
     */
    public double getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(double total) {
        this.total = total;
    }

}
