/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import business.OrderBusiness;
import com.mycompany.gp.domain.DeliveryOrder;
import com.mycompany.gp.domain.ORDER_STATE;
import com.mycompany.gp.domain.Order;
import com.mycompany.gp.domain.ProductOrder;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class ActiveOrderDetailDeliveryController implements Initializable {

    @FXML
    private Text txtOpenDate;
    @FXML
    private Text txtIdOrder;
    @FXML
    private Text txtTotalPrice;
    @FXML
    private Text txtCustomerData;
    @FXML
    private VBox orderContainer;
    @FXML
    private Button btnAddProduct;
    @FXML
    private Button btnPayOrder;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnEdit;
    
    private DeliveryOrder deliveryOrder;
    List<ProductOrder> productOrderList;
    OrderBusiness oBusiness = new OrderBusiness();
    @FXML
    private ImageView btnBack;
    
    

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
        loadProductsOrder();
    }

    public Text getTxtOpenDate() {
        return txtOpenDate;
    }

    public void setTxtOpenDate(Text txtOpenDate) {
        this.txtOpenDate = txtOpenDate;
    }

    public Text getTxtIdOrder() {
        return txtIdOrder;
    }

    public void setTxtIdOrder(Text txtIdOrder) {
        this.txtIdOrder = txtIdOrder;
    }

    public Text getTxtTotalPrice() {
        return txtTotalPrice;
    }

    public void setTxtTotalPrice(Text txtTotalPrice) {
        this.txtTotalPrice = txtTotalPrice;
    }

    public Text getTxtCustomerData() {
        return txtCustomerData;
    }

    public void setTxtCustomerData(Text txtCustomerData) {
        this.txtCustomerData = txtCustomerData;
    }

    public VBox getOrderContainer() {
        return orderContainer;
    }

    public void setOrderContainer(VBox orderContainer) {
        this.orderContainer = orderContainer;
    }

    public Button getBtnAddProduct() {
        return btnAddProduct;
    }

    public void setBtnAddProduct(Button btnAddProduct) {
        this.btnAddProduct = btnAddProduct;
    }

    public Button getBtnPayOrder() {
        return btnPayOrder;
    }

    public void setBtnPayOrder(Button btnPayOrder) {
        this.btnPayOrder = btnPayOrder;
    }
    
    public void loadProductsOrder(){
        if(deliveryOrder == null) return;
        
        txtOpenDate.setText(deliveryOrder.getCreationDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
        txtIdOrder.setText("#" + deliveryOrder.getOrderNumber());
        txtTotalPrice.setText("$" + deliveryOrder.getPrice());
        txtCustomerData.setText(deliveryOrder.getCustomerName() + " - " + deliveryOrder.getAddress() + " - " + deliveryOrder.getPhoneNumber());
        
        productOrderList = deliveryOrder.getProducts();
        
        Map<String, Integer> productCount = new HashMap<>();
        
        for(ProductOrder orderProduct : productOrderList){
            String productName = orderProduct.getProduct().getName();
            productCount.put(productName, productCount.getOrDefault(productName, 0) + 1);
        }
        
        orderContainer.getChildren().clear();
        
        for(Map.Entry<String, Integer> entry : productCount.entrySet()){
            String productName = entry.getKey();
            int count = entry.getValue();

            float price = productOrderList.stream().filter(p -> p.getProduct().getName().equals(productName)).findFirst().map(ProductOrder::getPrice).orElse(0.0f);

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ProductOrder.fxml"));
                Parent orderCard = loader.load();
                ProductOrderController cardController = loader.getController();

                cardController.setTxtProduct(count + " x " + productName);
                cardController.setTxtPrice("$" + (count * price));

                orderContainer.getChildren().add(orderCard); 
                orderContainer.setSpacing(0);

            } catch (IOException ex) {
                Logger.getLogger(ClosedOrderDetailController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @FXML
    private void OptionAddProduct(MouseEvent event) {
    }

    @FXML
    private void OptionPayOrder(MouseEvent event) {
    
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PayOrder.fxml"));
            Parent orderCard = loader.load();
            PayOrderController payController = loader.getController();
            
            payController.setDeliveryOrder(deliveryOrder);
            
            Stage stage = new Stage();
            stage.setScene(new Scene(orderCard));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            
            if(deliveryOrder.getState()== ORDER_STATE.CANCELLED || deliveryOrder.getState() == ORDER_STATE.PAID){
                Stage stageClose = (Stage) btnPayOrder.getScene().getWindow();
                stageClose.close();
            }
            
            
            
            
                
        }catch (IOException ex) {
                Logger.getLogger(ClosedOrderDetailController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void OptionDeleteOrder(MouseEvent event) {
    
        try {
            Order order = oBusiness.findOrderById(deliveryOrder.getOrderNumber());
            oBusiness.cancelOrder(order);
            
            Stage stage = (Stage) btnDelete.getScene().getWindow();
            stage.close();
        } catch (Exception ex) {
            Logger.getLogger(OrderCardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    @FXML
    private void OptionEditOrder(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/EditDeliveryOrder.fxml"));
            Parent root = loader.load();
            
            EditDeliveryOrderController orderController = loader.getController();
            orderController.setDeliveryOrder(deliveryOrder);
            
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Editar nombre de Orden");
            stage.showAndWait();
            
            txtCustomerData.setText(deliveryOrder.getCustomerName() + " - " + deliveryOrder.getAddress() + " - " + deliveryOrder.getPhoneNumber());
            
            
        } catch (IOException ex) {
            Logger.getLogger(ActiveOrderDetailDeliveryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    }

    @FXML
    private void OptionBack(MouseEvent event) {
    
        Stage stage = (Stage) btnBack.getScene().getWindow();
        stage.close();
    
    }
    
}
