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
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Raul
 */
public class AdminProductCardController implements Initializable {

    @FXML
    private Text txtProductName;
    @FXML
    private Button editButton;
    @FXML
    private Button hideButton;

      

    /**
     * Getters & Setters
     */
    public void setTxtProductName(String txtProductName) {
        this.txtProductName.setText(txtProductName);
    }
    
    public Text getTxtProductName() {
        return txtProductName;
    }  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void editProduct(MouseEvent event) {
    }

    @FXML
    private void hideProduct(MouseEvent event) {
    }
    
}
