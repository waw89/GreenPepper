/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gp.domain;

import java.time.LocalDateTime;


/**
 *
 * @author PC
 */
public class Discount {
    private long id;
    private String name;
    private float percentage;
    private LocalDateTime initialDate;
    private LocalDateTime finalDate;

    public Discount() {
    }

    public Discount(long id, String name, float percentage, LocalDateTime initialDate, LocalDateTime finalDate) {
        this.id = id;
        this.name = name;
        this.percentage = percentage;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
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
