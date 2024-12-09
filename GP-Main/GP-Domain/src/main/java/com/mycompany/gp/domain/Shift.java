/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gp.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Raul
 */
@Entity
@Table(name= "shift")
public class Shift implements Serializable{
    
    // Atributes
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="initialDate", nullable = false)
    private LocalDateTime initialDate;
    @Column(name="finalDate", nullable = false)
    private LocalDateTime finalDate;
    @Column(name="initialCash", nullable = false)
    private float initialCash;
    @Column(name="finalCash", nullable = false)
    private float finalCash;
    @Column(name="isActive", nullable = false)
    private boolean isActive;
    @OneToOne
    @JoinColumn(name="user_id", nullable=false)
    private Admin admin;
    
    // Constructor

    public Shift() {
    }

    public Shift(Long id, LocalDateTime initialDate, LocalDateTime finalDate, float initialCash, float finalCash, boolean isActive, Admin admin) {
        this.id = id;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
        this.initialCash = initialCash;
        this.finalCash = finalCash;
        this.isActive = isActive;
        this.admin = admin;
    }
    
    
    // Getters & Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public float getInitialCash() {
        return initialCash;
    }

    public void setInitialCash(float initialCash) {
        this.initialCash = initialCash;
    }

    public float getFinalCash() {
        return finalCash;
    }

    public void setFinalCash(float finalCash) {
        this.finalCash = finalCash;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
  
}
