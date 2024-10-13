/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business;

import com.mycompany.gp.domain.PickUpOrder;
import dao.IPickUpOrderDAO;
import dao.PickUpOrderDAO;

/**
 *
 * @author Raul
 */
public class OrderBusiness {
    IPickUpOrderDAO pudao = new PickUpOrderDAO(); 
    
    public PickUpOrder createPickUpOrder(PickUpOrder pickUpOrder){
        return pudao.create(pickUpOrder);
    }
}
