/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gp.domain;

/**
 *
 * @author PC
 */
public class IndividualProduct {
    private PRODUCT_TYPE type;

    public IndividualProduct() {
    }

    public IndividualProduct(PRODUCT_TYPE type) {
        this.type = type;
    }

    public PRODUCT_TYPE getType() {
        return type;
    }

    public void setType(PRODUCT_TYPE type) {
        this.type = type;
    }
    
}
