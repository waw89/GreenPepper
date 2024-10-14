/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business;

import com.mycompany.gp.domain.DinerOrder;
import com.mycompany.gp.domain.Order;
import com.mycompany.gp.domain.PickUpOrder;
import com.mycompany.gp.domain.Product;
import com.mycompany.gp.domain.ProductOrder;
import dao.DinerOrderDAO;
import dao.IDinerOrderDAO;
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
    IDinerOrderDAO dinerdao = new DinerOrderDAO();

    public PickUpOrder createPickUpOrder(PickUpOrder pickUpOrder) {
        float total = calculateCost(pickUpOrder);

        pickUpOrder.setPrice(total);

        return pudao.create(pickUpOrder);
    }
    
    public DinerOrder createDinerOrder(DinerOrder dinerOrder){
        float total = calculateCost(dinerOrder);
        dinerOrder.setPrice(total);
        
        return dinerdao.create(dinerOrder);
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
