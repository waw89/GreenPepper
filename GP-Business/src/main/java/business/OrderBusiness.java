/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business;

import com.mycompany.gp.domain.PickUpOrder;
import com.mycompany.gp.domain.Product;
import dao.IOrderDAO;
import dao.IPickUpOrderDAO;
import dao.IProductDAO;
import dao.PickUpOrderDAO;
import dao.ProductDAO;
import java.util.List;

/**
 *
 * @author Raul
 */
public class OrderBusiness {
    IPickUpOrderDAO pudao = new PickUpOrderDAO(); 
    IProductDAO prodDao = new ProductDAO(); 
    public PickUpOrder createPickUpOrder(PickUpOrder pickUpOrder){
        return pudao.create(pickUpOrder);
    }
    
    
    
    
    public List<Product> getAllProducts(){
        return prodDao.findProductEntities(); 
    }
}
