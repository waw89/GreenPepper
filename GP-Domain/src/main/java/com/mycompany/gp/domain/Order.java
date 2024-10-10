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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 *
 * @author waw
 */
@Entity
@Table(name="order")
@Inheritance(strategy = InheritanceType.JOINED)
public class Order implements Serializable{
    
    // Atributes
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    protected Long orderNumber; 
    
    @Column (name = "creationDate")
    protected LocalDateTime creationDate;
    
    @Column (name = "ORDER_STATE")
    protected ORDER_STATE ORDER_STATE ;
    
  
    // Relation with products
    protected List<Object> products;
    
    @Column (name = "price")
    protected Float price;
    
    // Relation with Employee
    protected Employee cashier;

    
    // Constructors
    
    
    public Order() {
    }

    public Order(Long orderNumber, LocalDateTime creationDate, ORDER_STATE ORDER_STATE, List<Object> products, Float price, Employee cashier) {
        this.orderNumber = orderNumber;
        this.creationDate = creationDate;
        this.ORDER_STATE = ORDER_STATE;
        this.products = products;
        this.price = price;
        this.cashier = cashier;
    }

    
    public Order(LocalDateTime creationDate, ORDER_STATE ORDER_STATE, List<Object> products, Float price, Employee cashier) {
        this.creationDate = creationDate;
        this.ORDER_STATE = ORDER_STATE;
        this.products = products;
        this.price = price;
        this.cashier = cashier;
    }
    
    
    // Getters & Setters

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

    public ORDER_STATE getOrderState() {
        return ORDER_STATE;
    }

    public void setOrderState(ORDER_STATE ORDER_STATE) {
        this.ORDER_STATE = ORDER_STATE;
    }

    public List<Object> getProducts() {
        return products;
    }

    public void setProducts(List<Object> products) {
        this.products = products;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Object getCashier() {
        return cashier;
    }

    public void setCashier(Employee cashier) {
        this.cashier = cashier;
    }
        
}
