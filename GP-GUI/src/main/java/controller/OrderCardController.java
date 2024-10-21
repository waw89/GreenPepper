/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import business.OrderBusiness;
import com.mycompany.gp.domain.Order;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class OrderCardController implements Initializable {

    @FXML
    private Text txtNombreMesa;
    @FXML
    private Text txtFolioMesa;
    private Button btnVerOrden;
    private Button btnCobrar;
    @FXML
    private Button btnViewDetails;
    @FXML
    private Button btnPay;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnDelete;

    OrderBusiness ob = new OrderBusiness();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public Text getTxtNombreMesa() {
        return txtNombreMesa;
    }

    public void setTxtNombreMesa(String txtNombreMesa) {
        this.txtNombreMesa.setText(txtNombreMesa); 
    }

    public Text getTxtFolioMesa() {
        return txtFolioMesa;
    }

    public void setTxtFolioMesa(String txtFolioMesa) {
        this.txtFolioMesa.setText(txtFolioMesa);
    }

    public Button getBtnVerOrden() {
        return btnVerOrden;
    }

    public void setBtnVerOrden(Button btnVerOrden) {
        this.btnVerOrden = btnVerOrden;
    }

    public Button getBtnCobrar() {
        return btnCobrar;
    }

    public void setBtnCobrar(Button btnCobrar) {
        this.btnCobrar = btnCobrar;
    }
    
    

    @FXML
    private void editOrder(MouseEvent event) {
    }

    @FXML
    private void cancelOrder(MouseEvent event) {
        try {
            Order order = ob.findOrderById(Long.parseLong(this.txtFolioMesa.getText()));
            ob.cancelOrder(order);
            System.out.println("Orden cancelada");
        } catch (Exception ex) {
            Logger.getLogger(OrderCardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
