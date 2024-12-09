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
@Table(name = "ProductOrder")
public class ProductOrder implements Serializable {

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

    @Column(name = "price")
    private float price;
    
    @Column(name = "details")
    private String details;

    @Column (name = "PRODUCT_SIZE")
    protected PRODUCT_SIZE PRODUCT_SIZE;

    public ProductOrder() {
    }


    public ProductOrder(Product product, Order order, float price, String details, PRODUCT_SIZE PRODUCT_SIZE) {
        this.product = product;
        this.order = order;
        this.price = price;
        this.details = details;
        this.PRODUCT_SIZE = PRODUCT_SIZE;
    }
    
    

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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



    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public PRODUCT_SIZE getPRODUCT_SIZE() {
        return PRODUCT_SIZE;
    }

    public void setPRODUCT_SIZE(PRODUCT_SIZE PRODUCT_SIZE) {
        this.PRODUCT_SIZE = PRODUCT_SIZE;
    }

    
    @Override
    public String toString() {
        return "ProductOrder{" + "id=" + id + ", product=" + product + ", order=" + order + ", price=" + price;
    }

}
