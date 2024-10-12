/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gp.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Raul
 */

@Entity
@Table(name="ProductOrder")
public class ProductOrder  implements Serializable{
    
    // Atributes
   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "productId", referencedColumnName = "productId")
    private Product product;
    
    @ManyToOne
    @JoinColumn(name = "orderNumber", referencedColumnName = "orderNumber")
    private Order order;
    
    @Column(name="price")
    private float price;
    
    @Column(name="amount")
    private int amount;
    
    @Column(name="details")
    private String details;

    
    public ProductOrder() {
    }
   
    public ProductOrder(Long id, Product product, Order order, float price, int amount, String details) {
        this.id = id;
        this.product = product;
        this.order = order;
        this.price = price;
        this.amount = amount;
        this.details = details;
    }
    
    
    // Getters & Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProductId() {
        return product;
    }

    public void setProductId(Product product) {
        this.product = product;
    }

    public Order getOrderNumber() {
        return order;
    }

    public void setOrderNumber(Order order) {
        this.order = order;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
    
    
    
}
