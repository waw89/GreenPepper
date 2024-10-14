/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gp.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author waw
 */

@Entity
@DiscriminatorValue (value = "PickUp")
public class PickUpOrder extends Order implements Serializable{
    
    // Atributes
    @Column (name = "customerName")
    private String customerName;
    
    @Column (name = "customerPhone")
    private String customerPhone;
    
    // Constructor

    public PickUpOrder() {
    }

    public PickUpOrder(String customerName, String customerPhone, LocalDateTime creationDate, com.mycompany.gp.domain.ORDER_STATE ORDER_STATE, List<ProductOrder> products, Float price, String details, Employee cashier) {
        super(creationDate, ORDER_STATE, products, price, details, cashier);
        this.customerName = customerName;
        this.customerPhone = customerPhone;
    }

   
    // Getters & Setters
 
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public com.mycompany.gp.domain.ORDER_STATE getORDER_STATE() {
        return ORDER_STATE;
    }

    public void setORDER_STATE(com.mycompany.gp.domain.ORDER_STATE ORDER_STATE) {
        this.ORDER_STATE = ORDER_STATE;
    }

    public List<ProductOrder> getProducts() {
        return products;
    }

    public void setProducts(List<ProductOrder> products) {
        this.products = products;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Employee getCashier() {
        return cashier;
    }

    public void setCashier(Employee cashier) {
        this.cashier = cashier;
    }
    
    
    
}
