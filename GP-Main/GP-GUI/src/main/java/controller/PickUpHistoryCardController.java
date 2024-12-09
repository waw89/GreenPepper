/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import business.OrderBusiness;
import com.mycompany.gp.domain.PickUpOrder;
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
public class PickUpHistoryCardController implements Initializable {

    @FXML
    private Text txtCliente;
    @FXML
    private Text txtTelefono;
    @FXML
    private Text txtEstado;
    @FXML
    private Button btnVerDetalles;
    
    OrderBusiness oBusiness = new OrderBusiness();
    List<PickUpOrder> orderList = oBusiness.getAllPickUpOrder();
    private PickUpOrder pickUpOrder;

    MainPageController mainController;

    public void setMainController(MainPageController mainController) {
        this.mainController = mainController;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        orderList = oBusiness.getAllPickUpOrder();
        
        if(pickUpOrder != null){
            setPickUpOrder(pickUpOrder);
        }
        
    }

    public void setPickUpOrder(PickUpOrder order){
        this.pickUpOrder = order; 
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
    
    
    private PickUpOrder findOrder(Long idPedido){
        Long orderId;
        orderId = idPedido;
        
        for(PickUpOrder orderFind : orderList){
            if(orderFind.getOrderNumber().equals(orderId)){
                return orderFind;
            }
        }
        return null;
    }
    

    @FXML
    private void detailOption(MouseEvent event) {
    
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ClosedOrderDetailPickUp.fxml"));
            Parent detail = loader.load();
            
            ClosedOrderDetailPickUpController detailController = loader.getController();
            
            detailController.setPickUpOrder(pickUpOrder);
            detailController.setMainPageController(mainController);
            mainController.getBp().setCenter(detail);
        
        } catch (IOException ex) {
            Logger.getLogger(PickUpHistoryCardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

}
