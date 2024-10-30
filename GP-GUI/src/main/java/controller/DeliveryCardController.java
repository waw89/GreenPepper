package controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import business.OrderBusiness;
import com.mycompany.gp.domain.DeliveryOrder;
import com.mycompany.gp.domain.Order;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class DeliveryCardController implements Initializable {

    @FXML
    private Text txtNombreCliente;
    private Text txtDireccion;
    @FXML
    private Button btnViewDetails;
    @FXML
    private Button btnPay;
    @FXML
    private Text txtFolio;
    
    OrderBusiness ob = new OrderBusiness();
    private DeliveryOrder deliveryOrder;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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

    public Text getTxtFolio() {
        return txtFolio;
    }

    public void setTxtFolio(String txtFolio) {
        this.txtFolio.setText(txtFolio);
    }
    
    @FXML
    private void OptionViewDetails(MouseEvent event) {
        
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ActiveOrderDetailDelivery.fxml"));
            Parent detail = loader.load();
            
            ActiveOrderDetailDeliveryController detailController = loader.getController();
            
            detailController.setDeliveryOrder(deliveryOrder);
            
            Stage stage = new Stage();
            stage.setScene(new Scene(detail));
            stage.show();
                    
        } catch (IOException ex) {
            Logger.getLogger(OrderCardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void OptionPayOrder(MouseEvent event) {
    }

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
