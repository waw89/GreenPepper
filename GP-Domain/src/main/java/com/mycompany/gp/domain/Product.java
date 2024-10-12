/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gp.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author PC
 */

@Entity
@Inheritance (strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "Type")
@Table (name = "Product")
public class Product implements Serializable {
    
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "productId")
    protected long id;
    
    @Column (name = "name")
    protected String name;
    
    @Column (name = "price")
    protected int price;
    
    @Column (name = "stateProduct")
    protected boolean stateProduct;
  
    @ManyToOne
    @JoinColumn(name = "activeOffer")
    protected Discount activeOffer;
    

    public Product() {
    }

    public Product(String name, int price, boolean state, Discount activeOffer) {
        this.name = name;
        this.price = price;
        this.stateProduct = state;
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
        return stateProduct;
    }

    public void setState(boolean state) {
        this.stateProduct = state;
    }

    public Discount getActiveOffer() {
        return activeOffer;
    }

    public void setActiveOffer(Discount activeOffer) {
        this.activeOffer = activeOffer;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
}
