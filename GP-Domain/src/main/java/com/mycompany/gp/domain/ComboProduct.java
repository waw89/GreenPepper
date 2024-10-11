/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Raul
 */
@Entity
@Table(name = "ComboProduct")
public class ComboProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "IdCombo", referencedColumnName = "productId") 
    private Combo combo;

    @ManyToOne
    @JoinColumn(name = "IdIndProduct", referencedColumnName = "productId") 
    private IndividualProduct product;

    @Column(name = "amount")
    private int amount;

    public ComboProduct(Long id, Combo combo, IndividualProduct product, int amount) {
        this.id = id;
        this.combo = combo;
        this.product = product;
        this.amount = amount;
    }

}
