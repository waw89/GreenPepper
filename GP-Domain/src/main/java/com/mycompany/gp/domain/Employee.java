/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gp.domain;

/**
 *
 * @author Raul
 */
public class Employee extends User{
    
    
    // Constructors

    public Employee() {
    }

    public Employee(Long id, String username, String password, String name) {
        super(id, username, password, name);
    }
    
    
}
