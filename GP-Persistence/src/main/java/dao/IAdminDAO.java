/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import com.mycompany.gp.domain.Admin;

/**
 *
 * @author Raul
 */
public interface IAdminDAO {
    
    public Admin create(Admin admin);
    public Admin edit(Admin admin);
    public void delete(Admin admin);
    
}
