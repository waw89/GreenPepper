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
import javafx.scene.control.Tooltip;

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
        customerNameTxt.textProperty().addListener((observable, oldValue, newValue) -> {

            String cleaned = newValue.replaceAll("[^\\p{L} ]", "");
            customerNameTxt.setText(cleaned);
        });

        phoneNumberTxt.textProperty().addListener((observable, oldValue, newValue) -> {

            String cleaned = newValue.replaceAll("[^\\d]", "");

            if (cleaned.length() > 10) {
                cleaned = cleaned.substring(0, 10);
            }
            phoneNumberTxt.setText(cleaned);
        });
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

    public boolean hasEmptyFields() {
        boolean hasEmpty = false;

        if (customerNameTxt.getText() == null || customerNameTxt.getText().isBlank()) {
            customerNameTxt.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            Tooltip tooltip = new Tooltip("Este campo es obligatorio.");
            customerNameTxt.setTooltip(tooltip);
            hasEmpty = true;
        } else {
            customerNameTxt.setStyle("");
            customerNameTxt.setTooltip(null);
        }

        if (phoneNumberTxt.getText() == null || phoneNumberTxt.getText().isBlank()) {
            phoneNumberTxt.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            Tooltip tooltip = new Tooltip("Este campo es obligatorio.");
            phoneNumberTxt.setTooltip(tooltip);
            hasEmpty = true;
        } else {
            phoneNumberTxt.setStyle("");
            phoneNumberTxt.setTooltip(null);
        }

        return hasEmpty;
    }

    public boolean hasEnoughDigits() {
        if (phoneNumberTxt.getText().length() != 10) {
            phoneNumberTxt.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            Tooltip tooltip = new Tooltip("Debe contener 10 dígitos");
            phoneNumberTxt.setTooltip(tooltip);
            return false;
        }
        return true;
    }
}
