/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import business.OrderBusiness;
import com.mycompany.gp.domain.DeliveryOrder;
import com.mycompany.gp.domain.DinerOrder;
import static com.mycompany.gp.domain.ORDER_STATE.CANCELLED;
import static com.mycompany.gp.domain.ORDER_STATE.PAID;
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
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class OrdersHistoryController implements Initializable {

    @FXML
    private DatePicker txtDesde;
    @FXML
    private DatePicker txtHasta;
    @FXML
    private Button btnComedor;
    @FXML
    private Button btnRecoger;
    @FXML
    private Button btnDomicilio;
    @FXML
    private VBox orderContainer;

    OrderBusiness oBusiness = new OrderBusiness();
    
    List<DinerOrder> dinerOrders = oBusiness.getAllDinOrder();
    List<PickUpOrder> pickUpOrders = oBusiness.getAllPickUpOrder();
    List<DeliveryOrder> deliveryOrders = oBusiness.getAllDelOrder();
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dinerOrders = oBusiness.getAllDinOrder();
        pickUpOrders = oBusiness.getAllPickUpOrder();
        deliveryOrders = oBusiness.getAllDelOrder();
        
        loadDinerOrders();
        
    }    
    
    private void loadDinerOrders(){
        orderContainer.getChildren().clear(); 
        
        for(DinerOrder dOrder : dinerOrders){
            try{
                
                if(dOrder.getORDER_STATE().equals(PAID) || dOrder.getORDER_STATE().equals(CANCELLED) ){
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/OrdHistoryCard.fxml"));
                    AnchorPane orderCard = loader.load();
                    OrdHistoryCardController cardController = loader.getController();
                    
                    cardController.setTxtIdPedido(dOrder.getOrderNumber().toString());
                    cardController.setTxtMesa(dOrder.getOrderName());
                    
                    if(dOrder.getORDER_STATE().equals(PAID)){
                        cardController.setTxtEstado("PAGADO");
                    } else if(dOrder.getORDER_STATE().equals(CANCELLED)){
                        cardController.setTxtEstado("CANCELADO");
                    }
                    
                    orderContainer.getChildren().add(orderCard);
                    orderContainer.setSpacing(10);    
                }
    
            } catch(IOException ex){
                Logger.getLogger(ActiveOrdersController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void loadDeliveryOrders(){
        orderContainer.getChildren().clear(); 
        
        for(DeliveryOrder delOrder : deliveryOrders){
            try{
                
                if(delOrder.getState().equals(PAID) || delOrder.getState().equals(CANCELLED) ){
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/DeliveryHistoryCard.fxml"));
                    AnchorPane orderCard = loader.load();
                    DeliveryHistoryCardController cardController = loader.getController();
                    
                    cardController.setDeliveryOrder(delOrder);
                    cardController.setTxtNombreCliente(delOrder.getCustomerName());
                    cardController.setTxtDireccion(delOrder.getAddress());                       
                    
                    if(delOrder.getState().equals(PAID)){
                        cardController.setTxtEstado("PAGADO");
                    } else if(delOrder.getState().equals(CANCELLED)){
                        cardController.setTxtEstado("CANCELADO");
                    }
                    
                    orderContainer.getChildren().add(orderCard);
                    orderContainer.setSpacing(10);    
                }
            } catch(IOException ex){
                Logger.getLogger(ActiveOrdersController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void loadPickUpOrders(){
        orderContainer.getChildren().clear();
        
        for(PickUpOrder pickOrder : pickUpOrders){
            try{
                
                if(pickOrder.getORDER_STATE().equals(PAID) || pickOrder.getORDER_STATE().equals(CANCELLED) ){
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PickUpHistoryCard.fxml"));
                    AnchorPane orderCard = loader.load();
                    PickUpHistoryCardController cardController = loader.getController();
                    
                    cardController.setPickUpOrder(pickOrder);
                    cardController.setTxtCliente(pickOrder.getCustomerName());
                    cardController.setTxtTelefono(pickOrder.getCustomerPhone());
                    
                    
                    if(pickOrder.getORDER_STATE().equals(PAID)){
                        cardController.setTxtEstado("PAGADO");
                    } else if(pickOrder.getORDER_STATE().equals(CANCELLED)){
                        cardController.setTxtEstado("CANCELADO");
                    }
                    
                    orderContainer.getChildren().add(orderCard);
                    orderContainer.setSpacing(10);    
                }
            } catch(IOException ex){
                Logger.getLogger(ActiveOrdersController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void setButtonSelected(Button button) {
        button.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-border-color: black; -fx-border-radius: 18; -fx-background-radius: 18;");
    }


    private void setButtonUnselected(Button button) {
        button.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-border-color: black; -fx-border-radius: 18; -fx-background-radius: 18;");
    }

    @FXML
    private void dinerOption(MouseEvent event) {
        loadDinerOrders();
        setButtonSelected(btnComedor);
        setButtonUnselected(btnDomicilio);
        setButtonUnselected(btnRecoger);       
    }

    @FXML
    private void pickUpOption(MouseEvent event) {
        loadPickUpOrders();
        setButtonSelected(btnRecoger);
        setButtonUnselected(btnComedor);
        setButtonUnselected(btnDomicilio);        
    }

    @FXML
    private void DeliveryOption(MouseEvent event) {
        loadDeliveryOrders();
        setButtonSelected(btnDomicilio);
        setButtonUnselected(btnComedor);
        setButtonUnselected(btnRecoger);
    }
  
}
