/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gp.domain;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;



/**
 *
 * @author Raul
 */

@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends User implements Serializable{
    
    @OneToOne(mappedBy = "admin")
    private Shift shift;
    // Constructors

    public Admin() {
    }

    public Admin(Long id, String username, String password, String name) {
        super(id, username, password, name);
    }

    public Admin(String username, String password, String name) {
        super(username, password, name);
    }
    
    

    
    // Getters & Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
  
    
   

    
    
    
    
    
    
    
}
