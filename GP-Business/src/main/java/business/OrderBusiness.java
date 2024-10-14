/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business;


import com.mycompany.gp.domain.DeliveryOrder;
import com.mycompany.gp.domain.ORDER_STATE;
import com.mycompany.gp.domain.DinerOrder;
import com.mycompany.gp.domain.Order;
import com.mycompany.gp.domain.PickUpOrder;
import com.mycompany.gp.domain.Product;
import com.mycompany.gp.domain.ProductOrder;
import dao.DeliveryOrderDAO;
import dao.IDeliveryDAO;
import dao.DinerOrderDAO;
import dao.IDinerOrderDAO;
import dao.IOrderDAO;
import dao.IPickUpOrderDAO;
import dao.IProductDAO;
import dao.OrderDAO;
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
    OrderDAO odao = new OrderDAO();
    IDinerOrderDAO dinerdao = new DinerOrderDAO();
    
  public PickUpOrder createPickUpOrder(PickUpOrder pickUpOrder) {
        float total = calculateCost(pickUpOrder);

        pickUpOrder.setPrice(total);

        return pudao.create(pickUpOrder);
    }
        
    public DeliveryOrder createDeliveryOrder(DeliveryOrder deliveryOrder){
        return deliveryDAOInterface.create(deliveryOrder); 
      
    public DinerOrder createDinerOrder(DinerOrder dinerOrder){
        float total = calculateCost(dinerOrder);
        dinerOrder.setPrice(total);
        
        return dinerdao.create(dinerOrder);
    }

    public Order editOrder(Order order) throws Exception{
        float total = calculateCost(order);
        order.setPrice(total);
        return odao.edit(order);
    }
    
     public Order cancelOrder(Order order) throws Exception{
        order.setOrderState(ORDER_STATE.CANCELLED);
        return odao.edit(order);
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
    
   
    public List<Order>getActiveOrders(){
        return odao.findActiveOrders();
    }
    
    public List<Order>getCanceledPaidOrders(){
        return odao.findCanceledPaidOrders();
    }
}
