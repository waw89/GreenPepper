/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gp.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 *
 * @author PC
 */
@Entity
@Table (name = "Discount")
public class Discount implements Serializable {
    
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
    
    @Basic
    @Column (name = "Name")
    private String name;
    
    @Basic
    @Column (name = "Percentage")
    private float percentage;
    
    @Basic
    @Column (name = "InitialDate")
    private LocalDateTime initialDate;
    
    @Basic
    @Column (name = "FinalDate")
    private LocalDateTime finalDate;

    
    // Constructors
    public Discount() {
    }

    public Discount(long id, String name, float percentage, LocalDateTime initialDate, LocalDateTime finalDate) {
        this.id = id;
        this.name = name;
        this.percentage = percentage;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
    }

    
    // Getters & Setters
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

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public LocalDateTime getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(LocalDateTime initialDate) {
        this.initialDate = initialDate;
    }

    public LocalDateTime getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(LocalDateTime finalDate) {
        this.finalDate = finalDate;
    }
    
}
