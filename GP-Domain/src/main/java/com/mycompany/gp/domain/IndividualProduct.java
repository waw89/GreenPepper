/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gp.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author PC
 */

@Entity
@PrimaryKeyJoinColumn (name = "IdIndProduct")
@DiscriminatorValue (value = "IndividualProduct")
@Table (name = "IndividualProduct")
public class IndividualProduct extends Product implements Serializable {
    
    @Enumerated
    @Column(name = "Type")
    private PRODUCT_TYPE type;

    public IndividualProduct() {
    }

    public IndividualProduct(PRODUCT_TYPE type,String name, boolean state, Discount activeOffer) {
        super(name, state, activeOffer);
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
