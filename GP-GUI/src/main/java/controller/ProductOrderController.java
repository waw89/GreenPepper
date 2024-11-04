/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class ProductOrderController implements Initializable {

    @FXML
    private Text txtProduct;
    @FXML
    private Text txtPrice;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  

    public Text getTxtProduct() {
        return txtProduct;
    }

    public void setTxtProduct(String txtProduct) {
        this.txtProduct.setText(txtProduct);
    }

    public Text getTxtPrice() {
        return txtPrice;
    }

    public void setTxtPrice(String txtPrice) {
        this.txtPrice.setText(txtPrice);
    }
    
    
}
