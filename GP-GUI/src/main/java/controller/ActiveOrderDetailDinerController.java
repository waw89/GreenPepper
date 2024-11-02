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
public class ActiveOrderDetailDinerController implements Initializable {

    @FXML
    private Text txtOpenDate;
    @FXML
    private Text txtIdOrder;
    @FXML
    private Text txtTotalPrice;
    @FXML
    private Text txtOrderName;
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
    
    
    private DinerOrder dinerOrder;
    List<ProductOrder> productOrderList;
    OrderBusiness oBusiness = new OrderBusiness();
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(dinerOrder != null){
            setDinerOrder(dinerOrder);
        }
    }
    
    public void setDinerOrder(DinerOrder order){
        this.dinerOrder = order;
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

    public Text getTxtOrderName() {
        return txtOrderName;
    }

    public void setTxtOrderName(Text txtOrderName) {
        this.txtOrderName = txtOrderName;
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
    
    public Button getBtnEdit() {
        return btnEdit;
    }

    public void setBtnEdit(Button btnEdit) {
        this.btnEdit = btnEdit;
    }

    public Button getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(Button btnDelete) {
        this.btnDelete = btnDelete;
    }
      
    public void loadProductsOrder(){
        if(dinerOrder == null) return;
        
        txtOpenDate.setText(dinerOrder.getCreationDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
        txtIdOrder.setText("#"+dinerOrder.getOrderNumber());
        txtTotalPrice.setText("$"+dinerOrder.getPrice());
        txtOrderName.setText(dinerOrder.getOrderName());
        
        productOrderList = dinerOrder.getProducts();
        
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
    }

    @FXML
    private void OptionDeleteOrder(MouseEvent event) {
        try {
                Order order = oBusiness.findOrderById(dinerOrder.getOrderNumber());
                oBusiness.cancelOrder(order);
                
                Stage stage = (Stage) btnDelete.getScene().getWindow();
                stage.close();
                
        } catch (Exception ex) {
            Logger.getLogger(ActiveOrderDetailDinerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    
    private void OptionEditOrder(MouseEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/EditDinerOrder.fxml"));
            Parent root = loader.load();
            
            EditDinerOrderController orderController = loader.getController();
            orderController.setDinerOrder(dinerOrder);
            
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Editar nombre de Orden");
            stage.showAndWait();
            
            txtOrderName.setText(dinerOrder.getOrderName());
            
        } catch (IOException ex) {
            Logger.getLogger(ActiveOrderDetailDinerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
    }
  
}
