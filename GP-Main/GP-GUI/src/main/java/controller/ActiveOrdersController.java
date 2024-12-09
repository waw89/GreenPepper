/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import business.OrderBusiness;
import com.mycompany.gp.domain.DeliveryOrder;
import com.mycompany.gp.domain.DinerOrder;
import static com.mycompany.gp.domain.ORDER_STATE.ACTIVE;
import com.mycompany.gp.domain.Order;
import com.mycompany.gp.domain.PickUpOrder;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Raul
 */
public class ActiveOrdersController implements Initializable {

    @FXML
    private Button btnDelivery;
    @FXML
    private Button btnDiner;
    @FXML
    private Button btnPickUp;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox orderContainer;
    
    OrderBusiness oBusiness = new OrderBusiness();
    
    MainPageController mainController;
    List<DinerOrder> dinerOrders = oBusiness.getAllDinOrder();
    List<PickUpOrder> pickUpOrders = oBusiness.getAllPickUpOrder();
    List<DeliveryOrder> deliveryOrders = oBusiness.getAllDelOrder();

    public void setMainController(MainPageController mainController) {
        this.mainController = mainController;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dinerOrders = oBusiness.getAllDinOrder();
        pickUpOrders = oBusiness.getAllPickUpOrder();
        deliveryOrders = oBusiness.getAllDelOrder();
        setButtonUnselected(btnDiner);
        setButtonUnselected(btnDelivery);
        setButtonUnselected(btnPickUp);
//        loadDeliveryOrders();
    }    
    
    public void loadDinerOrders() {
        orderContainer.getChildren().clear();  
        for(DinerOrder dOrder : dinerOrders){
            try {
                if(dOrder.getORDER_STATE().equals(ACTIVE)){
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/OrderCard.fxml"));
                    AnchorPane orderCard = loader.load();
                    OrderCardController cardController = loader.getController();
                    cardController.setMainPageController(mainController);
                    cardController.setDinerOrder(dOrder);
                    cardController.setTxtNombreMesa(dOrder.getOrderName());
                    cardController.setTxtFolioMesa("#" + dOrder.getOrderNumber().toString());
                    
                    
                    orderContainer.getChildren().add(orderCard);
                    orderContainer.setSpacing(10);
                }
            } catch (IOException ex) {
                Logger.getLogger(ActiveOrdersController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
     public void loadDeliveryOrders() {
        orderContainer.getChildren().clear();  
        for (DeliveryOrder delOrder : deliveryOrders) {
            try {
                if (delOrder.getState().equals(ACTIVE)) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/DeliveryCard.fxml"));
                    AnchorPane orderDeliveryCard = loader.load();
                    DeliveryCardController cardController = loader.getController();
                    cardController.setMainPageController(mainController);
                    cardController.setDeliveryOrder(delOrder);
                    cardController.setTxtNombreCliente(delOrder.getCustomerName() + " - " + delOrder.getAddress() + " - " + delOrder.getPhoneNumber());
                    cardController.setTxtFolio(String.valueOf("#" + delOrder.getOrderNumber()));

                    orderContainer.getChildren().add(orderDeliveryCard);
                    orderContainer.setSpacing(10);
                }
            } catch (IOException ex) {
                Logger.getLogger(ActiveOrdersController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    
    public void loadPickUpOrders() {
        orderContainer.getChildren().clear(); 
        for (PickUpOrder pickUpOrder : pickUpOrders) {
            try {
                if (pickUpOrder.getORDER_STATE().equals(ACTIVE)) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PickUpCard.fxml"));
                    AnchorPane PickUpCard = loader.load();
                    PickUpCardController cardController = loader.getController();
                    cardController.setMainPageController(mainController);
                    cardController.setPickUpOrder(pickUpOrder);
                    cardController.setTxtNombreCliente(pickUpOrder.getCustomerName() + " - " + pickUpOrder.getCustomerPhone());
                    cardController.setTxtFolio("#" + String.valueOf(pickUpOrder.getOrderNumber()));

                    orderContainer.getChildren().add(PickUpCard);
                    orderContainer.setSpacing(10);
                }
            } catch (IOException ex) {
                Logger.getLogger(ActiveOrdersController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    //Change color buttons
    private void setButtonSelected(Button button) {
        button.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-border-color: black; -fx-border-radius: 50; -fx-background-radius: 50;");
    }


    private void setButtonUnselected(Button button) {
        button.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-border-color: black; -fx-border-radius: 50; -fx-background-radius: 50;");
    }
    

    @FXML
    private void deliveryOption(MouseEvent event) {
        loadDeliveryOrders();
        setButtonSelected(btnDelivery);
        setButtonUnselected(btnDiner);
        setButtonUnselected(btnPickUp);
    }

    @FXML
    private void dinerOption(MouseEvent event) {
        loadDinerOrders();
        setButtonSelected(btnDiner);
        setButtonUnselected(btnDelivery);
        setButtonUnselected(btnPickUp);
    }

    @FXML
    private void pickUpOption(MouseEvent event) {
        loadPickUpOrders(); 
        setButtonSelected(btnPickUp);
        setButtonUnselected(btnDelivery);
        setButtonUnselected(btnDiner); 
    }
       
}
