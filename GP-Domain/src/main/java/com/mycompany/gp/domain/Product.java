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
    
    @Column (name = "state")
    protected boolean state;
  
    @ManyToOne
    @JoinColumn(name = "activeOffer")
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
