/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business;

import com.mycompany.gp.domain.IndividualProduct;
import com.mycompany.gp.domain.PRODUCT_SIZE;
import com.mycompany.gp.domain.PRODUCT_TYPE;
import com.mycompany.gp.domain.Product;
import dao.IProductDAO;
import dao.IndividualProductDAO;
import dao.ProductDAO;
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
    IProductDAO prodDao = new ProductDAO();

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

    public List<Product> getAllProducts() {
        return prodDao.findProductEntities();
    }
    
    public List<IndividualProduct> getAllFoods() {
    List<IndividualProduct> products = findProducts();
    List<IndividualProduct> foodProducts = new ArrayList<>();
    
    for (IndividualProduct product : products) {
       
        if (product.getType() == PRODUCT_TYPE.FOOD && product.getPRODUCT_SIZE() == PRODUCT_SIZE.SMALL || product.getPRODUCT_SIZE() == PRODUCT_SIZE.STUDENT) {
            foodProducts.add(product);
        }
    }
    
    return foodProducts;
}


    public List<IndividualProduct> getAllDrinks(){
        List<IndividualProduct> products = findProducts();
        List<IndividualProduct> drinkProducts = new ArrayList<>();
        for(IndividualProduct product : products){
            if(product.getType() == PRODUCT_TYPE.DRINK && product.getPRODUCT_SIZE() == PRODUCT_SIZE.SMALL){
                drinkProducts.add(product);
            }
        }
        return drinkProducts;
    }
    
    public List<IndividualProduct> getAllExtras(){
        List<IndividualProduct> products = findProducts();
        List<IndividualProduct> extraProducts = new ArrayList<>();
        for(IndividualProduct product : products){
            if(product.getType() == PRODUCT_TYPE.EXTRA){
                extraProducts.add(product);
            }
        }
        return extraProducts;
    }
    
    public List<IndividualProduct> findProducts() {
        this.products = (ArrayList<IndividualProduct>) util.createProducts();
        return products;
    }

    public Product findProductByName(String name) {
        
        return prodDao.findProductByName(name);
    }
    
    public Product findProductBySize(String name,String size){
       Product product = new Product();
        switch (size) {
            case "CH":
              product = prodDao.findSmallProduct(name);
                break;
            case "M":
               product = prodDao.findMediumProduct(name);
                break;
            case "G":
               product = prodDao.findLargeProduct(name);
                break;
            default:
                break;
        }
        return product;
    }

}
