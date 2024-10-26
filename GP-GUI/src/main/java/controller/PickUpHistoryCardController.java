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
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class PickUpHistoryCardController implements Initializable {

    @FXML
    private Text txtCliente;
    @FXML
    private Text txtTelefono;
    @FXML
    private Text txtEstado;
    @FXML
    private Button btnVerDetalles;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public Text getTxtCliente() {
        return txtCliente;
    }

    public void setTxtCliente(String txtCliente) {
        this.txtCliente.setText(txtCliente);
    }

    public Text getTxtTelefono() {
        return txtTelefono;
    }

    public void setTxtTelefono(String txtTelefono) {
        this.txtTelefono.setText(txtTelefono);
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

    public Button getBtnVerDetalles() {
        return btnVerDetalles;
    }

    public void setBtnVerDetalles(Button btnVerDetalles) {
        this.btnVerDetalles = btnVerDetalles;
    }

}
