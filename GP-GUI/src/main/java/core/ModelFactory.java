/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core;

import model.MainPageModel;
import model.ProductCardModel;

/**
 * @author Luis Enrique Contreras Peraza
 * @author Jesús Raúl Luna Bringas
 * @author Brayan D. García Picos
 */
public class ModelFactory {

    public MainPageModel getMainPageModel() {
        if (this.mainPageModel == null) {
            this.mainPageModel = new MainPageModel();
        }

        return mainPageModel;
    }
    
    public ProductCardModel getProductCardModel(){
        return new ProductCardModel(); 
    }
    
    
    private MainPageModel mainPageModel;
    private ProductCardModel productCardModel;

}
