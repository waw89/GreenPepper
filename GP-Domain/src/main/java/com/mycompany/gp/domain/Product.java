/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gp.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
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
    protected long id;
    
    @Column (name = "Name")
    protected String name;
    
    @Column (name = "State")
    protected boolean state;
    
    
    //Relacion con la tabla Discount
    protected Discount activeOffer;

    public Product() {
    }

    public Product(String name, boolean state, Discount activeOffer) {
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
