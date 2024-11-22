/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import controller.ProductItemController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author waw
 */
public class ProductAddedModel {

    public ObservableList<ProductItemController> getListOfProductItems() 
    {
        return listOfProductItems;
    }
    
    public void addProductItemToList(ProductItemController productItem)
    {
        listOfProductItems.add(productItem);
    }
    
    public void initializeListOfProductItems()
    {
       listOfProductItems = FXCollections.observableArrayList(); 
    }
    
    
    private ObservableList<ProductItemController> listOfProductItems;
    
}
