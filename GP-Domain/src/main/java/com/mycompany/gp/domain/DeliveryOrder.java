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
public class DeliveryOrder extends Order {

    // Atributes
    @Column(name = "customerName")
    private String customerName;
    @Column(name = "address")
    private String address;
    @Column(name = "phoneNumber")
    private String phoneNumber;

    // Constructors
    public DeliveryOrder() {

    }

    public DeliveryOrder(String customerName, String address, String phoneNumber, LocalDateTime creationDate, ORDER_STATE order, List<Object> products, Float price, Employee employee) {
        super(creationDate, order, products, price, employee);
        this.customerName = customerName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    // Getters y setters
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

}
