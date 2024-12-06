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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import util.Util;

/**
 *
 * @author PC
 */
public class BusinessProduct {

    IndividualProductDAO inProdDao = new IndividualProductDAO();
    Util util = new Util();
    List<IndividualProduct> products;
    IProductDAO prodDao = new ProductDAO();

    public void createSingleProduct(IndividualProduct product) {
        inProdDao.create(product);
    }

    public void createManyProducts(List<IndividualProduct> products) {
        for (IndividualProduct product : products) {
            inProdDao.create(product);
        }
    }

    public List<Product> getAllProducts() {
        return prodDao.findProductEntities();
    }

    public List<IndividualProduct> getAllFoods() {
        List<IndividualProduct> products = findProducts();
        List<IndividualProduct> foodProducts = new ArrayList<>();
        Set<String> uniqueProductNames = new HashSet<>();

        for (IndividualProduct product : products) {
            if (product.getType() == PRODUCT_TYPE.FOOD) {
                foodProducts.add(product);
            }
        }

        return foodProducts;
    }

    public List<IndividualProduct> getAllDrinks() {
        List<IndividualProduct> products = findProducts();
        List<IndividualProduct> drinkProducts = new ArrayList<>();
        Set<String> uniqueProductNames = new HashSet<>();

        for (IndividualProduct product : products) {
            if (product.getType() == PRODUCT_TYPE.DRINK) {
                drinkProducts.add(product);
            }
        }

        return drinkProducts;
    }

    public List<IndividualProduct> getAllExtras() {
        List<IndividualProduct> products = findProducts();
        List<IndividualProduct> extraProducts = new ArrayList<>();
        Set<String> uniqueProductNames = new HashSet<>();

        for (IndividualProduct product : products) {
            if (product.getType() == PRODUCT_TYPE.EXTRA) {
                extraProducts.add(product);
            }
        }

        return extraProducts;
    }

    public List<IndividualProduct> findProducts() {
        products = this.inProdDao.findIndividualProductEntities();
        return products;
    }

    public Product findProductByName(String name) {

        return prodDao.findProductByName(name);
    }

    public Product findProductBySize(String name, String size) {
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

    public void enableProduct(Product product) throws Exception {
        product.setState(true);
        this.prodDao.edit(product);
    }

    public void disableProduct(Product product) throws Exception {
        product.setState(false);
        this.prodDao.edit(product);
    }
    
    public void editProduct(Product product) throws Exception{
        this.prodDao.edit(product);
    }

}
