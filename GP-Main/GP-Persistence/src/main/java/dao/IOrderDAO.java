/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import com.mycompany.gp.domain.Order;
import dao.exceptions.NonexistentEntityException;

/**
 *
 * @author Raul
 */
public interface IOrderDAO {
    public Order create(Order order);
    public Order edit(Order order) throws NonexistentEntityException, Exception;
    public Order findOrderByName();
    
}
