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
public class PickUpCardController implements Initializable {

    @FXML
    private Button btnVerOrden;
    @FXML
    private Button btnCobrar;
    private Text txtNumeroTelefono;
    @FXML
    private Text txtNombreCliente;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnCancel;
    @FXML
    private Text txtFolio;

     OrderBusiness ob = new OrderBusiness();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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

    public Text getTxtNumeroTelefono() {
        return txtNumeroTelefono;
    }

    public void setTxtNumeroTelefono(String txtNumeroTelefono) {
        this.txtNumeroTelefono.setText(txtNumeroTelefono);
    }

    public Text getTxtNombreCliente() {
        return txtNombreCliente;
    }

    public void setTxtNombreCliente(String txtNombreCliente) {
        this.txtNombreCliente.setText(txtNombreCliente);
    }

    public Text getTxtFolio() {
        return txtFolio;
    }

    public void setTxtFolio(String txtFolio) {
        this.txtFolio.setText(txtFolio);
    }
    
    

    @FXML
    private void btnEdit(MouseEvent event) {
    }

     @FXML
    private void cancelOrder(MouseEvent event) {
        try {
            Order order = ob.findOrderById(Long.parseLong(this.txtFolio.getText()));
            ob.cancelOrder(order);
            System.out.println("Orden cancelada");
        } catch (Exception ex) {
            Logger.getLogger(OrderCardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
}
