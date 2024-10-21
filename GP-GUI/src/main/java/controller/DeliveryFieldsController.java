/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Raul
 */
public class DeliveryFieldsController implements Initializable {

    @FXML
    private TextField txtCustomerName;
    @FXML
    private TextField txtCustomerAddress;
    @FXML
    private TextField txtCustomerPhone;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   

    public TextField getTxtCustomerName() {
        return txtCustomerName;
    }

    public void setTxtCustomerName(TextField txtCustomerName) {
        this.txtCustomerName = txtCustomerName;
    }

    public TextField getTxtCustomerAddress() {
        return txtCustomerAddress;
    }

    public void setTxtCustomerAddress(TextField txtCustomerAddress) {
        this.txtCustomerAddress = txtCustomerAddress;
    }

    public TextField getTxtCustomerPhone() {
        return txtCustomerPhone;
    }

    public void setTxtCustomerPhone(TextField txtCustomerPhone) {
        this.txtCustomerPhone = txtCustomerPhone;
    }
    
    
    
}
