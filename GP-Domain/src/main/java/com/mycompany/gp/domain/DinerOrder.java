/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gp.domain;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 *
 * @author waw
 */

@Entity
public class DinerOrder extends Order{
    
    // Atributes
        @Column (name = "orderName")
        private String orderName; 
    
        
    // Constructors

    public DinerOrder() {
    }

    public DinerOrder(String orderName, LocalDateTime creationDate, ORDER_STATE order, List<Object> products, Float price,  Employee cashier) {
        super(creationDate, order, products, price, cashier);
        this.orderName = orderName;
    }
    
    

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

}
