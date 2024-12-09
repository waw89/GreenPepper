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
import javafx.scene.control.Tooltip;

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
           
        txtCustomerName.textProperty().addListener((observable, oldValue, newValue) -> {
           
            String cleaned = newValue.replaceAll("[^\\p{L} ]", "");
            txtCustomerName.setText(cleaned);
        });

        
        txtCustomerPhone.textProperty().addListener((observable, oldValue, newValue) -> {
          
            String cleaned = newValue.replaceAll("[^\\d]", "");
            
            if (cleaned.length() > 10) {
                cleaned = cleaned.substring(0, 10);
            }
            txtCustomerPhone.setText(cleaned);
        });
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

    public boolean hasEmptyFields() {
        boolean hasEmpty = false;

        if (txtCustomerName.getText() == null || txtCustomerName.getText().isBlank()) {
            txtCustomerName.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            Tooltip tooltip = new Tooltip("Este campo es obligatorio.");
            txtCustomerName.setTooltip(tooltip);
            hasEmpty = true;
        } else {
            txtCustomerName.setStyle("");
            txtCustomerName.setTooltip(null);
        }

        if (txtCustomerAddress.getText() == null || txtCustomerAddress.getText().isBlank()) {
            txtCustomerAddress.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            Tooltip tooltip = new Tooltip("Este campo es obligatorio.");
            txtCustomerAddress.setTooltip(tooltip);
            hasEmpty = true;
        } else {
            txtCustomerAddress.setStyle("");
            txtCustomerAddress.setTooltip(null);
        }

        if (txtCustomerPhone.getText() == null || txtCustomerPhone.getText().isBlank()) {
            txtCustomerPhone.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            Tooltip tooltip = new Tooltip("Este campo es obligatorio.");
            txtCustomerPhone.setTooltip(tooltip);
            hasEmpty = true;
        } else {
            txtCustomerPhone.setStyle("");
            txtCustomerPhone.setTooltip(null);
        }

        return hasEmpty;
    }
    
    public boolean hasEnoughDigits() {
        if (txtCustomerPhone.getText().length() != 10) {
            txtCustomerPhone.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            Tooltip tooltip = new Tooltip("Debe contener 10 dígitos");
            txtCustomerPhone.setTooltip(tooltip);
            return false;
        }
        return true;
    }

}
