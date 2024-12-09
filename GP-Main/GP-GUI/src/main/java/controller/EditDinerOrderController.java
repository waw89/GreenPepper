/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import business.OrderBusiness;
import com.mycompany.gp.domain.DinerOrder;
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
public class EditDinerOrderController implements Initializable {

    @FXML
    private Button btnCancel;
    @FXML
    private Button btnAccept;
    @FXML
    private TextField txtNewOrderName;
    
    private DinerOrder dinerOrder;
    
    OrderBusiness oBusiness = new OrderBusiness();
    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtNewPhoneNumber;

    /**
     * Initializes the controller class.
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (dinerOrder != null){
            setDinerOrder(dinerOrder);
        }
    }
    
    public void setDinerOrder(DinerOrder order){
        this.dinerOrder = order;
        txtNewOrderName.setText(dinerOrder.getOrderName());
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

    public TextField getTxtNewOrderName() {
        return txtNewOrderName;
    }

    public void setTxtNewOrderName(TextField txtNewOrderName) {
        this.txtNewOrderName = txtNewOrderName;
    }
      
    @FXML
    private void OptionCancel(MouseEvent event) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        
        stage.close();
    }

    @FXML
    private void OptionAccept(MouseEvent event) {
        dinerOrder.setOrderName(txtNewOrderName.getText());
        
        oBusiness.EditDataDiner(dinerOrder);
        
        Stage stage = (Stage) txtNewOrderName.getScene().getWindow();
        stage.close();
        
    }
    
    
}
