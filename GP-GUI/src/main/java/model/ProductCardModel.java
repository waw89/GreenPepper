/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import com.mycompany.gp.domain.IndividualProduct;
import javafx.beans.property.SimpleStringProperty;

/**
 * @author waw 
 */

public class ProductCardModel {

    private SimpleStringProperty productName;
    private SimpleStringProperty price;
    private SimpleStringProperty ammount;

    public void customControllerWithProductData(IndividualProduct foodProduct) {
        this.productName = new SimpleStringProperty(foodProduct.getName());
        this.price = new SimpleStringProperty(String.valueOf(foodProduct.getPrice()));
        this.ammount = new SimpleStringProperty("1");
    }

    public SimpleStringProperty getProductName() {
        return productName;
    }

    public void setProductName(SimpleStringProperty productName) {
        this.productName = productName;
    }

    public SimpleStringProperty getPrice() {
        return price;
    }

    public void setPrice(SimpleStringProperty price) {
        this.price = price;
    }

    public SimpleStringProperty getAmmount() {
        return ammount;
    }

    public void setAmmount(SimpleStringProperty ammount) {
        this.ammount = ammount;
    }


}