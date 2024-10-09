/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gp.domain;

/**
 *
 * @author PC
 */
public class Product {
    protected long id;
    protected String name;
    protected boolean state;
    protected Discount activeOffer;

    public Product() {
    }

    public Product(long id, String name, boolean state, Discount activeOffer) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.activeOffer = activeOffer;
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
