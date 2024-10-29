package controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import business.OrderBusiness;
import com.mycompany.gp.domain.DinerOrder;
import com.mycompany.gp.domain.Order;
import com.mycompany.gp.domain.ProductOrder;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class ClosedOrderDetailController implements Initializable {

    @FXML
    private Text txtOpenDate;
    @FXML
    private Text txtIdOrder;
    @FXML
    private VBox orderContainer;
    @FXML
    private Text txtTotalPrice;
    @FXML
    private Text txtOrderName;
    
    OrderBusiness oBusiness = new OrderBusiness();
    
    private List<ProductOrder> productOrderList;
    private List<ProductOrder> productOrder = oBusiness.getAllProductOrder();
    private DinerOrder dinerOrder;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        productOrder = oBusiness.getAllProductOrder();
        
        if(dinerOrder != null){
            setOrderDetails(dinerOrder);
        }

    }
    
    public Order setOrderDetails(DinerOrder order){
        txtOpenDate.setText(order.getCreationDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
        txtIdOrder.setText("#"+String.valueOf(order.getOrderNumber()));
        txtTotalPrice.setText("$" + String.valueOf(order.getPrice()));
        txtOrderName.setText(order.getOrderName());
        
        productOrderList = order.getProducts();
        
            for(ProductOrder orderProduct  : productOrderList){                        
                        try {
                            
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ProductOrder.fxml"));
                            Parent orderCard = loader.load();
                            ProductOrderController cardController = loader.getController();
                            
                            cardController.setTxtProduct(orderProduct.getProduct().getName());
                            cardController.setTxtPrice(String.valueOf(orderProduct.getPrice()));

                            orderContainer.getChildren().add(orderCard);
                            orderContainer.setSpacing(0);
 
                        } catch (IOException ex) {
                            Logger.getLogger(ClosedOrderDetailController.class.getName()).log(Level.SEVERE, null, ex);
                        }
     
            }
        
        
        return order;
    }

    public Text getTxtOpenDate() {
        return txtOpenDate;
    }

    public void setTxtOpenDate(Text txtOpenDate) {
        this.txtOpenDate.setText(txtOpenDate.getText());
    }

    public Text getTxtIdOrder() {
        return txtIdOrder;
    }

    public void setTxtIdOrder(Text txtIdOrder) {
        this.txtIdOrder.setText(txtIdOrder.getText());
    }

    public VBox getOrderContainer() {
        return orderContainer;
    }

    public void setOrderContainer(VBox orderContainer) {
        this.orderContainer = orderContainer;
    }

    public Text getTxtTotalPrice() {
        return txtTotalPrice;
    }

    public void setTxtTotalPrice(Text txtTotalPrice) {
        this.txtTotalPrice.setText(txtTotalPrice.getText());
    }

    public Text getTxtOrderName() {
        return txtOrderName;
    }

    public void setTxtOrderName(Text txtOrderName) {
        this.txtOrderName.setText(txtOrderName.getText());
    }

    
    
}
