/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import com.mycompany.gp.domain.DeliveryOrder;

/**
 *
 * @author Raul
 */
public interface IDeliveryDAO {
    public DeliveryOrder create(DeliveryOrder orderToPersist);
}
