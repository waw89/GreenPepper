/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import business.OrderBusiness;
import com.mycompany.gp.domain.DeliveryOrder;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class EditDeliveryOrderController implements Initializable {

    @FXML
    private TextField txtNewOrderName;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnAccept;
    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtNewPhoneNumber;
    
    private DeliveryOrder deliveryOrder;
    OrderBusiness oBussines = new OrderBusiness();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(deliveryOrder != null){
            setDeliveryOrder(deliveryOrder);
        }
    }
    
    public void setDeliveryOrder(DeliveryOrder order){
        this.deliveryOrder = order;
        txtNewOrderName.setText(deliveryOrder.getCustomerName());
        txtAddress.setText(deliveryOrder.getAddress());
        txtNewPhoneNumber.setText(deliveryOrder.getPhoneNumber());
    }

    public TextField getTxtNewOrderName() {
        return txtNewOrderName;
    }

    public void setTxtNewOrderName(TextField txtNewOrderName) {
        this.txtNewOrderName = txtNewOrderName;
    }

    public Button getBtnCancel() {
        return btnCancel;
    }

    public void setBtnCancel(Button btnCancel) {
        this.btnCancel = btnCancel;
    }

    public Button getBtnAccept() {
        return btnAccept;
    }

    public void setBtnAccept(Button btnAccept) {
        this.btnAccept = btnAccept;
    }

    public TextField getTxtAddress() {
        return txtAddress;
    }

    public void setTxtAddress(TextField txtAddress) {
        this.txtAddress = txtAddress;
    }

    public TextField getTxtNewPhoneNumber() {
        return txtNewPhoneNumber;
    }

    public void setTxtNewPhoneNumber(TextField txtNewPhoneNumber) {
        this.txtNewPhoneNumber = txtNewPhoneNumber;
    }

    @FXML
    private void OptionCancel(MouseEvent event) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        
        stage.close();
    }

    @FXML
    private void OptionAccept(MouseEvent event) {
        deliveryOrder.setCustomerName(txtNewOrderName.getText());
        deliveryOrder.setAddress(txtAddress.getText());
        deliveryOrder.setPhoneNumber(txtNewPhoneNumber.getText());
        
        oBussines.EditDataDelivery(deliveryOrder);
    
        Stage stage = (Stage) txtNewOrderName.getScene().getWindow();
        stage.close();
    
    }
    
}
