package controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ximenacastro
 */
public class PickUpFieldsController implements Initializable {

    @FXML
    private TextField customerNameTxt;
    @FXML
    private TextField phoneNumberTxt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public TextField getCustomerNameTxt() {
        return customerNameTxt;
    }

    public void setCustomerNameTxt(TextField customerNameTxt) {
        this.customerNameTxt = customerNameTxt;
    }

    public TextField getPhoneNumberTxt() {
        return phoneNumberTxt;
    }

    public void setPhoneNumberTxt(TextField phoneNumberTxt) {
        this.phoneNumberTxt = phoneNumberTxt;
    }
    
}
