/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business;

import com.mycompany.gp.domain.IndividualProduct;
import com.mycompany.gp.domain.PRODUCT_TYPE;
import com.mycompany.gp.domain.Product;
import dao.IndividualProductDAO;
import java.util.ArrayList;
import java.util.List;
import util.Util;

/**
 *
 * @author PC
 */
public class BusinessProduct {
    IndividualProductDAO inProdDao = new IndividualProductDAO();
    Util util = new Util();
    ArrayList<IndividualProduct> products;
    
    //Create new Product
    public void createProduct(PRODUCT_TYPE type, String name, int price, boolean state) {
        IndividualProduct inProd = new IndividualProduct();
        
        inProd.setType(type);
        inProd.setName(name);
        inProd.setPrice(price);
        inProd.setState(state);
        inProd.setActiveOffer(null);
        
        inProdDao.create(inProd);
        
    }

    public List<IndividualProduct> chargerProducts() {
        this.products = (ArrayList<IndividualProduct>) util.createProducts();
        return inProdDao.fillIndividualProductList(products);
    }

    
}
