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
    
    
    
    // Delivery Order
    
    /*
        The delivery order is an order that is created to be sent to an address, is important to 
        check the type of the content of the arguments that are given with the object, also is 
        important to check 
    */
}
