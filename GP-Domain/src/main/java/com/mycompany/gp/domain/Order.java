/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gp.domain;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author waw
 */
public class Order {
    protected Long orderNumber; 
    protected LocalDateTime creationDate;
    protected ORDER_STATE order ;
    // pending: Change Object for Product class once Product class is ready
    protected List<Object> products;
    protected Float price;
    // pending: change Object for Employee Class once the Employee class is ready
    protected Object cashier;
    
}
