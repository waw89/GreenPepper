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
public class OrdHistoryCardController implements Initializable {

    @FXML
    private Text txtMesa;
    @FXML
    private Text txtIdPedido;
    @FXML
    private Button btnVerDetalles;
    @FXML
    private Text txtEstado;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public Text getTxtMesa() {
        return txtMesa;
    }

    public void setTxtMesa(String txtMesa) {
        this.txtMesa.setText(txtMesa);
    }

    public Text getTxtIdPedido() {
        return txtIdPedido;
    }

    public void setTxtIdPedido(String txtIdPedido) {
        this.txtIdPedido.setText(txtIdPedido);
    }

    public Button getBtnVerDetalles() {
        return btnVerDetalles;
    }

    public void setBtnVerDetalles(Button btnVerDetalles) {
        this.btnVerDetalles = btnVerDetalles;
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
