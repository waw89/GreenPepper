/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class DeliveryHistoryCardController implements Initializable {

    @FXML
    private Text txtNombreCliente;
    @FXML
    private Text txtDireccion;
    @FXML
    private Text txtEstado;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public Text getTxtNombreCliente() {
        return txtNombreCliente;
    }

    public void setTxtNombreCliente(String txtNombreCliente) {
        this.txtNombreCliente.setText(txtNombreCliente);
    }

    public Text getTxtDireccion() {
        return txtDireccion;
    }

    public void setTxtDireccion(String txtDireccion) {
        this.txtDireccion.setText(txtDireccion);
    }

    public Text getTxtEstado() {
        return txtEstado;
    }

    public void setTxtEstado(String txtEstado) {
        if("CANCELADO".equals(txtEstado)){
            this.txtEstado.setFill(Color.RED);
        }else if("PAGADO".equals(txtEstado)){
            this.txtEstado.setFill(Color.GREEN);
        }
        
        this.txtEstado.setText(txtEstado);
    }

    
    
}
