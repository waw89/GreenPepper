/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core;

import model.OrderModel;

/**
 *
 * @author waw
 */
public class ModelFactory {
    
    private OrderModel orderModel;
    
    public OrderModel getOrderModel(){
        if (this.orderModel == null){
            this.orderModel = new OrderModel();
        }
        
        return orderModel; 
    }
}
