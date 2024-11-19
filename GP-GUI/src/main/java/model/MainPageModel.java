/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import business.BusinessProduct;
import com.mycompany.gp.domain.IndividualProduct;
import controllers.ProductCardController;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.AnchorPane;

/**
 * @author Luis Enrique Contreras Peraza 
 * @author Jesús Raúl Luna Bringas
 * @author Brayan D. García Picos 
 */
public class MainPageModel {
    
    
    /*
        Calls a method from the business to load the products to the database.
        This method should be called everytime the application is started.
    
        Note: For next versions, it would be nice to validate if the products are already in the 
        database, so we dont load this again and save time.
    */
    
    public List<IndividualProduct> chargeProductsToDatabase() {
        listOfProductsInDatabase = accessPointToBusinessProduct.chargerProducts();
        return listOfProductsInDatabase; 
    }
    
    
    /*
        Retrieves all the products from the database
    */
    public List<IndividualProduct> getProductsFromDatabase(){
        return listOfProductsInDatabase; 
    }
    
    /*
        Assigns an observable array list to the list of productCardController
    */
    public void initializaListOfProductCard(){
        listOfProductCardElements = FXCollections.observableArrayList();
    }
    
    /*
        Adds a ProductCardController toi the list
    */
    public void addProductCardToList(ProductCardController productCard){
        listOfProductCardElements.add(productCard); 
    }
    
    /*
        Gets the list of product card
    */
    public ObservableList<ProductCardController> getListOfProductCardElements() {
        return listOfProductCardElements;
    }    
    
    
    private BusinessProduct accessPointToBusinessProduct = new BusinessProduct(); 
    private List<IndividualProduct> listOfProductsInDatabase; 
    private ObservableList<ProductCardController> listOfProductCardElements; 
    

}