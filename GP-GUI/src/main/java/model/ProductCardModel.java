/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import com.mycompany.gp.domain.IndividualProduct;
import com.mycompany.gp.domain.Product;
import javafx.beans.property.SimpleStringProperty;

/**
 * @author waw
 */
public class ProductCardModel {
    private Product product;
    private SimpleStringProperty productName;
    private SimpleStringProperty productPriceAccumulated;
    private SimpleStringProperty ammount;
    
    
    public void customModelWithSameData(IndividualProduct foodProduct) {
        this.productName = new SimpleStringProperty(foodProduct.getName());
        this.productPriceAccumulated = new SimpleStringProperty(String.valueOf(foodProduct.getPrice()));
        this.ammount = new SimpleStringProperty("1");
        this.product = foodProduct;
    }

    public SimpleStringProperty getProductName() {
        return productName;
    }

    public void setProductName(SimpleStringProperty productName) {
        this.productName = productName;
    }

    public SimpleStringProperty getAccumulatedPrice() {
        return productPriceAccumulated;
    }

    public void setAccumulatedPrice(SimpleStringProperty price) {
        this.productPriceAccumulated = price;
    }

    public SimpleStringProperty getAmmount() {
        return ammount;
    }

    public void setAmmount(SimpleStringProperty ammount) {
        this.ammount = ammount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getProductNameStringPropertyContent(){
        return this.getProductName().getValue(); 
    }
    
    /*
        Increase ammount 
     */
    public void increaseAmmount() {

        int currentAmmount = Integer.parseInt(this.ammount.get());
        int newAmmount = currentAmmount + 1;
        this.ammount.set(String.valueOf(newAmmount));
        updatePriceWhenIncreased();

    }

    public void decreaseAmmountActionPerformed() {

        if (!(Integer.parseInt(this.ammount.get()) == 1)) {
            int currentAmmount = Integer.valueOf(ammount.get());
            int newDecreasedAmmount = currentAmmount - 1;
            this.ammount.set(String.valueOf(newDecreasedAmmount));
            updatePriceWhenDecreased();
        }

    }

    public void updatePriceWhenIncreased() {
        float currentPrice = Float.parseFloat(this.productPriceAccumulated.get());
        float newPrice = product.getPrice() + currentPrice;
        this.productPriceAccumulated.set(String.valueOf(newPrice));
    }

    public void updatePriceWhenDecreased() {

        float currentPrice = Float.parseFloat(this.productPriceAccumulated.get());
        float newPrice = currentPrice - product.getPrice();
        this.productPriceAccumulated.set(String.valueOf(newPrice));

    }



}
