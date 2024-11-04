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

    public Combo(List<ComboProduct> productList, String name, int price, boolean productState, Discount activeOffer, com.mycompany.gp.domain.PRODUCT_SIZE PRODUCT_SIZE) {
        super(name, price, productState, activeOffer, PRODUCT_SIZE);
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

    public boolean isStateProduct() {
        return productState;
    }

    public void setStateProduct(boolean stateProduct) {
        this.productState = stateProduct;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
    
}
