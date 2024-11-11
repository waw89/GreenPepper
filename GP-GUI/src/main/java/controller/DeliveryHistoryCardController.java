/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import business.OrderBusiness;
import com.mycompany.gp.domain.DeliveryOrder;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
    @FXML
    private Button btnVerDetalles;
    
    OrderBusiness oBusiness = new OrderBusiness();
    List<DeliveryOrder> orderList = oBusiness.getAllDelOrder();
    private DeliveryOrder deliveryOrder;

    MainPageController mainController;

    public void setMainController(MainPageController mainController) {
        this.mainController = mainController;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        orderList = oBusiness.getAllDelOrder();
        
        if(deliveryOrder != null){
            setDeliveryOrder(deliveryOrder);
        }
        
    }
    
    public void setDeliveryOrder(DeliveryOrder order){
        this.deliveryOrder = order;
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

    @FXML
    private void detailOption(MouseEvent event) {
    
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ClosedOrderDetailDelivery.fxml"));
            Parent detail = loader.load();
            
            ClosedOrderDetailDeliveryController detailController = loader.getController();
            detailController.setMainPageController(mainController);
            detailController.setDeliveryOrder(deliveryOrder);
            
            mainController.getBp().setCenter(detail);
            
        }catch (IOException ex) {
            Logger.getLogger(PickUpHistoryCardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    }

    
    
}
