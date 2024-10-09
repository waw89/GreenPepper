/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gp.domain;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Entity;

/**
 *
 * @author waw
 */

@Entity
public class DeliveryOrder extends Order {
    
    // Atributes
    private String customerName;
    private String address; 
    private String phoneNumber;
   
    
    // Constructors

    public DeliveryOrder() {
    
    }

    public DeliveryOrder(String customerName, String address, String phoneNumber, Long orderNumber, LocalDateTime creationDate, ORDER_STATE order, Product product, List<Object> products, Float price, Employee employee, Object cashier) {
        super(orderNumber, creationDate, order, product, products, price, employee, cashier);
        this.customerName = customerName;
        this.address = address;
        this.phoneNumber = phoneNumber;
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

    public ORDER_STATE getOrder() {
        return order;
    }

    public void setOrder(ORDER_STATE order) {
        this.order = order;
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

    public void setCashier(Object cashier) {
        this.cashier = cashier;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    
    
    
    
    
    
    
}
