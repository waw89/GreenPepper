/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gp.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 *
 * @author waw
 */

@Entity
public class PickUpOrder extends Order implements Serializable{
    
    // Atributes
    @Column (name = "customerName")
    private String customerName;
    
    // Constructor

    public PickUpOrder(LocalDateTime creationDate, ORDER_STATE ORDER_STATE, List<ProductOrder> products, Float price, Employee cashier) {
        super(creationDate, ORDER_STATE, products, price, cashier);
        this.customerName = customerName;
    }

   
    // Getters & Setters
 
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
    
    
}
