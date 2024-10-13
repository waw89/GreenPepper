/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import com.mycompany.gp.domain.PickUpOrder;
import dao.exceptions.NonexistentEntityException;

/**
 *
 * @author Raul
 */
public interface IPickUpOrderDAO {
    public PickUpOrder create(PickUpOrder pickUpOrder);
    public PickUpOrder edit(PickUpOrder pickUpOrder)throws NonexistentEntityException, Exception ;
    
}
