/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import business.OrderBusiness;
import business.UserBusiness;
import com.mycompany.gp.domain.DeliveryOrder;
import com.mycompany.gp.domain.Employee;
import com.mycompany.gp.domain.ORDER_STATE;
import com.mycompany.gp.domain.Order;
import com.mycompany.gp.domain.ProductOrder;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Raul
 */
public class CreateOrderController implements Initializable {

    private Order order;
    @FXML
    private Button btnDelivery;
    @FXML
    private Button btnDiner;
    @FXML
    private Button btnPickUp;
    @FXML
    private VBox txtFieldContainer;
    @FXML
    private TextArea txtDetails;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnCreateOrder;

    private String type;
    
    UserBusiness us = new UserBusiness();
    OrderBusiness ob = new OrderBusiness();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        type = "Delivery";
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/DeliveryFields.fxml"));
            AnchorPane txtFieldArea = loader.load();
            // DeliveryFieldsController deliveryFC = loader.getController();
            txtFieldContainer.getChildren().add(txtFieldArea);
        } catch (IOException ex) {
            Logger.getLogger(CreateOrderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @FXML
    private void deliveryOption(MouseEvent event) {
    }

    @FXML
    private void dinerOption(MouseEvent event) {
        type = "Diner";
    }

    @FXML
    private void pickUpOption(MouseEvent event) {
        type = "Pick Up";
    }

    @FXML
    private void goBack(MouseEvent event) {
        
    }

    @FXML
    private void createOrder(MouseEvent event) throws IOException {
        if(type.equals("Delivery")){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/DeliveryFields.fxml"));
            AnchorPane txtFieldArea = loader.load();
            DeliveryFieldsController dfc = loader.getController();
            DeliveryOrder deliveryOrder = new DeliveryOrder();
            
            deliveryOrder.setProducts(order.getProducts());
            deliveryOrder.setPrice(order.getPrice());
            deliveryOrder.setCustomerName(dfc.getTxtCustomerName().getText());
            deliveryOrder.setAddress(dfc.getTxtCustomerAddress().getText());
            deliveryOrder.setPhoneNumber(dfc.getTxtCustomerPhone().getText());
            deliveryOrder.setDetails(this.txtDetails.getText());
            deliveryOrder.setState(ORDER_STATE.ACTIVE);
            deliveryOrder.setCreationDate(LocalDateTime.now());
            deliveryOrder.setCashier((Employee) us.findUser(2L));
            ob.createDeliveryOrder(deliveryOrder);
            System.out.println("Orden creada!");
        }else if(type.equals("Diner")){
            
        }else if(type.equals("Pick Up")){
            
        }
    }

}
