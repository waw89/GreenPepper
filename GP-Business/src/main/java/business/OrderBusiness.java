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
import dao.ProductOrderDAO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    ProductOrderDAO podao = new ProductOrderDAO();
    
    public PickUpOrder createPickUpOrder(PickUpOrder pickUpOrder) {
        float total = calculateCost(pickUpOrder);

        pickUpOrder.setPrice(total);

        return pudao.create(pickUpOrder);
    }

    public DeliveryOrder createDeliveryOrder(DeliveryOrder deliveryOrder) {
        return deliveryDAOInterface.create(deliveryOrder);
    }

    public DinerOrder createDinerOrder(DinerOrder dinerOrder) {
        float total = calculateCost(dinerOrder);
        dinerOrder.setPrice(total);

        return dinerdao.create(dinerOrder);
    }

    public Order editOrder(Order order) throws Exception {
        float total = calculateCost(order);
        order.setPrice(total);
        return odao.edit(order);
    }

    public Order cancelOrder(Order order) throws Exception {
        order.setOrderState(ORDER_STATE.CANCELLED);
        return odao.edit(order);
    }

    public List<Product> getAllProducts() {
        return prodDao.findProductEntities();
    }
    
    public List<DinerOrder> getAllDinOrder(){
        return dinerdao.findDinerOrderEntities();
    }
    
    public List<DeliveryOrder> getAllDelOrder(){
        return deliveryDAOInterface.findDeliveryOrderEntities();
    }
    
    public List<PickUpOrder> getAllPickUpOrder(){
        return pudao.findPickUpOrderEntities();
    }
    
    public List<Order> getAllOrder(){
        return odao.findOrderEntities();
    }
    
    public List<ProductOrder> getAllProductOrder(){
        return podao.findProductOrderEntities();
    }
    
    public float calculateCost(Order order) {
        float total = 0;
        List<ProductOrder> products = order.getProducts();
        for (ProductOrder product : products) {
            total = total + product.getPrice();
        }
        return total;
    }

    public List<Order> getActiveOrders() {
        return odao.findActiveOrders();
    }

    public List<Order> getCanceledPaidOrders() {
        return odao.findCanceledPaidOrders();
    }
    
    public Order findOrderById(Long orderNumber){
        return odao.findOrder(orderNumber);
    }
    
    public DinerOrder EditDataDiner(DinerOrder dinerOrder){
        try {
            return dinerdao.edit(dinerOrder);
        } catch (Exception ex) {
            Logger.getLogger(OrderBusiness.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dinerOrder;
    }
    
    public DeliveryOrder EditDataDelivery(DeliveryOrder deliveryOrder){
        try{
            return deliveryDAOInterface.edit(deliveryOrder);
        } catch (Exception ex) {
            Logger.getLogger(OrderBusiness.class.getName()).log(Level.SEVERE, null, ex);
        }
        return deliveryOrder;
    }
    
    public PickUpOrder EditDataPickUp(PickUpOrder pickUpOrder){
        try{
            return pudao.edit(pickUpOrder);
        } catch (Exception ex) {
            Logger.getLogger(OrderBusiness.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pickUpOrder;
    }
      
}
