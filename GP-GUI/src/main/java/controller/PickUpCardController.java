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
    @FXML
    private Text txtNumeroTelefono;
    @FXML
    private Text txtNombreCliente;

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
 
}
