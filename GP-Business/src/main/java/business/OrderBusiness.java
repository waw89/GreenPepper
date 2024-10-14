/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business;

import com.mycompany.gp.domain.DeliveryOrder;
import com.mycompany.gp.domain.Order;
import com.mycompany.gp.domain.PickUpOrder;
import com.mycompany.gp.domain.Product;
import com.mycompany.gp.domain.ProductOrder;
import dao.DeliveryOrderDAO;
import dao.IDeliveryDAO;
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
    IDeliveryDAO deliveryDAOInterface = new DeliveryOrderDAO(); 
    
    public PickUpOrder createPickUpOrder(PickUpOrder pickUpOrder) {
        float total = calculateCost(pickUpOrder);

        pickUpOrder.setPrice(total);

        return pudao.create(pickUpOrder);
    }
    
    
    public DeliveryOrder createDeliveryOrder(DeliveryOrder deliveryOrder){
        return deliveryDAOInterface.create(deliveryOrder); 
    }

    public List<Product> getAllProducts() {
        return prodDao.findProductEntities();
    }

    public float calculateCost(Order order) {
        float total = 0;
        List<ProductOrder> products = order.getProducts();;

        for (ProductOrder product : products) {
            if (product.getAmount() >= 2) {
                float totalPerProduct = product.getAmount() * product.getPrice();
                total = total + totalPerProduct;
            } else {
                total = total + product.getPrice();
            }

        }

        return total;
    }
}
