/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gp.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


/**
 *
 * @author PC
 */

@Entity
@PrimaryKeyJoinColumn (name = "IdCombo")
@DiscriminatorValue (value = "Combo")
@Table (name = "Combo")
public class Combo extends Product implements Serializable {
    
    @OneToMany(mappedBy = "combo")
    private List<ComboProduct> productList;

    public Combo() {
    }

    public Combo(List<ComboProduct> productList) {
        this.productList = productList;
    }

    public Combo(List<ComboProduct> productList, String name, boolean state, Discount activeOffer) {
        super(name, state, activeOffer);
        this.productList = productList;
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

    public List<ComboProduct> getProductList() {
        return productList;
    }

    public void setProductList(List<ComboProduct> productList) {
        this.productList = productList;
    }
    
    
}
