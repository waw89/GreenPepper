/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import controller.ProductItemController;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author waw
 */
public class ProductAddedModel {

    private ObservableList<ProductItemController> listOfProductItems;
    private SimpleStringProperty txtSummaryProductName;
    private SimpleStringProperty txtProductSummaryPrice;
    private SimpleStringProperty txtAmount;

    /**
     * Returns the list of product items.
     *
     */
    public ObservableList<ProductItemController> getListOfProductItems() {
        return listOfProductItems;
    }

    /**
     * Takes a ProductItemController and adds it to the list.
     */
    public void addProductItemToList(ProductItemController productItem) {
        listOfProductItems.add(productItem);
    }

    /**
     * This method must be called before working with the list of product items.
     */
    public void initializeListOfProductItems() {
        listOfProductItems = FXCollections.observableArrayList();
    }

    public SimpleStringProperty getTxtSummaryProductName() {
        return txtSummaryProductName;
    }

    public void setTxtSummaryProductName(SimpleStringProperty txtSummaryProductName) {
        this.txtSummaryProductName = txtSummaryProductName;
    }

    public SimpleStringProperty getTxtProductSummaryPrice() {
        return txtProductSummaryPrice;
    }

    public void setTxtProductSummaryPrice(SimpleStringProperty txtProductSummaryPrice) {
        this.txtProductSummaryPrice = txtProductSummaryPrice;
    }

    public SimpleStringProperty getTxtAmount() {
        return txtAmount;
    }

    public void setTxtAmount(SimpleStringProperty txtAmount) {
        this.txtAmount = txtAmount;
    }
    
    public void customModelWithSameData(ProductCardModel productCardModel) {
       this.txtSummaryProductName = new SimpleStringProperty(String.valueOf(productCardModel.getProductName().getValue()));
       this.txtProductSummaryPrice = new SimpleStringProperty(String.valueOf(productCardModel.getAccumulatedPrice().getValue()));
       this.txtAmount = new SimpleStringProperty(String.valueOf(productCardModel.getAmmount().getValue()));
       
    }

}
