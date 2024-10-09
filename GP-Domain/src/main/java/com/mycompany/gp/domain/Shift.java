/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gp.domain;

import java.time.LocalDateTime;

/**
 *
 * @author Raul
 */
public class Shift {
    
    // Atributes
    
    private Long id;
    private LocalDateTime initialDate;
    private LocalDateTime finalDate;
    private float initialCash;
    private float finalCash;
    private boolean isActive;
    
    
    // Constructor

    public Shift() {
    }

    public Shift(Long id, LocalDateTime initialDate, LocalDateTime finalDate, float initialCash, float finalCash, boolean isActive) {
        this.id = id;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
        this.initialCash = initialCash;
        this.finalCash = finalCash;
        this.isActive = isActive;
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
