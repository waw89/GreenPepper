/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gp.gui;

import business.OrderBusiness;
import com.mycompany.gp.domain.Product;
import java.util.List;

/**
 *
 * @author waw
 */
public class MainOrder {

    OrderBusiness ob = new OrderBusiness();

    List<Product> products;

    public void method() {
        this.products = ob.getAllProducts();

        for (Product prod : products) {
            System.out.println(prod.toString());
        }
    }

    public static void main(String[] args) {
        MainOrder mo = new MainOrder(); 
        
        mo.method();
    }

}
