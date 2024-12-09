/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import business.OrderBusiness;
import com.mycompany.gp.domain.DeliveryOrder;
import com.mycompany.gp.domain.ORDER_STATE;
import com.mycompany.gp.domain.ProductOrder;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class ClosedOrderDetailDeliveryController implements Initializable {

    @FXML
    private Text txtOpenDate;
    @FXML
    private Text txtIdOrder;
    @FXML
    private VBox orderContainer;
    @FXML
    private Text txtTotalPrice;
    @FXML
    private Text txtCustomerData;
    @FXML
    private Button btnReopen;
    
    private DeliveryOrder deliveryOrder;
    private List<ProductOrder> productOrderList;
    OrderBusiness oBusiness = new OrderBusiness();
    @FXML
    private ImageView btnBack;
    
    MainPageController mainPageController;

    public void setMainPageController(MainPageController mainPageController) {
        this.mainPageController = mainPageController;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        if(deliveryOrder != null){
            setDeliveryOrder(deliveryOrder);
        }
        
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
        this.txtTotalPrice = txtTotalPrice;
    }

    public Text getTxtCustomerData() {
        return txtCustomerData;
    }

    public void setTxtCustomerData(Text txtCustomerData) {
        this.txtCustomerData = txtCustomerData;
    }

    public DeliveryOrder getDeliveryOrder() {
        return deliveryOrder;
    }

    public void setDeliveryOrder(DeliveryOrder deliveryOrder) {
        this.deliveryOrder = deliveryOrder;
        loadProductsOrder();
    }
    
    private void loadProductsOrder(){
        if(deliveryOrder == null) return;
        
        txtOpenDate.setText(deliveryOrder.getCreationDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
        txtIdOrder.setText("#" + deliveryOrder.getOrderNumber());
        txtTotalPrice.setText("$" + deliveryOrder.getPrice() + " - " + deliveryOrder.getPaymentMethod());
        txtCustomerData.setText(deliveryOrder.getCustomerName() + " - " + deliveryOrder.getAddress());
        
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
    private void OptionReopenOrder(MouseEvent event) {
        try {
            boolean openOrder = askForOpen();
            if (openOrder) {
                deliveryOrder.setState(ORDER_STATE.ACTIVE);
                oBusiness.EditDataDelivery(deliveryOrder);
                showOrderOpenedConfirmation();
                mainPageController.loadPage("OrdersHistory");
            }
        } catch (Exception e) {
            Logger.getLogger(OrderCardController.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    @FXML
    private void OptionBack(MouseEvent event) {
    
        Stage stage = (Stage) btnBack.getScene().getWindow();
        stage.close();
    
    }
    
        private void showOrderOpenedConfirmation() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Orden reabierta");
        alert.setContentText("Se ha reabierto la orden correctamente");
        alert.showAndWait();
    }

    private boolean askForOpen() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Reabrir la orden");
        alert.setContentText("¿Desea volver a abrir la orden?");

        Optional<ButtonType> result = alert.showAndWait();

        return result.isPresent() && result.get() == ButtonType.OK;
    }
    
    
    
}
