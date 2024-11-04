/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import com.mycompany.gp.domain.Product;
import java.util.List;

/**
 *
 * @author Raul
 */
public interface IProductDAO {

    public List<Product> findProductEntities();
    public Product findProductByName(String name);
    public Product findSmallProduct(String name);
    public Product findMediumProduct(String name);
    public Product findLargeProduct(String name);
}
