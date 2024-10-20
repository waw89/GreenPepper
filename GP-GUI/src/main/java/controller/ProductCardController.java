/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Raul
 */
public class ProductCardController implements Initializable {

    @FXML
    private Text txtProductName;
    @FXML
    private Text txtPrice;
    @FXML
    private ComboBox<?> cmbSize;
    @FXML
    private Button btnIncreaseAmount;
    @FXML
    private Text txtAmount;
    @FXML
    private Button btnAddProduct;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  

    public Text getTxtProductName() {
        return txtProductName;
    }

    public void setTxtProductName(String txtProductName) {
        this.txtProductName.setText(txtProductName);
    }

    public Text getTxtPrice() {
        return txtPrice;
    }

    public void setTxtPrice(String txtPrice) {
        this.txtPrice.setText(txtPrice);
    }

    public Text getTxtAmount() {
        return txtAmount;
    }

    public void setTxtAmount(Text txtAmount) {
        this.txtAmount = txtAmount;
    }
    
    
    
}
