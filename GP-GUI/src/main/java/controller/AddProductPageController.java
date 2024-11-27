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
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Raul
 */
public class AddProductPageController implements Initializable {

    @FXML
    private ToggleButton btnExtra;
    @FXML
    private ToggleButton btnFood;
    @FXML
    private ToggleButton btnDrink;
    @FXML
    private TextField txtProductName;
    @FXML
    private ToggleButton btnCH;
    @FXML
    private ToggleButton btnM;
    @FXML
    private ToggleButton btnG;
    @FXML
    private TextField priceCH;
    @FXML
    private TextField priceM;
    @FXML
    private TextField priceG;
    @FXML
    private Button btnAddProduct;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void selectFood(MouseEvent event) {
    }

    @FXML
    private void selectDrink(MouseEvent event) {
    }

    @FXML
    private void selectExtra(MouseEvent event) {
    }

    @FXML
    private void btnCHSelected(MouseEvent event) {
    }

    @FXML
    private void btnMSelected(MouseEvent event) {
    }

    @FXML
    private void btnGSelected(MouseEvent event) {
    }

    @FXML
    private void addProduct(MouseEvent event) {
    }
    
}
