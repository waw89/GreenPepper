/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gp.domain;

import javax.persistence.Entity;

/**
 *
 * @author PC
 */

@Entity
public class IndividualProduct extends Product {
    private PRODUCT_TYPE type;

    public IndividualProduct() {
    }

    public IndividualProduct(PRODUCT_TYPE type, long id, String name, boolean state, Discount activeOffer) {
        super(id, name, state, activeOffer);
        this.type = type;
    }

    public PRODUCT_TYPE getType() {
        return type;
    }

    public void setType(PRODUCT_TYPE type) {
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public Discount getActiveOffer() {
        return activeOffer;
    }

    public void setActiveOffer(Discount activeOffer) {
        this.activeOffer = activeOffer;
    }
}
