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
public class OrderCardController implements Initializable {

    @FXML
    private Text txtNombreMesa;
    @FXML
    private Text txtFolioMesa;
    @FXML
    private Button btnVerOrden;
    @FXML
    private Button btnCobrar;

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
    
    
}
